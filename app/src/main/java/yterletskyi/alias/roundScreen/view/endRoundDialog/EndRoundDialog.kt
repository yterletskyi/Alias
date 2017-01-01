package yterletskyi.alias.roundScreen.view.endRoundDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import yterletskyi.alias.R
import yterletskyi.alias.roundScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.roundScreen.model.Team

/**
 * Created by yterletskyi on 15.11.16.
 */
class EndRoundDialog(context: Context) : Dialog(context), OnEndRoundTeamSelectListener {

    lateinit var teamSelectListener: OnEndRoundTeamSelectListener
    private var mTeamList: MutableList<Team>? = null
    private var mCurrentTeamIndex: Int = 0;

    constructor(context: Context, teams: MutableList<Team>, currentTeamIndex: Int) : this(context) {
        mTeamList = teams
        mCurrentTeamIndex = currentTeamIndex
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_end_round)
        setCancelable(false)
        setTitle(R.string.who_gets_the_word)

        val recyclerView = findViewById(R.id.recycler_teams_end_round) as RecyclerView

        val adapter = SimpleTeamsAdapter(mTeamList!!, mCurrentTeamIndex)
        adapter.onEndRoundTeamSelectListener = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTeamSelected(team: Team) {
        teamSelectListener.onTeamSelected(team)
        dismiss()
    }

    override fun onNoneSelected() {
        teamSelectListener.onNoneSelected()
        dismiss()
    }
}