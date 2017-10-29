package yterletskyi.alias.startScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_start.*
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.view.GameActivity
import yterletskyi.alias.settingsScreen.view.SettingsActivity
import yterletskyi.alias.util.ActivityStarter


/**
 * Created by Yura T on 10/29/2017.
 */
class StartFragment : Fragment(), StartContract.View {

    override lateinit var presenter: StartContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_game.setOnClickListener { presenter.onGameBtnClicked() }
        btn_settings.setOnClickListener { presenter.onSettingsBtnClicked() }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun showGame() {
        ActivityStarter().start(activity, GameActivity::class.java, false)
    }

    override fun showSettings() {
        ActivityStarter().start(activity, SettingsActivity::class.java, false)
    }

    companion object {
        fun newInstance() = StartFragment()
    }

}