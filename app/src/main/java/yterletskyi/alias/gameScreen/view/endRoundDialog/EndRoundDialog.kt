package yterletskyi.alias.gameScreen.view.endRoundDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_end_round.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.OnEndRoundTeamSelectListener
import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 15.11.16.
 */
class EndRoundDialog(context: Context) : Dialog(context), OnEndRoundTeamSelectListener {

    lateinit var teamSelectListener: OnEndRoundTeamSelectListener
    private var mTeamList: MutableList<Team>? = null

    constructor(context: Context, teams: MutableList<Team>) : this(context) {
        mTeamList = teams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_end_round)
        setCancelable(false)
        setTitle(R.string.who_gets_the_word)

        val adapter = SimpleTeamsAdapter(mTeamList!!)
        adapter.onEndRoundTeamSelectListener = this
        recycler_teams_end_round.adapter = adapter
        recycler_teams_end_round.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTeamSelected(team: Team) {
        Toast.makeText(context, team.name, Toast.LENGTH_SHORT).show()
    }

    override fun onNoneSelected() {
        Toast.makeText(context, "None", Toast.LENGTH_SHORT).show()
        dismiss()
    }
}