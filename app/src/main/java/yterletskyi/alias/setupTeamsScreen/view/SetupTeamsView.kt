package yterletskyi.alias.setupTeamsScreen.view

import android.support.v7.widget.LinearLayoutManager
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 21.11.16.
 */
interface SetupTeamsView {

    fun setupRecyclerView(adapter: TeamAdapter, layoutManager: LinearLayoutManager)

}