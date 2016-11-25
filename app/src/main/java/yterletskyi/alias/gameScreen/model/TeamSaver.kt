package yterletskyi.alias.gameScreen.model

import android.content.Context
import yterletskyi.alias.SharedPreferencesManager

/**
 * Created by yterletskyi on 14.11.16.
 */
class TeamSaver(context: Context) {

    private val KEY_NAME: String = "team_name"
    private val KEY_WINS: String = "wins_name"
    private val KEY_DRAW: String = "draws_name"
    private val mPrefManager: SharedPreferencesManager = SharedPreferencesManager(context)

    fun saveTeams(teams: MutableList<Team>) {
        mPrefManager.saveTeamsCount(teams.size)
        for (i in 0..teams.size - 1) {
            saveTeam(teams[i], i)
        }
    }

    fun getTeams(): MutableList<Team> {
        val teamsCount = getTeamsCount()
        val teams: MutableList<Team> = mutableListOf()
        for (i in 0..teamsCount - 1) {
            val team = getTeam(i)
            teams.add(team)
        }
        return teams
    }

    private fun saveTeam(team: Team, index: Int) {
        mPrefManager.putString(team.name, KEY_NAME + index.toString())
        mPrefManager.putInt(team.winScores, KEY_WINS + index.toString())
        mPrefManager.putInt(team.drawScores, KEY_DRAW + index.toString())
    }

    private fun getTeamsCount(): Int {
        return mPrefManager.getTeamsCount()
    }

    private fun getTeam(i: Int): Team {
        val name = mPrefManager.getString(KEY_NAME + i.toString())
        val wins = mPrefManager.getInt(KEY_WINS + i.toString())
        val draws = mPrefManager.getInt(KEY_DRAW + i.toString())
        return Team(name, wins, draws)
    }
}