package yterletskyi.alias.setupTeamsScreen.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.gameScreen.model.TeamSaver
import yterletskyi.alias.setupTeamsScreen.model.SimpleItemTouchHelperCallback
import yterletskyi.alias.setupTeamsScreen.view.SetupTeamsView


/**
 * Created by yterletskyi on 21.11.16.
 */
class SetupTeamsPresenter(val mView: SetupTeamsView) {

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
        val adapter = TeamAdapter(mTeams!!)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val callback = SimpleItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        mView.setupRecyclerView(adapter, layoutManager, touchHelper)
    }

    fun onDestroy(context: Context) {
        saveTeams(context)
    }

    private fun saveTeams(context: Context) {
        val teamSaver = TeamSaver(context)
        teamSaver.saveTeams(mTeams!!)
    }

    fun addTeam() {

    }
}