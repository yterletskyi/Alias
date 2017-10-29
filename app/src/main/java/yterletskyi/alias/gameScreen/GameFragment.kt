package yterletskyi.alias.gameScreen

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.AnimRes
import android.support.annotation.ArrayRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_game.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.GamePreferences
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.view.endRoundDialog.EndRoundDialog

/**
 * Created by Yura T on 10/29/2017.
 */
class GameFragment : Fragment(), GameContract.View {

    override lateinit var presenter: GameContract.Presenter

    private lateinit var mMenu: Menu

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(R.layout.fragment_game, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun enableButtons() {
        btn_correct_answer.isEnabled = true
        btn_not_correct_answer.isEnabled = true
    }

    override fun disableButtons() {
        btn_correct_answer.isEnabled = false
        btn_not_correct_answer.isEnabled = false
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_correct_answer.setOnClickListener({ presenter.answerCorrect() })
        btn_not_correct_answer.setOnClickListener({ presenter.answerWrong() })
    }

    override fun showSnackbar(descrResId: Int, actionResId: Int) {
        Snackbar.make(fragment_game, descrResId, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionResId, { presenter.startGame() })
                .show()
    }

    override fun setupActionBarTitle(teamName: String) {
        activity.actionBar!!.title = teamName
    }

    override fun setMaxTimeProgressBarValue(value: Int) {
        progressBar.max = value
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        mMenu = menu!!
        inflater!!.inflate(R.menu.menu_game, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item!!.itemId
        if (itemId == R.id.item_menu_pause) {
            presenter.pause()
            item.isVisible = false
            mMenu.findItem(R.id.item_menu_resume).isVisible = true
            return true
        } else if (itemId == R.id.item_menu_resume) {
            presenter.resume()
            item.isVisible = false
            mMenu.findItem(R.id.item_menu_pause).isVisible = true
            return true
        }
        return false
    }

    override fun setWord(word: String, @AnimRes animId: Int) {
        text_word.startAnimation(AnimationUtils.loadAnimation(context, animId))
        text_word.text = word
    }

    override fun openEndRoundDialog(teamsArrayList: MutableList<Team>) {
        val dialog = EndRoundDialog(context, teamsArrayList)
        dialog.teamSelectListener = presenter
        dialog.show()
    }

    override fun setTimerValue(time: String) {
        text_timer_value.text = time
    }

    override fun changeProgressBarValue(time: Int) {
        progressBar.progress = time
    }

    override fun setDrawScore(draws: String) {
        text_draw_score.text = draws
    }

    override fun setWinScore(wins: String) {
        text_win_score.text = wins
    }

    override fun showOptionItem() {
        mMenu.findItem(R.id.item_menu_pause).isVisible = true
    }

    override fun hideOptionItem() {
        mMenu.findItem(R.id.item_menu_pause).isVisible = false
    }

    override fun getResArray(@ArrayRes resId: Int): Array<String> {
        return resources.getStringArray(resId)
    }

    override fun getGamePreferences(): GamePreferences {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        return GamePreferences(sharedPrefs)
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }
}