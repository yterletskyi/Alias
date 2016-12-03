package yterletskyi.alias.startScreen.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import yterletskyi.alias.ActivityStarter
import yterletskyi.alias.AliasApp
import yterletskyi.alias.R
import yterletskyi.alias.startScreen.presenter.StartPresenter

class StartActivity : AppCompatActivity(), StartView {

    private var mStartPresenter: StartPresenter = StartPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
//        // todo: think about better solution for this instead of workaround
//        mStartPresenter.onStart();
    }

    override fun showActivity(activity: Class<out Activity>, finishThis: Boolean) {
        ActivityStarter().start(this, activity, finishThis)
    }

    override fun getAliasApp(): AliasApp {
        return application as AliasApp
    }

    @OnClick(R.id.btn_game)
    fun onGameBtnClicked() {
        mStartPresenter.onGameBtnClicked()
    }

    @OnClick(R.id.btn_settings)
    fun onSettingsBtnClicked() {
        mStartPresenter.onSettingsBtnClicked()
    }
}
