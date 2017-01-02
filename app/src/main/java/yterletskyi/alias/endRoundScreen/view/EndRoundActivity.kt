package yterletskyi.alias.endRoundScreen.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.ActivityStarter
import yterletskyi.alias.AliasApp
import yterletskyi.alias.R
import yterletskyi.alias.endRoundScreen.presenter.EndRoundPresenter
import yterletskyi.alias.roundScreen.view.RoundActivity
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

class EndRoundActivity : AppCompatActivity(), EndRoundView {

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

    override fun setupTeamsRecyclerView(adapter: TeamAdapter) {
        mTeamsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mTeamsRecyclerView.adapter = adapter
    }

    override fun getAliasApp(): AliasApp {
        return application as AliasApp
    }

    override fun showActivity(activity: Class<out Activity>, finishThis: Boolean) {
        ActivityStarter().start(this, RoundActivity::class.java, true)
    }
}
