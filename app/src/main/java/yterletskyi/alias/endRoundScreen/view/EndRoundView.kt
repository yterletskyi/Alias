package yterletskyi.alias.endRoundScreen.view

import yterletskyi.alias.BaseView
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 27.11.16.
 */
interface EndRoundView : BaseView {

    fun setupTeamsRecyclerView(adapter: TeamAdapter)

}