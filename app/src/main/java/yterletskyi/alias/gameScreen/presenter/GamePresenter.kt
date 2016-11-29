package yterletskyi.alias.gameScreen.presenter

import android.content.Intent
import android.os.Bundle
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
class GamePresenter(val mView: GameView) : OnRoundTimeListener, OnEndRoundTeamSelectListener {

    private lateinit var mGame: Game

    fun onCreate(data: Intent) {
        mGame = Game(mView.getGamePreferences(), mView.getResArray(R.array.words), data.getIntExtra("CurrentTeamIndex", 0))
        mGame.onRoundEndListener = this
        mView.showSnackbar(R.string.tap_to_start, R.string.start)
        mView.setupActionBarTitle(mGame.getCurrentTeam().name)
        val roundLength = mGame.getRoundLength()
        mView.setMaxTimeProgressBarValue(roundLength)
        val roundLengthStr = TimeFormatter().formatTimeStr(roundLength)
        mView.setTimerValue(roundLengthStr)
    }

    fun startGame() {
        setWord()
        enableUi()
        showPauseButton()
        mGame.start()
    }

    fun hidePauseButton() {
        mView.hideOptionItem()
    }

    fun showPauseButton() {
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

    fun answerCorrect() {
        val wins = mGame.correctAnswer()
        mView.setWinScore(wins.toString())
        setWord()
    }

    fun answerWrong() {
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
        // check if score < endScore
        hidePauseButton()
        disableUi()
        mView.setTimerValue("0")
        mView.openEndRoundDialog(mGame.teamsArrayList)
    }

    override fun onTeamSelected(team: Team) {
        team.winScores++
        saveTeams()
        startEndRoundActivity()
    }

    override fun onNoneSelected() {
        saveTeams()
        startEndRoundActivity()
    }

    private fun startEndRoundActivity() {
        val data = Bundle()
        data.putInt("CurrentTeamIndex", mGame.getCurrentTeamIndex())
        mView.showEndRoundActivity(data)
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

    fun saveTeams() {
        val teamSaver = mView.getGamePreferences().getTeamSaver()
        teamSaver.saveTeams(mGame.teamsArrayList)
    }
}