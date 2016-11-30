package yterletskyi.alias.roundScreen.presenter

import yterletskyi.alias.R
import yterletskyi.alias.TimeFormatter
import yterletskyi.alias.endRoundScreen.view.EndRoundActivity
import yterletskyi.alias.gameFinishScreen.view.GameFinishActivity
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.roundScreen.model.OnRoundTimeListener
import yterletskyi.alias.roundScreen.model.Team
import yterletskyi.alias.roundScreen.view.RoundView

/**
 * Created by yterletskyi on 13.11.16.
 */
class RoundPresenter(val mView: RoundView) : OnRoundTimeListener, OnEndRoundTeamSelectListener {

    private lateinit var mGame: Game;

    fun onCreate() {
        mGame = mView.getGame()
        mGame.onRoundEndListener = this
        setupSnackbar()
        setupActionBar()
        setupProgressBarAndTimer()
    }

    private fun setupProgressBarAndTimer() {
        val roundLength = mGame.gameConfigs!!.roundLengthSec
        mView.setMaxTimeProgressBarValue(roundLength)
        val roundLengthStr = TimeFormatter().secondsToString(roundLength)
        mView.setTimerValue(roundLengthStr)
    }

    private fun setupActionBar() {
        mView.setupActionBarTitle(mGame.teamManager.currentTeam.name)
    }

    private fun setupSnackbar() {
        mView.showSnackbar(R.string.tap_to_start, R.string.start)
    }

    fun startGame() {
        setWord()
        enableUi()
        showPauseButton()
        mGame.startWithNewRound()
    }

    fun hidePauseButton() {
        mView.hideOptionItem()
    }

    fun showPauseButton() {
        mView.showOptionItem()
    }

    private fun setWord() {
        val word = mGame.words.nextWord()
        mView.setWord(word, R.anim.fade_in_animation)
    }

    private fun enableUi() {
        mView.enableButtons()
    }

    private fun disableUi() {
        mView.disableButtons()
    }

    fun answerCorrect() {
        val wins = mGame.answerCorrect()
        mView.setWinScore(wins.toString())
        setWord()
    }

    fun answerWrong() {
        val draws = mGame.answerWrong()
        mView.setDrawScore(draws.toString())
        setWord()
    }

    override fun onSecondElapsed(time: Int) {
        val timeStr = TimeFormatter().secondsToString(time)
        mView.setTimerValue(timeStr)
        setProgressBarValue(time)
    }

    private fun setProgressBarValue(time: Int) {
        mView.changeProgressBarValue(mGame.gameConfigs!!.roundLengthSec - time)
    }

    override fun onRoundEnded() {
        // check if score < endScore
        hidePauseButton()
        disableUi()
        mView.setTimerValue("0")
        mView.openEndRoundDialog(mGame.teamManager.teams)
    }

    override fun onTeamSelected(team: Team) {
        team.winScores++
        proceed()
    }

    override fun onNoneSelected() {
        proceed()
    }

    private fun proceed() {
        val finish = isItLastRound()
        if (finish) {
            mView.showScreen(GameFinishActivity::class.java)
        } else {
            mView.showScreen(EndRoundActivity::class.java)
        }
    }

    private fun isItLastRound(): Boolean {
        val finishScore = mGame.gameConfigs!!.gameFinishScore
        val maxScore = mGame.teamManager.getLeadingTeam().winScores
        return maxScore >= finishScore
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