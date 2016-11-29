package yterletskyi.alias.endRoundScreen.presenter

import android.content.Intent
import android.os.Bundle
import yterletskyi.alias.endRoundScreen.view.EndRoundView
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
class EndRoundPresenter(private val mView: EndRoundView) {

    private var mCurrentTeamIndex: Int = 0
    private lateinit var mTeams: MutableList<Team>

    fun onCreate(intent: Intent) {
        mTeams = mView.getGamePreferences().getTeamSaver().getTeams()
        mCurrentTeamIndex = intent.getIntExtra("CurrentTeamIndex", -1)
        setupTeamTxtView(mTeams[mCurrentTeamIndex])
        setupTeamsRecyclerView(mCurrentTeamIndex)
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
        mView.setWins(team.winScores)
        mView.setDraws(team.drawScores)
    }

    fun nextRound() {
        showGameActivity()
    }

    private fun showGameActivity() {
        val data = Bundle()
        data.putInt("CurrentTeamIndex", ++mCurrentTeamIndex)
        mView.startGameActivity(data)
    }


}