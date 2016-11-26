package yterletskyi.alias.gameScreen.presenter

import android.content.Context
import yterletskyi.alias.R
import yterletskyi.alias.TimeFormatter
import yterletskyi.alias.gameScreen.model.Game
import yterletskyi.alias.gameScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.gameScreen.model.OnRoundTimeListener
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.view.GameView

/**
 * Created by yterletskyi on 13.11.16.
 */
class GamePresenter(context: Context, val mView: GameView) : OnRoundTimeListener, OnEndRoundTeamSelectListener {

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
        showOptionItem()
        setupTimeProgressBar()
        mGame.start()
    }

    private fun setupTimeProgressBar() {
        mView.setMaxTimeProgressBarValue(mGame.getRoundLength())
    }

    fun hideOptionItem() {
        mView.hideOptionItem()
    }

    fun showOptionItem() {
        mView.showOptionItem()
    }

    private fun setWord() {
        val word = mGame.getNextWord()
        mView.setWord(word, R.anim.fade_in_animation)
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
        setWord()
    }

    fun wrongAnswer() {
        val draws = mGame.wrongAnswer()
        mView.setDrawScore(draws.toString())
        setWord()
    }

    override fun onSecondElapsed(time: Int) {
        val timeStr = TimeFormatter().formatTimeStr(time)
        mView.changeTimerValue(timeStr)
        changeProgressBarValue(time)
    }

    private fun changeProgressBarValue(time: Int) {
        mView.changeProgressBarValue(mGame.getRoundLength() - time)
    }

    override fun onRoundEnded() {
        hideOptionItem()
        mView.changeTimerValue("0")
        mView.openEndRoundDialog(mGame.teamsArrayList)
    }

    override fun onTeamSelected(team: Team) {

    }

    override fun onNoneSelected() {

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