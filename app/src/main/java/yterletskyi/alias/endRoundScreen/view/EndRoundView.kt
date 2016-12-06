package yterletskyi.alias.endRoundScreen.view

import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
interface EndRoundView {

    fun setupTeamsRecyclerView(adapter: TeamAdapter)

    fun startGameActivity()

    fun getGame(): Game
}