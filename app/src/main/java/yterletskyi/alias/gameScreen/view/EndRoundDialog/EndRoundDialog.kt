package yterletskyi.alias.gameScreen.view.EndRoundDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Team

/**
 * Created by yterletskyi on 15.11.16.
 */
class EndRoundDialog(context: Context) : Dialog(context) {

    private var mTeamList: MutableList<Team>? = null

    constructor(context: Context, teams: MutableList<Team>) : this(context) {
        mTeamList = teams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_end_round)

        setTitle(R.string.who_gets_the_word)

        val adapter = SimpleTeamsAdapter(context, mTeamList!!)
        val listView = findViewById(R.id.list_teams_end_round) as ListView

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val team = mTeamList!![i]
            Toast.makeText(context, team.name, Toast.LENGTH_SHORT).show()
            dismiss()
        }

        listView.adapter = adapter

    }

}