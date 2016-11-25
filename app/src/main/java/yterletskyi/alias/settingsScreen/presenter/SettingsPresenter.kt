package yterletskyi.alias.settingsScreen.presenter

import android.content.Context
import android.widget.SeekBar
import yterletskyi.alias.R
import yterletskyi.alias.SharedPreferencesManager
import yterletskyi.alias.TimeFormatter
import yterletskyi.alias.gameScreen.model.Constants
import yterletskyi.alias.settingsScreen.view.SettingsView

/**
 * Created by yterletskyi on 12.11.16.
 */
// todo refactor this class
class SettingsPresenter(val mView: SettingsView) {

    var mSharedPreferenceManager: SharedPreferencesManager

    init {
        mSharedPreferenceManager = SharedPreferencesManager(mView as Context)
    }

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
        val marks = mSharedPreferenceManager.getRoundLengthTime()
        val totalSeconds = (marks + 1) * Constants.TIME_STEP
        val time = TimeFormatter().formatTimeStr(totalSeconds)
        mView.setTimeText(time)
        mView.setTimeSeek(marks)
    }

    private fun setupGameEndScore() {
        val score = mSharedPreferenceManager.getGameFinishScore()
        val scoreStr = ((score + 1) * Constants.SCORE_STEP).toString()
        mView.setScoreSeek(score)
        mView.setScoreText(scoreStr)
    }

    private fun saveEndTime() {
        val time = mView.getEndTime()
        val secs = timeToSeconds(time)
        val marks = secs / Constants.TIME_STEP - 1
        mSharedPreferenceManager.saveRoundLengthTime(marks)
    }

    private fun timeToSeconds(time: String): Int {
        val minutes = time.substring(0, time.indexOf(":"))
        val seconds = time.substring(time.indexOf(":") + 1, time.length)
        return minutes.toInt() * 60 + seconds.toInt()
    }

    private fun saveEndScore() {
        val score = mView.getEndScore()
        val marks = (score - 1) / Constants.SCORE_STEP
        mSharedPreferenceManager.saveGameFinishScore(marks)
    }

    fun onSeekBarValueChanged(seekBar: SeekBar?, progress: Int) {
        if (seekBar!!.id == R.id.seek_time) {
            val secs = (progress + 1) * Constants.TIME_STEP
            val time = TimeFormatter().formatTimeStr(secs)
            mView.setTimeText(time)
        } else {
            val text = ((progress + 1) * Constants.SCORE_STEP).toString()
            mView.setScoreText(text)
        }
    }
}