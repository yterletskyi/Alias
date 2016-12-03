package yterletskyi.alias.setupTeamsScreen.presenter

import yterletskyi.alias.roundScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.model.OnAddTeamListener
import yterletskyi.alias.setupTeamsScreen.view.AddTeamDialog
import yterletskyi.alias.setupTeamsScreen.view.SetupTeamsView


/**
 * Created by yterletskyi on 21.11.16.
 */
class SetupTeamsPresenter(val mView: SetupTeamsView) : OnAddTeamListener {

    private var mAdapter: SetupTeamAdapter? = null
    private var mTeams: MutableList<Team>? = null

    fun onCreate() {
        getTeams()
        populateTeamsRecyclerView()
    }

    private fun getTeams() {
        val teamSaver = mView.getGamePreferences().getTeamSaver()
        mTeams = teamSaver.getTeams()
    }

    private fun populateTeamsRecyclerView() {
        mAdapter = SetupTeamAdapter(mTeams!!)
        mView.setupRecyclerView(mAdapter!!)
    }

    fun onDestroy() {
        saveTeams()
    }

    private fun saveTeams() {
        val teamSaver = mView.getGamePreferences().getTeamSaver()
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
        mAdapter!!.notifyItemInserted(mAdapter!!.itemCount)
    }
}