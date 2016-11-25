package yterletskyi.alias.setupTeamsScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import butterknife.ButterKnife
import yterletskyi.alias.R
import yterletskyi.alias.setupTeamsScreen.presenter.SetupTeamsPresenter
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

class SetupTeamsActivity : AppCompatActivity(), SetupTeamsView {

    val mPresenter = SetupTeamsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup_teams)
        ButterKnife.bind(this)
        mPresenter.onCreate(this)
    }

    override fun setupRecyclerView(adapter: TeamAdapter, layoutManager: LinearLayoutManager, touchHelper: ItemTouchHelper) {
        val recyclerView = findViewById(R.id.recycler_view_teams) as RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        touchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onDestroy() {
        mPresenter.onDestroy(this)
        super.onDestroy()
    }
}
