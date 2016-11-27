package yterletskyi.alias

import android.app.Activity
import android.content.Intent
import android.os.Bundle

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

    fun start(from: Activity, to: Class<out Activity>, finishFrom: Boolean, data: Bundle) {
        val intent = Intent(from, to)
        intent.putExtras(data)
        from.startActivity(intent)
        if (finishFrom) {
            from.finish()
        }
    }

}
