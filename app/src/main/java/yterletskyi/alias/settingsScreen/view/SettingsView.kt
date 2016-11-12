package yterletskyi.alias.settingsScreen.view

/**
 * Created by yterletskyi on 12.11.16.
 */
interface SettingsView {

    fun showSetupTeamsScreen()

    fun save()

    fun setEndTime(minutes: Int, seconds: Int)

    fun setEndScore(scores: Int)

    fun getEndTime(): Int

    fun getEndScore(): Int

}