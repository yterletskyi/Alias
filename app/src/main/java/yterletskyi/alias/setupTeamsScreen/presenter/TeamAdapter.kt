package yterletskyi.alias.setupTeamsScreen.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import yterletskyi.alias.AutoColorTextView
import yterletskyi.alias.R
import yterletskyi.alias.roundScreen.model.Team


/**
 * Created by yterletskyi on 21.11.16.
 */
class TeamAdapter(val mTeams: MutableList<Team>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_complex, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder?, position: Int) {
        val team = mTeams[position]

        holder!!.nameTxtView.text = team.name
        holder.winsTxtView.text = (team.winScores - team.drawScores).toString()
    }

    override fun getItemCount(): Int {
        return mTeams.size
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.text_wins)
        lateinit var winsTxtView: AutoColorTextView

        @BindView(R.id.text_team_complex_name)
        lateinit var nameTxtView: TextView

        @BindView(R.id.text_team_complex_undo)
        lateinit var mUndoTxtView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

}