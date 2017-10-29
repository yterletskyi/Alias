package yterletskyi.alias.setupTeamsScreen.view

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import kotlinx.android.synthetic.main.dialog_add_team.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Team
import yterletskyi.alias.setupTeamsScreen.model.OnAddTeamListener

/**
 * Created by yterletskyi on 25.11.16.
 */
class AddTeamDialog : DialogFragment() {

    private lateinit var mEditText: EditText;
    lateinit var onAddTeamListener: OnAddTeamListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.write_team_name)
        mEditText = edit_text_team_name;
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_team, null)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok, { dialogInterface, i ->
            val text = mEditText.text.toString()
            onAddTeamListener.onTeamAdded(Team(text))
        })
        builder.setNegativeButton(android.R.string.cancel, { dialogInterface, i -> dismiss() })
        return builder.create()
    }
}