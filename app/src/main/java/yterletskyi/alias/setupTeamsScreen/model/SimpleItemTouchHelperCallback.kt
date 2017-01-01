package yterletskyi.alias.setupTeamsScreen.model

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import yterletskyi.alias.setupTeamsScreen.presenter.ItemTouchHelperAdapter


/**
 * Created by yterletskyi on 25.11.16.
 */
class SimpleItemTouchHelperCallback(val mAdapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        mAdapter.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        mAdapter.onItemDismissed(viewHolder!!.adapterPosition)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return mAdapter.isItemViewSwipeEnabled()
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }
}