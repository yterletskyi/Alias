package yterletskyi.alias.endRoundScreen.view

import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
interface EndRoundView {

    fun setTextViewText(text: String)

    fun setupTeamsRecyclerView(adapter: TeamAdapter)

    fun setWins(winScores: Int)

    fun setDraws(drawScores: Int)

    fun startGameActivity()

    fun getGame(): Game
}