package yterletskyi.alias.roundScreen.model

/**
 * Created by yterletskyi on 29.11.16.
 */
class TeamManager(val teams: MutableList<Team>) {

    private var mCurrentTeamIndex = 0
    var currentTeam: Team = teams[mCurrentTeamIndex]

    fun nextTeam() {
        mCurrentTeamIndex++
        if (mCurrentTeamIndex == teams.size) {
            mCurrentTeamIndex = 0
        }
        currentTeam = teams[mCurrentTeamIndex]
    }

    fun getLeadingTeam(): Team {
        var bestTeam = teams[0]
        for (team in teams) {
            if (team.winScores > bestTeam.winScores) {
                bestTeam = team
            }
        }
        return bestTeam
    }

    fun clearScores() {
        for (team in teams) {
            team.drawScores = 0
            team.winScores = 0
        }
    }
}