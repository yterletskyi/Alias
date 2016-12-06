package yterletskyi.alias.gameFinishScreen.view

import yterletskyi.alias.BaseView
import yterletskyi.alias.setupTeamsScreen.presenter.TeamAdapter

/**
 * Created by yterletskyi on 06.12.16.
 */
interface GameFinishView : BaseView {

    fun setTextViewText(text: String)

    fun setupTeamsRecyclerView(adapter: TeamAdapter)

    fun setScores(winScores: Int)

    fun close()

}