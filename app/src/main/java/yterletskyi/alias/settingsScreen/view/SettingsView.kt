package yterletskyi.alias.settingsScreen.view

/**
 * Created by yterletskyi on 12.11.16.
 */
interface SettingsView {

    fun showSetupTeamsScreen()

    fun setTimeSeek(minutes: Int, seconds: Int)

    fun setScoreSeek(scores: Int)

    fun setTimeText(time: String)

    fun setScoreText(scores: String)

    fun getEndTime(): Int

    fun getEndScore(): Int

}