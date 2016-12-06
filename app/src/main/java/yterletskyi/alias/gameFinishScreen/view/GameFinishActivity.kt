package yterletskyi.alias.gameFinishScreen.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.BaseActivity
import yterletskyi.alias.R
import yterletskyi.alias.gameFinishScreen.presenter.GameFinishPresenter
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

class GameFinishActivity : BaseActivity(), GameFinishView {

    @BindView(R.id.text_cur_team_wins)
    lateinit var mCurrentTeamWinsTxtView: TextView

    @BindView(R.id.text_team_name_current)
    lateinit var mCurrentTeamNameTxtView: TextView

    @BindView(R.id.recycler_view_teams)
    lateinit var mTeamsRecyclerView: RecyclerView

    private val mPresenter = GameFinishPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_finish)
        ButterKnife.bind(this)
        mPresenter.onCreate()
    }

    override fun setTextViewText(text: String) {
        mCurrentTeamNameTxtView.text = text
    }

    override fun setupTeamsRecyclerView(adapter: TeamAdapter) {
        mTeamsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mTeamsRecyclerView.adapter = adapter
    }

    override fun setScores(winScores: Int) {
        mCurrentTeamWinsTxtView.text = winScores.toString()
    }

    @OnClick(R.id.btn_to_menu)
    fun onToMenuBtnClicked() {
        mPresenter.toMenu()
    }

    override fun close() {
        finish()
    }
}
