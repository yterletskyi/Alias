package yterletskyi.alias.setupTeamsScreen.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.view.SetupTeamsView

/**
 * Created by yterletskyi on 21.11.16.
 */
class SetupTeamsPresenter(val mView: SetupTeamsView) {

    fun onCreate(context: Context) {
        populateTeamsRecyclerView(context)
    }

    private fun populateTeamsRecyclerView(context: Context) {
        val adapter = TeamAdapter(mutableListOf(Team("Team1", 0, 0), Team("Team2", 0, 0), Team("Team3", 0, 0), Team("Team4", 0, 0)))
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mView.setupRecyclerView(adapter, layoutManager)
    }


}