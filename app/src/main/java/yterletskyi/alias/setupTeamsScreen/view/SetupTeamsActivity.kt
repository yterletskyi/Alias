package yterletskyi.alias.setupTeamsScreen.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_setup_teams.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.model.SimpleItemTouchHelperCallback
import yterletskyi.alias.setupTeamsScreen.presenter.SetupTeamsPresenter
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

class SetupTeamsActivity : AppCompatActivity(), SetupTeamsView {

    val mPresenter = SetupTeamsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup_teams)
        btn_add_team.setOnClickListener({ mPresenter.addTeam() })
        mPresenter.onCreate()
    }

    override fun setupRecyclerView(adapter: TeamAdapter) {
        recycler_view_teams.adapter = adapter
        recycler_view_teams.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val callback = SimpleItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recycler_view_teams)
    }

    override fun getGamePreferences(): GamePreferences {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        return GamePreferences(sharedPrefs)
    }

    override fun showAddTeamDialog(addTeamDialog: AddTeamDialog) {
        addTeamDialog.show(supportFragmentManager, "addTeamDialog")
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}
