package yterletskyi.alias

import android.app.Activity
import android.support.v7.app.AppCompatActivity

/**
 * Created by yterletskyi on 06.12.16.
 */
open class BaseActivity : AppCompatActivity(), BaseView {

    override fun getAliasApp(): AliasApp {
        return application as AliasApp
    }

    override fun showActivity(activity: Class<out Activity>, finishThis: Boolean) {
        ActivityStarter().start(this, activity, finishThis);
    }
}