package yterletskyi.alias.setupTeamsScreen.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import yterletskyi.alias.R
import yterletskyi.alias.roundScreen.model.Team
import java.util.*


/**
 * Created by yterletskyi on 03.12.16.
 */
class SetupTeamAdapter(val mTeams: MutableList<Team>) : RecyclerView.Adapter<SetupTeamAdapter.SetupTeamViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SetupTeamViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_setup_team, parent, false)
        return SetupTeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetupTeamViewHolder?, position: Int) {
        val team = mTeams[position]
        holder!!.nameTxtView.text = team.name
    }

    override fun getItemCount(): Int {
        return mTeams.size
    }

    override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, adapterPosition: Int) {
        // leave it to better times
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                Collections.swap(mTeams, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mTeams, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismissed(position: Int) {
        mTeams.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return mTeams.size > 2
    }

    class SetupTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.text_setup_team__name)
        lateinit var nameTxtView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

}