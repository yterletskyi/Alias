package yterletskyi.alias.gameScreen.view

import android.support.annotation.IdRes
import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 13.11.16.
 */
interface GameView {

    fun showSnackbar(descrResId: Int, actionResId: Int)

    fun setupActionBarTitle(teamName: String)

    fun setWord(word: String, @IdRes animId: Int)

    fun enableButtons()

    fun disableButtons()

    fun changeTimerValue(time: String)

    fun setDrawScore(draws: String)

    fun setWinScore(wins: String)

    fun openEndRoundDialog(teamsArrayList: MutableList<Team>)

    fun showOptionItem()

    fun hideOptionItem()

}