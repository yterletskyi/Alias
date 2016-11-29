package yterletskyi.alias.setupTeamsScreen.view

import yterletskyi.alias.roundScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 21.11.16.
 */
interface SetupTeamsView {

    fun setupRecyclerView(adapter: TeamAdapter)

    fun showAddTeamDialog(addTeamDialog: AddTeamDialog)

    fun getGamePreferences(): GamePreferences

}