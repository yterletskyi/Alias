package yterletskyi.alias.gameScreen.presenter

import android.content.Context
import yterletskyi.alias.R
import yterletskyi.alias.TimeFormatter
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
        mView.setupActionBarTitle(mGame.getCurrentTeam().name)
        val roundLength = TimeFormatter().formatTimeStr(mGame.getRoundLength())
        mView.changeTimerValue(roundLength)
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
        mView.setWinScore(wins.toString())
        mView.setWord(mGame.getNextWord())
    }

    fun wrongAnswer() {
        val draws = mGame.wrongAnswer()
        mView.setDrawScore(draws.toString())
        mView.setWord(mGame.getNextWord())
    }

    override fun onSecondElapsed(time: Int) {
        val timeStr = TimeFormatter().formatTimeStr(time)
        mView.changeTimerValue(timeStr)
    }

    override fun onRoundEnded() {
        mView.changeTimerValue("0")
        mView.openEndRoundDialog(mGame.teamsArrayList)
    }

    fun pause() {
        disableUi()
        mGame.pause()
    }

    fun resume() {
        enableUi()
        mGame.resume()
    }

    fun onStop() {
        mGame.stop()
    }
}