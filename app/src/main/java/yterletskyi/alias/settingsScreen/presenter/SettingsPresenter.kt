package yterletskyi.alias.settingsScreen.presenter

import android.content.Context
import android.widget.SeekBar
import yterletskyi.alias.R
import yterletskyi.alias.SharedPreferencesManager
import yterletskyi.alias.settingsScreen.view.SettingsView

/**
 * Created by yterletskyi on 12.11.16.
 */
class SettingsPresenter(val mView: SettingsView) {

    val SCORE_STEP: Int = 10
    val TIME_STEP: Int = 15

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
        setupTime();
        setupGameEndScore();
    }

    private fun setupTime() {
        val marks = mSharedPreferenceManager.getGameLengthTime()
        val totalSeconds = (marks + 1) * TIME_STEP
        val minutes = totalSeconds / 60
        val seconds = totalSeconds - minutes * 60
        val time = composeTimeStr(minutes, seconds)
        mView.setTimeText(time)
        mView.setTimeSeek(marks)
    }

    private fun composeTimeStr(min: Int, sec: Int): String {
        var secStr = sec.toString()
        if (sec < 10) {
            secStr = "0" + secStr
        }
        return min.toString() + ":" + secStr
    }

    private fun setupGameEndScore() {
        val score = mSharedPreferenceManager.getGameFinishScore()
        val scoreStr = ((score + 1) * SCORE_STEP).toString()
        mView.setScoreSeek(score)
        mView.setScoreText(scoreStr)
    }

    private fun saveEndTime() {
        val time = mView.getEndTime()
        val secs = timeToSeconds(time);
        val marks = secs / TIME_STEP - 1
        mSharedPreferenceManager.saveGameLengthTime(marks)
    }

    private fun timeToSeconds(time: String): Int {
        val minutes = time.substring(0, time.indexOf(":"))
        val seconds = time.substring(time.indexOf(":") + 1, time.length)
        return minutes.toInt() * 60 + seconds.toInt()
    }

    private fun saveEndScore() {
        val score = mView.getEndScore()
        val marks = (score - 1) / SCORE_STEP
        mSharedPreferenceManager.saveGameFinishScore(marks)
    }

    fun onSeekBarValueChanged(seekBar: SeekBar?, progress: Int) {
        if (seekBar!!.id == R.id.seek_time) {
            val secs = (progress + 1) * TIME_STEP;
            val minutes = secs / 60
            val seconds = secs - minutes * 60
            val time = composeTimeStr(minutes, seconds)
            mView.setTimeText(time)
        } else {
            val text = ((progress + 1) * SCORE_STEP).toString()
            mView.setScoreText(text)
        }
    }
}