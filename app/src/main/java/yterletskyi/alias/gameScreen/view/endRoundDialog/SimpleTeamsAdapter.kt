package yterletskyi.alias.gameScreen.view.endRoundDialog

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
import yterletskyi.alias.gameScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 15.11.16.
 */
class SimpleTeamsAdapter(private val mTeamsList: MutableList<Team>) : RecyclerView.Adapter<RecyclerView.ViewHolder?>(), View.OnClickListener {

    lateinit var onEndRoundTeamSelectListener: OnEndRoundTeamSelectListener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val holder: RecyclerView.ViewHolder
        if (viewType == 1) {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_simple, null)
            holder = TeamSimpleViewHolder(view)
            holder.rootRelativeLayout.setOnClickListener {
                onEndRoundTeamSelectListener.onTeamSelected(mTeamsList[holder.adapterPosition])
            }
        } else {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_team_simple_none, null)
            view.setOnClickListener(this)
            holder = NoneTeamSimpleViewHolder(view)
            holder.rootLinearLayout.setOnClickListener {
                onEndRoundTeamSelectListener.onNoneSelected()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewType = holder!!.itemViewType
        if (viewType == 1) {
            val team = mTeamsList[position]
            (holder as TeamSimpleViewHolder).teamNameTxtView.text = team.name
            holder.teamWinsTxtView.text = team.winScores.toString()
            holder.teamDrawsTxtView.text = team.drawScores.toString()
        }
    }

    override fun getItemCount(): Int {
        return mTeamsList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == mTeamsList.size) {
            return 2
        } else {
            return 1
        }
    }

    override fun onClick(v: View?) {

    }

    class TeamSimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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