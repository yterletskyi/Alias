package yterletskyi.alias.roundScreen.view

import android.app.Activity
import android.support.annotation.IdRes
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.model.Team

/**
 * Created by yterletskyi on 13.11.16.
 */
interface RoundView {

    fun showSnackbar(descrResId: Int, actionResId: Int)

    fun setupActionBarTitle(teamName: String)

    fun setWord(word: String, @IdRes animId: Int)

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

    fun getGame(): Game

    fun showScreen(screen: Class<out Activity>)
}