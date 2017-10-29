package yterletskyi.alias.settingsScreen.presenter

import yterletskyi.alias.util.TimeFormatter
import yterletskyi.alias.gameScreen.model.Constants
import yterletskyi.alias.settingsScreen.view.SettingsView

/**
 * Created by yterletskyi on 12.11.16.
 */
// todo refactor this class
class SettingsPresenter(val mView: SettingsView) {

    fun save() {
        saveEndTime()
        saveEndScore()
    }

    fun showSetupTeamsScreen() {
        mView.showSetupTeamsScreen()
    }

    fun onCreate() {
        setupTime()
        setupGameEndScore()
    }

    private fun setupTime() {
        val gamePreferences = mView.getGamePreferences()
        val marks = gamePreferences.getRoundLengthTime()
        val totalSeconds = (marks + 1) * Constants.TIME_STEP
        val time = TimeFormatter().formatTimeStr(totalSeconds)
        mView.setTimeText(time)
        mView.setTimeSeek(marks)
    }

    private fun setupGameEndScore() {
        val gamePreferences = mView.getGamePreferences()
        val score = gamePreferences.getGameFinishScore()
        val scoreStr = ((score + 1) * Constants.SCORE_STEP).toString()
        mView.setScoreSeek(score)
        mView.setScoreText(scoreStr)
    }

    private fun saveEndTime() {
        val gamePreferences = mView.getGamePreferences()
        val time = mView.getEndTime()
        val secs = timeToSeconds(time)
        val marks = secs / Constants.TIME_STEP - 1
        gamePreferences.saveRoundLengthTime(marks)
    }

    private fun timeToSeconds(time: String): Int {
        val minutes = time.substring(0, time.indexOf(":"))
        val seconds = time.substring(time.indexOf(":") + 1, time.length)
        return minutes.toInt() * 60 + seconds.toInt()
    }

    private fun saveEndScore() {
        val gamePreferences = mView.getGamePreferences()
        val score = mView.getEndScore()
        val marks = (score - 1) / Constants.SCORE_STEP
        gamePreferences.saveGameFinishScore(marks)
    }

    fun onSeekTimeValueChanged(progress: Int) {
        val secs = (progress + 1) * Constants.TIME_STEP
        val time = TimeFormatter().formatTimeStr(secs)
        mView.setTimeText(time)
    }

    fun onSeekScoreValueChanged(progress: Int) {
        val text = ((progress + 1) * Constants.SCORE_STEP).toString()
        mView.setScoreText(text)
    }
}