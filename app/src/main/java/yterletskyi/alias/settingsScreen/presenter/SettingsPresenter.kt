package yterletskyi.alias.settingsScreen.presenter

import yterletskyi.alias.settingsScreen.view.SettingsView

/**
 * Created by yterletskyi on 12.11.16.
 */
class SettingsPresenter(val mView: SettingsView) {

    fun save() {
        mView.save()
    }

    fun showSetupTeamsScreen() {
        mView.showSetupTeamsScreen()
    }


}