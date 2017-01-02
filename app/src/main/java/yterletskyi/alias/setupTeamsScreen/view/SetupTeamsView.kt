package yterletskyi.alias.setupTeamsScreen.view

import yterletskyi.alias.BaseView
import yterletskyi.alias.roundScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.presenter.SetupTeamAdapter

/**
 * Created by yterletskyi on 21.11.16.
 */
interface SetupTeamsView : BaseView {

    fun setupRecyclerView(adapter: SetupTeamAdapter)

    fun showAddTeamDialog(addTeamDialog: AddTeamDialog)

    fun getGamePreferences(): GamePreferences

}