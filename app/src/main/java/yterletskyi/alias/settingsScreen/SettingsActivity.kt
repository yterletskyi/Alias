package yterletskyi.alias.settingsScreen

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_settings.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.GamePreferences
import yterletskyi.alias.setupTeamsScreen.view.SetupTeamsActivity
import yterletskyi.alias.util.ActivityStarter

class SettingsActivity : AppCompatActivity(), SettingsView {

    val mPresenter: SettingsPresenter = SettingsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        addActionBarUpButton()
        btn_go_setup_teams.setOnClickListener({ mPresenter.showSetupTeamsScreen() })
        mPresenter.onCreate()
        seek_score.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mPresenter.onSeekScoreValueChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        seek_time.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mPresenter.onSeekTimeValueChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun addActionBarUpButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return false
    }

    override fun showSetupTeamsScreen() {
        ActivityStarter().start(this, SetupTeamsActivity::class.java, false)
    }

    override fun onDestroy() {
        mPresenter.save()
        super.onDestroy()
    }

    override fun setTimeSeek(position: Int) {
        seek_time.progress = position
    }

    override fun setScoreSeek(position: Int) {
        seek_score.progress = position
    }

    override fun setTimeText(time: String) {
        text_time.text = time
    }

    override fun setScoreText(scores: String) {
        text_score.text = scores
    }

    override fun getEndTime(): String {
        return text_time.text.toString()
    }

    override fun getEndScore(): Int {
        val text = text_score.text
        return (text as String).toInt()
    }

    override fun getGamePreferences(): GamePreferences {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        return GamePreferences(sharedPrefs)
    }
}
