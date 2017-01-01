package yterletskyi.alias.roundScreen.view.endRoundDialog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import yterletskyi.alias.R
import yterletskyi.alias.roundScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.roundScreen.model.Team

/**
 * Created by yterletskyi on 15.11.16.
 */
class SimpleTeamsAdapter(private val mTeamsList: MutableList<Team>, private val mCurrentTeamIndex: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    lateinit var onEndRoundTeamSelectListener: OnEndRoundTeamSelectListener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val holder: RecyclerView.ViewHolder
        if (viewType == 1) {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_current_simple, parent, false)
            holder = TeamSimpleCurrentViewHolder(view)
            holder.rootRelativeLayout.setOnClickListener {
                onEndRoundTeamSelectListener.onTeamSelected(mTeamsList[holder.adapterPosition])
            }
        } else if (viewType == 2) {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_simple_none, parent, false)
            holder = NoneTeamSimpleViewHolder(view)
            holder.rootLinearLayout.setOnClickListener {
                onEndRoundTeamSelectListener.onNoneSelected()
            }
        } else {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_simple, parent, false)
            holder = TeamSimpleViewHolder(view)
            holder.rootRelativeLayout.setOnClickListener {
                onEndRoundTeamSelectListener.onTeamSelected(mTeamsList[holder.adapterPosition])
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewType = holder!!.itemViewType
        if (viewType == 1) {
            val team = mTeamsList[position]
            (holder as TeamSimpleCurrentViewHolder).teamNameTxtView.text = team.name
            holder.teamWinsTxtView.text = team.winScores.toString()
            holder.teamDrawsTxtView.text = team.drawScores.toString()
        } else if (viewType == 3) {
            val team = mTeamsList[position]
            (holder as TeamSimpleViewHolder).teamNameTxtView.text = team.name
            holder.teamScoreTxtView.text = (team.winScores - team.drawScores).toString()
        }
    }

    override fun getItemCount(): Int {
        return mTeamsList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == mCurrentTeamIndex) { // current simple
            return 1
        } else if (position == mTeamsList.size) { // none
            return 2
        } else { // simple
            return 3
        }
    }

    class TeamSimpleCurrentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.relative_team_simple_root)
        lateinit var rootRelativeLayout: RelativeLayout

        @BindView(R.id.text_team_simple_name)
        lateinit var teamNameTxtView: TextView

        @BindView(R.id.text_team_simple_wins)
        lateinit var teamWinsTxtView: TextView

        @BindView(R.id.text_team_simple_draws)
        lateinit var teamDrawsTxtView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    class TeamSimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.relative_team_simple_root)
        lateinit var rootRelativeLayout: RelativeLayout

        @BindView(R.id.text_team_simple_name)
        lateinit var teamNameTxtView: TextView

        @BindView(R.id.text_team_simple_score)
        lateinit var teamScoreTxtView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    class NoneTeamSimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.linear_none_root)
        lateinit var rootLinearLayout: LinearLayout

        @BindView(R.id.text_team_simple_none)
        lateinit var noneTxtView: TextView

        init {
            ButterKnife.bind(this, view)
        }

    }
}