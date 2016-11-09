package yterletskyi.alias

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import butterknife.OnClick

class StartActivity : AppCompatActivity(), StartView {

    private var mStartPresenter: StartPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        ButterKnife.bind(this)
        mStartPresenter = StartPresenter(this)
    }

    override fun showGame() {
        Log.i(TAG, "showGame: ")
        // TODO: 09.11.16 go to Game Activity
    }

    override fun showSettings() {
        Log.i(TAG, "showSettings: ")
        // TODO: 09.11.16 go to Settings Activity
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
