package yterletskyi.alias.util

import android.app.Activity
import android.content.Intent

/**
 * Created by yterletskyi on 09.11.16.
 */

class ActivityStarter {

    fun start(from: Activity, to: Class<out Activity>, finishFrom: Boolean) {
        val intent = Intent(from, to)
        from.startActivity(intent)
        if (finishFrom) {
            from.finish()
        }
    }

}
