package yterletskyi.alias.endRoundScreen.presenter

import android.content.Intent
import yterletskyi.alias.endRoundScreen.view.EndRoundView
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
class EndRoundPresenter(private val mView: EndRoundView) {

    private lateinit var mTeams: MutableList<Team>

    fun onCreate(intent: Intent) {
        mTeams = mView.getGamePreferences().getTeamSaver().getTeams()
        val currentTeamIndex = intent.getIntExtra("CurrentTeamIndex", -1)
        setupTeamTxtView(mTeams[currentTeamIndex])
        setupTeamsRecyclerView(currentTeamIndex)
    }

    private fun setupTeamsRecyclerView(currentTeamIndex: Int) {
        val teams = copyList()
        teams.removeAt(currentTeamIndex)
        val adapter = TeamAdapter(teams)
        mView.setupTeamsRecyclerView(adapter)
    }

    private fun copyList(): MutableList<Team> {
        val list: MutableList<Team> = mutableListOf()
        for (team in mTeams) {
            list.add(team)
        }
        return list
    }

    private fun setupTeamTxtView(team: Team) {
        mView.setTextViewText(team.name)
    }

    fun nextRound() {

    }


}