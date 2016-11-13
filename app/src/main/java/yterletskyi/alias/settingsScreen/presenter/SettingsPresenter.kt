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
        setupGameFinishTime();
        setupGameEndScore();
    }

    private fun setupGameFinishTime() {
        val secs = mSharedPreferenceManager.getGameLengthTime()
        val minutes = secs / 60
        val seconds = secs % 60
        val time = composeTime(minutes, seconds)
        mView.setTimeText(time)
        mView.setTimeSeek(minutes, seconds)
    }

    private fun composeTime(min: Int, sec: Int): String {
        return min.toString() + ":" + sec.toString()
    }

    private fun setupGameEndScore() {
        val score = mSharedPreferenceManager.getGameFinishScore()
        mView.setScoreSeek(score)
        mView.setScoreText(score.toString())
    }

    private fun saveEndTime() {
        val secs = mView.getEndTime()
        mSharedPreferenceManager.saveGameLengthTime(secs)
    }

    private fun saveEndScore() {
        val score = mView.getEndScore()
        mSharedPreferenceManager.saveGameFinishScore(score)
    }

    fun onSeekBarValueChanged(seekBar: SeekBar?, progress: Int) {
        if (seekBar!!.id == R.id.seek_time) {
            mView.setTimeText(progress.toString())
        } else {
            mView.setScoreText(progress.toString())
        }
    }
}