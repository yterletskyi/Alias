package yterletskyi.alias.settingsScreen.presenter

import yterletskyi.alias.TimeFormatter
import yterletskyi.alias.roundScreen.model.Constants
import yterletskyi.alias.roundScreen.model.GameConfigs
import yterletskyi.alias.settingsScreen.view.SettingsView

/**
 * Created by yterletskyi on 12.11.16.
 */
class SettingsPresenter(private val mView: SettingsView) {

    private var mGameConfigs: GameConfigs? = null

    fun onCreate() {
        init()
        setupTime()
        setupGameEndScore()
    }

    private fun init() {
        mGameConfigs = mView.getAliasApp().game.gameConfigs
    }

    fun save() {
        mView.getAliasApp().saveGameConfigToPreferences()
    }

    fun showSetupTeamsScreen() {
        mView.showSetupTeamsScreen()
    }

    private fun setupTime() {
        val totalSeconds = mGameConfigs!!.roundLengthSec
        val time = TimeFormatter().secondsToString(totalSeconds)
        mView.setTimeText(time)
        mView.setTimeSeek(totalSeconds / Constants.TIME_STEP)
    }

    private fun setupGameEndScore() {
        val score = mGameConfigs!!.gameFinishScore
        mView.setScoreSeek(score / Constants.SCORE_STEP)
        mView.setScoreText(score.toString())
    }

    fun onSeekTimeValueChanged(progress: Int) {
        val secs = (progress + 1) * Constants.TIME_STEP
        mGameConfigs!!.roundLengthSec = secs
        val time = TimeFormatter().secondsToString(secs)
        mView.setTimeText(time)
    }

    fun onSeekScoreValueChanged(progress: Int) {
        val score = (progress + 1) * Constants.SCORE_STEP
        mGameConfigs!!.gameFinishScore = score
        mView.setScoreText(score.toString())
    }
}