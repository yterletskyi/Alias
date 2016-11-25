package yterletskyi.alias.setupTeamsScreen.model

import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 25.11.16.
 */
interface OnAddTeamListener {
    fun onTeamAdded(newTeam: Team)
}