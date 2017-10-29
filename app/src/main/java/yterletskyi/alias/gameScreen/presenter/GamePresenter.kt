package yterletskyi.alias.gameScreen.presenter

import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.GameContract
import yterletskyi.alias.gameScreen.model.Game
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.util.TimeFormatter

/**
 * Created by yterletskyi on 13.11.16.
 */
class GamePresenter(private val mView: GameContract.View) : GameContract.Presenter {

    private lateinit var mGame: Game

    override fun start() {
        mGame = Game(mView.getGamePreferences(), mView.getResArray(R.array.words))
        mGame.onRoundEndListener = this
        mView.showSnackbar(R.string.tap_to_start, R.string.start)
        mView.setupActionBarTitle(mGame.getCurrentTeam().name)
        val roundLength = mGame.getRoundLength()
        mView.setMaxTimeProgressBarValue(roundLength)
        val roundLengthStr = TimeFormatter().formatTimeStr(roundLength)
        mView.setTimerValue(roundLengthStr)
    }

    override fun startGame() {
        setWord()
        enableUi()
        showPauseButton()
        mGame.start()
    }

    private fun hidePauseButton() {
        mView.hideOptionItem()
    }

    private fun showPauseButton() {
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

    override fun answerCorrect() {
        val wins = mGame.correctAnswer()
        mView.setWinScore(wins.toString())
        setWord()
    }

    override fun answerWrong() {
        val draws = mGame.wrongAnswer()
        mView.setDrawScore(draws.toString())
        setWord()
    }

    override fun onSecondElapsed(time: Int) {
        val timeStr = TimeFormatter().formatTimeStr(time)
        mView.setTimerValue(timeStr)
        setProgressBarValue(time)
    }

    private fun setProgressBarValue(time: Int) {
        mView.changeProgressBarValue(mGame.getRoundLength() - time)
    }

    override fun onRoundEnded() {
        hidePauseButton()
        disableUi()
        mView.setTimerValue("0")
        mView.openEndRoundDialog(mGame.teamsArrayList)
    }

    override fun onTeamSelected(team: Team) {
        team.winScores++
    }

    override fun onNoneSelected() {

    }

    override fun pause() {
        disableUi()
        mGame.pause()
    }

    override fun resume() {
        enableUi()
        mGame.resume()
    }

    override fun stop() {
        mGame.stop()
    }

}