package yterletskyi.alias.setupTeamsScreen.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.model.TeamSaver
import yterletskyi.alias.setupTeamsScreen.model.OnAddTeamListener
import yterletskyi.alias.setupTeamsScreen.model.SimpleItemTouchHelperCallback
import yterletskyi.alias.setupTeamsScreen.view.AddTeamDialog
import yterletskyi.alias.setupTeamsScreen.view.SetupTeamsView


/**
 * Created by yterletskyi on 21.11.16.
 */
class SetupTeamsPresenter(val mView: SetupTeamsView) : OnAddTeamListener {

    private var mAdapter: TeamAdapter? = null
    private var mTeams: MutableList<Team>? = null

    fun onCreate(context: Context) {
        getTeams(context)
        populateTeamsRecyclerView(context)
    }

    private fun getTeams(context: Context) {
        val teamSaver = TeamSaver(context)
        mTeams = teamSaver.getTeams()
    }

    private fun populateTeamsRecyclerView(context: Context) {
        mAdapter = TeamAdapter(mTeams!!)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val callback = SimpleItemTouchHelperCallback(mAdapter!!)
        val touchHelper = ItemTouchHelper(callback)
        mView.setupRecyclerView(mAdapter!!, layoutManager, touchHelper)
    }

    fun onDestroy(context: Context) {
        saveTeams(context)
    }

    private fun saveTeams(context: Context) {
        val teamSaver = TeamSaver(context)
        teamSaver.saveTeams(mTeams!!)
    }

    fun addTeam() {
        showAddTeamDialog()
    }

    private fun showAddTeamDialog() {
        val addTeamDialog = AddTeamDialog()
        addTeamDialog.onAddTeamListener = this
        mView.showAddTeamDialog(addTeamDialog)
    }

    override fun onTeamAdded(newTeam: Team) {
        mTeams!!.add(newTeam)
        val pos = mAdapter!!.itemCount
        mAdapter!!.notifyItemInserted(pos)
    }
}