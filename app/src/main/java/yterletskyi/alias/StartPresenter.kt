package yterletskyi.alias

/**
 * Created by yterletskyi on 09.11.16.
 */

class StartPresenter(private val mView: StartView) {

    fun onGameBtnClicked() {
        mView.showGame()
    }

    fun onSettingsBtnClicked() {
        mView.showSettings()
    }


}
