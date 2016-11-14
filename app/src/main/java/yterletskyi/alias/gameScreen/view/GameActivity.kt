package yterletskyi.alias.gameScreen.view

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.presenter.GamePresenter

class GameActivity : AppCompatActivity(), GameView {

    @BindView(R.id.activity_game)
    lateinit var mRootLayout: ConstraintLayout

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
        supportActionBar!!.setTitle(teamName)
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

}
