package yterletskyi.alias.gameScreen.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.AnimRes
import android.support.annotation.ArrayRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_game.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.GamePreferences
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.presenter.GamePresenter
import yterletskyi.alias.gameScreen.view.endRoundDialog.EndRoundDialog

class GameActivity : AppCompatActivity(), GameView {

    lateinit var mMenu: Menu

    val mPresenter: GamePresenter = GamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mPresenter.onCreate()
    }

    override fun showSnackbar(descrResId: Int, actionResId: Int) {
        Snackbar.make(activity_game, descrResId, Snackbar.LENGTH_INDEFINITE).setAction(actionResId, {
            mPresenter.startGame()
        }).show()
    }

    override fun setupActionBarTitle(teamName: String) {
        supportActionBar!!.title = teamName
    }

    override fun setMaxTimeProgressBarValue(value: Int) {
        progressBar.max = value
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        mMenu = menu!!
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item!!.itemId
        if (itemId == R.id.item_menu_pause) {
            mPresenter.pause()
            item.isVisible = false
            mMenu.findItem(R.id.item_menu_resume).isVisible = true
            return true
        } else if (itemId == R.id.item_menu_resume) {
            mPresenter.resume()
            item.isVisible = false
            mMenu.findItem(R.id.item_menu_pause).isVisible = true
            return true
        }
        return false
    }

    override fun setWord(word: String, @AnimRes animId: Int) {
        text_word.startAnimation(AnimationUtils.loadAnimation(this, animId))
        text_word.text = word
    }

    override fun enableButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disableButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //    @OnClick(R.id.btn_correct_answer)
//    fun onCorrectAnswer() {
//        mPresenter.answerCorrect()
//    }
//
//    @OnClick(R.id.btn_not_correct_answer)
//    fun onNotCorrectAnswer() {
//        mPresenter.answerWrong()
//    }

//    override fun enableButtons() {
//        findViewById(R.id.btn_correct_answer).isEnabled = true
//        findViewById(R.id.btn_not_correct_answer).isEnabled = true
//    }
//
//    override fun disableButtons() {
//        findViewById(R.id.btn_correct_answer).isEnabled = false
//        findViewById(R.id.btn_not_correct_answer).isEnabled = false
//    }

    override fun openEndRoundDialog(teamsArrayList: MutableList<Team>) {
        val dialog = EndRoundDialog(this, teamsArrayList)
        dialog.teamSelectListener = mPresenter
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
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        return GamePreferences(sharedPrefs)
    }

    override fun onStop() {
        mPresenter.onStop()
        super.onStop()
    }
}
