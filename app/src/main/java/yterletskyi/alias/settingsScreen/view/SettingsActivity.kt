package yterletskyi.alias.settingsScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.R
import yterletskyi.alias.settingsScreen.presenter.SettingsPresenter

class SettingsActivity : AppCompatActivity(), SettingsView {

    var mPresenter: SettingsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        ButterKnife.bind(this)
        mPresenter = SettingsPresenter(this)
        mPresenter!!.onCreate()
    }

    @OnClick(R.id.btn_go_setup_teams)
    fun onSetupTeamsBtnClicked() {
        mPresenter!!.showSetupTeamsScreen()
    }

    @OnClick(R.id.btn_save)
    fun onSaveBtnClicked() {
        mPresenter!!.save()
    }

    override fun showSetupTeamsScreen() {
        // TODO: show setup teams screen
    }

    override fun save() {
        mPresenter!!.save()
    }

    override fun setEndTime(minutes: Int, seconds: Int) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun setEndScore(scores: Int) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun getEndTime(): Int {
        throw UnsupportedOperationException("not implemented")
    }

    override fun getEndScore(): Int {
        throw UnsupportedOperationException("not implemented")
    }
}
