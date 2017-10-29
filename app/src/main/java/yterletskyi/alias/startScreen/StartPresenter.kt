package yterletskyi.alias.startScreen

/**
 * Created by yterletskyi on 09.11.16.
 */

class StartPresenter(private val mView: StartContract.View) : StartContract.Presenter {

    init {
        mView.presenter = this
    }

    override fun start() {
    }

    override fun onGameBtnClicked() {
        mView.showGame()
    }

    override fun onSettingsBtnClicked() {
        mView.showSettings()
    }

}
