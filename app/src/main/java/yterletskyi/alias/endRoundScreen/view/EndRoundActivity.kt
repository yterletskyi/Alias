package yterletskyi.alias.endRoundScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.ActivityStarter
import yterletskyi.alias.AliasApp
import yterletskyi.alias.R
import yterletskyi.alias.endRoundScreen.presenter.EndRoundPresenter
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.view.RoundActivity
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

class EndRoundActivity : AppCompatActivity(), EndRoundView {

    @BindView(R.id.text_cur_team_wins)
    lateinit var mCurrentTeamWinsTxtView: TextView

    @BindView(R.id.text_cur_team_draws)
    lateinit var mCurrentTeamDrawsTxtView: TextView

    @BindView(R.id.text_team_name_current)
    lateinit var mCurrentTeamNameTxtView: TextView

    @BindView(R.id.recycler_view_teams)
    lateinit var mTeamsRecyclerView: RecyclerView

    private val mPresenter: EndRoundPresenter = EndRoundPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_round)
        ButterKnife.bind(this)
        mPresenter.onCreate()
    }

    @OnClick(R.id.btn_next_round)
    fun onNextRoundBtnClicked() {
        mPresenter.nextRound()
    }

    override fun setTextViewText(text: String) {
        mCurrentTeamNameTxtView.text = text
    }

    override fun setupTeamsRecyclerView(adapter: TeamAdapter) {
        mTeamsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mTeamsRecyclerView.adapter = adapter
    }

    override fun setWins(winScores: Int) {
        mCurrentTeamWinsTxtView.text = winScores.toString()
    }

    override fun setDraws(drawScores: Int) {
        mCurrentTeamDrawsTxtView.text = drawScores.toString()
    }

    override fun getGame(): Game {
        return (application as AliasApp).game
    }

    override fun startGameActivity() {
        ActivityStarter().start(this, RoundActivity::class.java, true)
    }
}
