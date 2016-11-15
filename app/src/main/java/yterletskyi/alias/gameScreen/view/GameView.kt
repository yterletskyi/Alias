package yterletskyi.alias.gameScreen.view

import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 13.11.16.
 */
interface GameView {

    fun showSnackbar(descrResId: Int, actionResId: Int)

    fun setupActionBar(teamName: String)

    fun setWord(word: String)

    fun enableButtons()

    fun disableButtons()

    fun changeTimerValue(time: Int)

    fun setDrawScore(draws: Int)

    fun setWinScore(wins: Int)

    fun openEndRoundDialog(teamsArrayList: MutableList<Team>)

}