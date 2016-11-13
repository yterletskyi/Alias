package yterletskyi.alias.startScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.ActivityStarter
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.view.GameActivity
import yterletskyi.alias.settingsScreen.view.SettingsActivity
import yterletskyi.alias.startScreen.presenter.StartPresenter

class StartActivity : AppCompatActivity(), StartView {

    private var mStartPresenter: StartPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        mStartPresenter = StartPresenter(this)
        ButterKnife.bind(this)

    }

    override fun showGame() {
        ActivityStarter().start(this, GameActivity::class.java, false)
    }

    override fun showSettings() {
        ActivityStarter().start(this, SettingsActivity::class.java, false)
    }

    @OnClick(R.id.btn_game)
    fun onGameBtnClicked() {
        mStartPresenter!!.onGameBtnClicked()
    }

    @OnClick(R.id.btn_settings)
    fun onSettingsBtnClicked() {
        mStartPresenter?.onSettingsBtnClicked()
    }

    companion object {
        private val TAG = StartActivity::class.java.simpleName
    }

}
