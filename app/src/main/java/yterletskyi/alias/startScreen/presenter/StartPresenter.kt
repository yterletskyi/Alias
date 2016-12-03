package yterletskyi.alias.startScreen.presenter

import yterletskyi.alias.roundScreen.view.RoundActivity
import yterletskyi.alias.settingsScreen.view.SettingsActivity
import yterletskyi.alias.startScreen.view.StartView

/**
 * Created by yterletskyi on 09.11.16.
 */

class StartPresenter(private val mView: StartView) {

    fun onGameBtnClicked() {
        mView.showActivity(RoundActivity::class.java, false)
    }

    fun onSettingsBtnClicked() {
        mView.showActivity(SettingsActivity::class.java, false)
    }

    fun onStart() {
        val game = mView.getAliasApp().game
        game.teamManager.clearScores()
    }

}
