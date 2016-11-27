package yterletskyi.alias.setupTeamsScreen.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Team
import java.util.*


/**
 * Created by yterletskyi on 21.11.16.
 */
class TeamAdapter(val mTeams: MutableList<Team>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_complex, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder?, position: Int) {
        val team = mTeams[position]

        holder!!.nameTxtView.text = team.name
        holder.winsTxtView.text = team.winScores.toString()
        holder.drawsTxtView.text = team.drawScores.toString()
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

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.text_wins)
        lateinit var winsTxtView: TextView

        @BindView(R.id.text_draws)
        lateinit var drawsTxtView: TextView

        @BindView(R.id.text_team_complex_name)
        lateinit var nameTxtView: TextView

        @BindView(R.id.text_team_complex_undo)
        lateinit var mUndoTxtView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

}