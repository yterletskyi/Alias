package yterletskyi.alias.setupTeamsScreen.presenter

import android.support.v7.widget.RecyclerView

/**
 * Created by yterletskyi on 25.11.16.
 */
interface ItemTouchHelperAdapter {

    fun onItemMoved(fromPosition: Int, toPosition: Int)

    fun onItemDismissed(position: Int)

    fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, adapterPosition: Int)

    fun isItemViewSwipeEnabled(): Boolean

}