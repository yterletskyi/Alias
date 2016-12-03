package yterletskyi.alias.endRoundScreen.presenter

import yterletskyi.alias.endRoundScreen.view.EndRoundView
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
class EndRoundPresenter(private val mView: EndRoundView) {

    private lateinit var mGame: Game

    fun onCreate() {
        mGame = mView.getGame()
        setupTeamTxtView()
        setupTeamsRecyclerView()
    }

    private fun setupTeamsRecyclerView() {
        val teams = copyList()
        teams.remove(mGame.teamManager.currentTeam)
        val adapter = TeamAdapter(teams)
        mView.setupTeamsRecyclerView(adapter)
    }

    private fun copyList(): MutableList<Team> {
        val list: MutableList<Team> = mutableListOf()
        for (team in mGame.teamManager.teams) {
            list.add(team)
        }
        return list
    }

    private fun setupTeamTxtView() {
        val team = mGame.teamManager.currentTeam
        mView.setTextViewText(team.name)
        mView.setScores(team.winScores - team.drawScores)
    }

    fun nextRound() {
        showGameActivity()
    }

    private fun showGameActivity() {
        mView.startGameActivity()
    }


}