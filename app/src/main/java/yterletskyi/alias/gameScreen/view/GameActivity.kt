package yterletskyi.alias.gameScreen.view

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.presenter.GamePresenter
import yterletskyi.alias.gameScreen.view.EndRoundDialog.EndRoundDialog

class GameActivity : AppCompatActivity(), GameView {

    @BindView(R.id.text_win_score)
    lateinit var mWinScoreText: TextView

    @BindView(R.id.text_draw_score)
    lateinit var mDrawScoreText: TextView

    @BindView(R.id.text_timer_value)
    lateinit var mTimerValueText: TextView

    @BindView(R.id.activity_game)
    lateinit var mRootLayout: ConstraintLayout

    lateinit var mMneu: Menu

    var mPresenter: GamePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        ButterKnife.bind(this)
        mPresenter = GamePresenter(this, this)
        mPresenter!!.onCreate()
        mRootLayout.setOnClickListener { }
    }

    override fun showSnackbar(descrResId: Int, actionResId: Int) {
        Snackbar.make(mRootLayout, descrResId, Snackbar.LENGTH_INDEFINITE).setAction(actionResId, {
            mPresenter!!.startGame()
        }).show()
    }

    override fun setupActionBar(teamName: String) {
        supportActionBar!!.title = teamName
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        mMneu = menu!!
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
            mMneu.findItem(R.id.item_menu_resume).isVisible = true
            return true
        } else if (itemId == R.id.item_menu_resume) {
            mPresenter!!.resume()
            item.isVisible = false
            mMneu.findItem(R.id.item_menu_pause).isVisible = true
            return true
        }
        return false
    }

    override fun setWord(word: String) {
        (findViewById(R.id.text_word) as TextView).setText(word)
    }

    @OnClick(R.id.btn_correct_answer)
    fun onCorrectAnswer() {
        mPresenter!!.wrongAnswer()
    }

    @OnClick(R.id.btn_not_correct_answer)
    fun onNotCorrectAnswer() {
        mPresenter!!.correctAnswer()
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

    override fun changeTimerValue(time: Int) {
        mTimerValueText.text = time.toString()
    }

    override fun setDrawScore(draws: Int) {
        mDrawScoreText.text = draws.toString()
    }

    override fun setWinScore(wins: Int) {
        mWinScoreText.text = wins.toString()
    }

    override fun onStop() {
        mPresenter!!.onStop()
        super.onStop()
    }
}
