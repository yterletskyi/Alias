package yterletskyi.alias.settingsScreen

import yterletskyi.alias.gameScreen.model.GamePreferences

/**
 * Created by yterletskyi on 12.11.16.
 */
interface SettingsView {

    fun showSetupTeamsScreen()

    fun setTimeSeek(position: Int)

    fun setScoreSeek(position: Int)

    fun setTimeText(time: String)

    fun setScoreText(scores: String)

    fun getEndTime(): String

    fun getEndScore(): Int

    fun getGamePreferences(): GamePreferences

}