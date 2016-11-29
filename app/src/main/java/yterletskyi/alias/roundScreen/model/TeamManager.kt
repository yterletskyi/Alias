package yterletskyi.alias.roundScreen.model

/**
 * Created by yterletskyi on 29.11.16.
 */
class TeamManager(val teams: MutableList<Team>) {

    var currentTeamIndex = 0
    var currentTeam: Team = teams[currentTeamIndex]

    fun nextTeam() {
        currentTeamIndex++
        if (currentTeamIndex == teams.size) {
            currentTeamIndex = 0
        }
        currentTeam = teams[currentTeamIndex]
    }
}