package yterletskyi.alias.startScreen

import yterletskyi.alias.BasePresenter
import yterletskyi.alias.BaseView

/**
 * Created by Yura T on 10/29/2017.
 */
interface StartContract {

    interface View : BaseView<Presenter> {

        fun showGame()

        fun showSettings()

    }

    interface Presenter : BasePresenter {

        fun onGameBtnClicked()

        fun onSettingsBtnClicked();

    }

}