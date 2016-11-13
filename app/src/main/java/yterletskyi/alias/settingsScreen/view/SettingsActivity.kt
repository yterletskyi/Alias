package yterletskyi.alias.settingsScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.R
import yterletskyi.alias.settingsScreen.presenter.SettingsPresenter

class SettingsActivity : AppCompatActivity(), SettingsView, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.text_time)
    lateinit var mTimeTxtView: TextView

    @BindView(R.id.text_score)
    lateinit var mScoreTxtView: TextView

    @BindView(R.id.seek_time)
    lateinit var mTimeSeekBar: SeekBar

    @BindView(R.id.seek_score)
    lateinit var mScoreSeekBar: SeekBar

    var mPresenter: SettingsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        ButterKnife.bind(this)
        mPresenter = SettingsPresenter(this)
        mPresenter!!.onCreate()
        mScoreSeekBar.setOnSeekBarChangeListener(this)
        mTimeSeekBar.setOnSeekBarChangeListener(this)
    }

    @OnClick(R.id.btn_go_setup_teams)
    fun onSetupTeamsBtnClicked() {
        mPresenter!!.showSetupTeamsScreen()
    }

    override fun showSetupTeamsScreen() {
        // TODO: show setup teams screen
    }

    override fun onDestroy() {
        mPresenter!!.save()
        super.onDestroy()
    }

    override fun setTimeSeek(minutes: Int, seconds: Int) {
        mTimeSeekBar.setProgress(seconds)
    }

    override fun setScoreSeek(scores: Int) {
        mScoreSeekBar.progress = scores
    }

    override fun setTimeText(time: String) {
        mTimeTxtView.text = time
    }

    override fun setScoreText(scores: String) {
        mScoreTxtView.text = scores
    }

    override fun getEndTime(): Int {
        return mTimeSeekBar.getProgress()
    }

    override fun getEndScore(): Int {
        return mScoreSeekBar.progress
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        mPresenter!!.onSeekBarValueChanged(seekBar, progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}
