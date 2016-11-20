package yterletskyi.alias.gameScreen.view

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.presenter.GamePresenter
import yterletskyi.alias.gameScreen.view.EndRoundDialog.EndRoundDialog

class GameActivity : AppCompatActivity(), GameView {

    @BindView(R.id.progressBar)
    lateinit var mTimeProgressBar: ProgressBar

    @BindView(R.id.text_win_score)
    lateinit var mWinScoreText: TextView

    @BindView(R.id.text_draw_score)
    lateinit var mDrawScoreText: TextView

    @BindView(R.id.text_timer_value)
    lateinit var mTimerValueText: TextView

    @BindView(R.id.text_word)
    lateinit var mWordTxtView: TextView

    @BindView(R.id.activity_game)
    lateinit var mRootLayout: ConstraintLayout

    lateinit var mMenu: Menu

    var mPresenter: GamePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        ButterKnife.bind(this)
        mPresenter = GamePresenter(this, this)
        mPresenter!!.onCreate()
    }

    override fun showSnackbar(descrResId: Int, actionResId: Int) {
        Snackbar.make(mRootLayout, descrResId, Snackbar.LENGTH_INDEFINITE).setAction(actionResId, {
            mPresenter!!.startGame()
        }).show()
    }

    override fun setupActionBarTitle(teamName: String) {
        supportActionBar!!.title = teamName
    }

    override fun setMaxTimeProgressBarValue(value: Int) {
        mTimeProgressBar.max = value
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
            mPresenter!!.pause()
            item.isVisible = false
            mMenu.findItem(R.id.item_menu_resume).isVisible = true
            return true
        } else if (itemId == R.id.item_menu_resume) {
            mPresenter!!.resume()
            item.isVisible = false
            mMenu.findItem(R.id.item_menu_pause).isVisible = true
            return true
        }
        return false
    }

    override fun setWord(word: String, @IdRes animId: Int) {
        mWordTxtView.startAnimation(AnimationUtils.loadAnimation(this, animId))
        mWordTxtView.text = word
    }

    @OnClick(R.id.btn_correct_answer)
    fun onCorrectAnswer() {
        mPresenter!!.correctAnswer()
    }

    @OnClick(R.id.btn_not_correct_answer)
    fun onNotCorrectAnswer() {
        mPresenter!!.wrongAnswer()
    }

    override fun enableButtons() {
        findViewById(R.id.btn_correct_answer).isEnabled = true;
        findViewById(R.id.btn_not_correct_answer).isEnabled = true;
    }

    override fun disableButtons() {
        findViewById(R.id.btn_correct_answer).isEnabled = false;
        findViewById(R.id.btn_not_correct_answer).isEnabled = false;
    }

    override fun openEndRoundDialog(teamsArrayList: MutableList<Team>) {
        val dialog = EndRoundDialog(this, teamsArrayList)
        dialog.show()
    }

    override fun changeTimerValue(time: String) {
        mTimerValueText.text = time
    }

    override fun changeProgressBarValue(time: Int) {
        mTimeProgressBar.progress = time
    }

    override fun setDrawScore(draws: String) {
        mDrawScoreText.text = draws
    }

    override fun setWinScore(wins: String) {
        mWinScoreText.text = wins
    }

    override fun showOptionItem() {
        mMenu.findItem(R.id.item_menu_pause).isVisible = true
    }

    override fun hideOptionItem() {
        mMenu.findItem(R.id.item_menu_pause).isVisible = false
    }

    override fun onStop() {
        mPresenter!!.onStop()
        super.onStop()
    }
}
