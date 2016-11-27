package yterletskyi.alias.endRoundScreen.view

import yterletskyi.alias.gameScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
interface EndRoundView {

    fun setTextViewText(text: String)

    fun setupTeamsRecyclerView(adapter: TeamAdapter)

    fun getGamePreferences(): GamePreferences

    fun setWins(winScores: Int)

    fun setDraws(drawScores: Int)

}