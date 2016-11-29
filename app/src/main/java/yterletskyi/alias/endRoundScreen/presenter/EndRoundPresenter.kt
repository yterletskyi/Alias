package yterletskyi.alias.endRoundScreen.presenter

import yterletskyi.alias.endRoundScreen.view.EndRoundView
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
class EndRoundPresenter(private val mView: EndRoundView) {

    private lateinit var mGame: Game;

    fun onCreate() {
        mGame = mView.getGame()

        setupTeamTxtView(mGame.teamManager.currentTeam)
        setupTeamsRecyclerView(mGame.teamManager.currentTeamIndex)
    }

    private fun setupTeamsRecyclerView(currentTeamIndex: Int) {
        val teams = copyList()
        teams.removeAt(currentTeamIndex)
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

    private fun setupTeamTxtView(team: Team) {
        mView.setTextViewText(team.name)
        mView.setWins(team.winScores)
        mView.setDraws(team.drawScores)
    }

    fun nextRound() {
        showGameActivity()
    }

    private fun showGameActivity() {
        mView.startGameActivity()
    }


}