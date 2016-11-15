package yterletskyi.alias.gameScreen.presenter

import android.content.Context
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Game
import yterletskyi.alias.gameScreen.model.OnRoundTimeListener
import yterletskyi.alias.gameScreen.view.GameView

/**
 * Created by yterletskyi on 13.11.16.
 */
class GamePresenter(context: Context, val mView: GameView) : OnRoundTimeListener {

    private var mGame: Game = Game(context)

    fun onCreate() {
        mGame.onRoundEndListener = this
        mView.showSnackbar(R.string.tap_to_start, R.string.start)
        mView.setupActionBar(mGame.getCurrentTeam().name)
        mView.changeTimerValue(mGame.getGameLength())
    }

    fun startGame() {
        setWord()
        enableUi()
        mGame.start()
    }

    private fun setWord() {
        // todo add animation here
        val word = mGame.getNextWord()
        mView.setWord(word)
    }

    private fun enableUi() {
        mView.enableButtons()
    }

    private fun disableUi() {
        mView.disableButtons()
    }

    fun correctAnswer() {
        val wins = mGame.correctAnswer()
        mView.setWinScore(wins)
        mView.setWord(mGame.getNextWord())
    }

    fun wrongAnswer() {
        val draws = mGame.wrongAnswer()
        mView.setDrawScore(draws)
        mView.setWord(mGame.getNextWord())
    }

    override fun onSecondElapsed(time: Int) {
        mView.changeTimerValue(time)
    }

    override fun onRoundEnded() {
        disableUi()
        mView.changeTimerValue(0)
    }

    fun pause() {

    }
}