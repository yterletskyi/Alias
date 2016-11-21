package yterletskyi.alias.gameScreen.view.EndRoundDialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 15.11.16.
 */
class SimpleTeamsAdapter(private val mContext: Context, private val mTeamsList: MutableList<Team>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(mContext)
        var holder = TeamSimpleViewHolder()
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.item_team_simple, null)
            holder.teamNameTxtView = view.findViewById(R.id.text_team_simple_name) as TextView
            holder.teamWinsTxtView = view.findViewById(R.id.text_team_simple_wins) as TextView
            holder.teamDrawsTxtView = view.findViewById(R.id.text_team_simple_draws) as TextView
            view.tag = holder
        } else {
            holder = view.tag as TeamSimpleViewHolder
        }

        val team = mTeamsList[position]

        holder.teamNameTxtView!!.text = team.name
        holder.teamWinsTxtView!!.text = team.winScores.toString()
        holder.teamDrawsTxtView!!.text = team.drawScores.toString()


        return view!!
    }

    override fun getItem(position: Int): Any {
        return mTeamsList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mTeamsList.size
    }

    class TeamSimpleViewHolder {
        var teamNameTxtView: TextView? = null
        var teamWinsTxtView: TextView? = null
        var teamDrawsTxtView: TextView? = null

    }

}