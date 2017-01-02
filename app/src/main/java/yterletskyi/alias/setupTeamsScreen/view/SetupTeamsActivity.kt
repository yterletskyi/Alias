package yterletskyi.alias.setupTeamsScreen.view

import android.app.Activity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.AliasApp
import yterletskyi.alias.R
import yterletskyi.alias.roundScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.model.SimpleItemTouchHelperCallback
import yterletskyi.alias.setupTeamsScreen.presenter.SetupTeamAdapter
import yterletskyi.alias.setupTeamsScreen.presenter.SetupTeamsPresenter

class SetupTeamsActivity : AppCompatActivity(), SetupTeamsView {

    val mPresenter = SetupTeamsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup_teams)
        ButterKnife.bind(this)
        mPresenter.onCreate()
    }

    override fun setupRecyclerView(adapter: SetupTeamAdapter) {
        val recyclerView = findViewById(R.id.recycler_view_teams) as RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val callback = SimpleItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    override fun getGamePreferences(): GamePreferences {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        return GamePreferences(sharedPrefs)
    }

    @OnClick(R.id.btn_add_team)
    fun addTeam() {
        mPresenter.addTeam()
    }

    override fun showAddTeamDialog(addTeamDialog: AddTeamDialog) {
        addTeamDialog.show(supportFragmentManager, "addTeamDialog")
    }

    override fun getAliasApp(): AliasApp {
        return application as AliasApp
    }

    override fun showActivity(activity: Class<out Activity>, finishThis: Boolean) {
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}
