package yterletskyi.alias.settingsScreen.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.ActivityStarter
import yterletskyi.alias.R
import yterletskyi.alias.roundScreen.model.GamePreferences
import yterletskyi.alias.settingsScreen.presenter.SettingsPresenter
import yterletskyi.alias.setupTeamsScreen.view.SetupTeamsActivity

class SettingsActivity : AppCompatActivity(), SettingsView {

    @BindView(R.id.text_time)
    lateinit var mTimeTxtView: TextView

    @BindView(R.id.text_score)
    lateinit var mScoreTxtView: TextView

    @BindView(R.id.seek_time)
    lateinit var mTimeSeekBar: SeekBar

    @BindView(R.id.seek_score)
    lateinit var mScoreSeekBar: SeekBar

    val mPresenter: SettingsPresenter = SettingsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        ButterKnife.bind(this)
        addActionBarUpButton();
        mPresenter.onCreate()
        mScoreSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mPresenter.onSeekScoreValueChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        mTimeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

    @OnClick(R.id.btn_go_setup_teams)
    fun onSetupTeamsBtnClicked() {
        mPresenter.showSetupTeamsScreen()
    }

    override fun showSetupTeamsScreen() {
        ActivityStarter().start(this, SetupTeamsActivity::class.java, false)
    }

    override fun onDestroy() {
        mPresenter.save()
        super.onDestroy()
    }

    override fun setTimeSeek(position: Int) {
        mTimeSeekBar.progress = position
    }

    override fun setScoreSeek(position: Int) {
        mScoreSeekBar.progress = position
    }

    override fun setTimeText(time: String) {
        mTimeTxtView.text = time
    }

    override fun setScoreText(scores: String) {
        mScoreTxtView.text = scores
    }

    override fun getEndTime(): String {
        return mTimeTxtView.text.toString()
    }

    override fun getEndScore(): Int {
        val text = mScoreTxtView.text
        return (text as String).toInt()
    }

    override fun getGamePreferences(): GamePreferences {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        return GamePreferences(sharedPrefs)
    }
}
