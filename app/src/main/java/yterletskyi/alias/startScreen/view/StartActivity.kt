package yterletskyi.alias.startScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.ActivityStarter
import yterletskyi.alias.R
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
        Log.i(TAG, "showGame: ")
        // TODO: 09.11.16 go to Game Activity
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
