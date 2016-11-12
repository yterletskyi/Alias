package yterletskyi.alias.settingsScreen.presenter

import android.content.Context
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
        mView.setEndTime(minutes, seconds)
    }

    private fun setupGameEndScore() {
        val score = mSharedPreferenceManager.getGameFinishScore()
        mView.setEndScore(score)
    }

    private fun saveEndTime() {
        val secs = mView.getEndTime()
        mSharedPreferenceManager.saveGameLengthTime(secs)
    }

    private fun saveEndScore() {
        val score = mView.getEndScore()
        mSharedPreferenceManager.saveGameFinishScore(score)
    }

}