package yterletskyi.alias.endRoundScreen.presenter

import yterletskyi.alias.endRoundScreen.view.EndRoundView
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
class EndRoundPresenter(private val mView: EndRoundView) {

    private lateinit var mGame: Game

    fun onCreate() {
        mGame = mView.getGame()
        setupTeamsRecyclerView()
    }

    private fun setupTeamsRecyclerView() {
        val adapter = TeamAdapter(mGame.teamManager.teams)
        mView.setupTeamsRecyclerView(adapter)
    }

    fun nextRound() {
        mGame.teamManager.nextTeam()
        showGameActivity()
    }

    private fun showGameActivity() {
        mView.startGameActivity()
    }
}