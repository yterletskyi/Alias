package yterletskyi.alias.gameScreen

import android.support.annotation.AnimRes
import android.support.annotation.ArrayRes
import yterletskyi.alias.BasePresenter
import yterletskyi.alias.BaseView
import yterletskyi.alias.gameScreen.model.GamePreferences
import yterletskyi.alias.gameScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.gameScreen.model.OnRoundTimeListener
import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by Yura T on 10/29/2017.
 */
interface GameContract {

    interface View : BaseView<Presenter> {
        fun showSnackbar(descrResId: Int, actionResId: Int)

        fun setupActionBarTitle(teamName: String)

        fun setWord(word: String, @AnimRes animId: Int)

        fun enableButtons()

        fun disableButtons()

        fun setTimerValue(time: String)

        fun setDrawScore(draws: String)

        fun setWinScore(wins: String)

        fun openEndRoundDialog(teamsArrayList: MutableList<Team>)

        fun showOptionItem()

        fun hideOptionItem()

        fun setMaxTimeProgressBarValue(value: Int)

        fun changeProgressBarValue(time: Int)

        fun getResArray(@ArrayRes resId: Int): Array<String>

        fun getGamePreferences(): GamePreferences
    }

    interface Presenter : BasePresenter, OnRoundTimeListener, OnEndRoundTeamSelectListener {

        fun stop()

        fun pause()

        fun resume()

        fun answerCorrect()

        fun answerWrong()

        fun startGame()

    }

}