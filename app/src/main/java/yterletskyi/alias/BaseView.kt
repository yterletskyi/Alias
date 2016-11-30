package yterletskyi.alias

import android.app.Activity

/**
 * Created by yterletskyi on 30.11.16.
 */
interface BaseView {

    fun getAliasApp(): AliasApp

    fun showActivity(activity: Class<out Activity>, finishThis: Boolean)

}