package yterletskyi.alias;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by yterletskyi on 09.11.16.
 */

public class ActivityStarter {

    public void start(Activity from, Class<? extends Activity> to, boolean finishFrom) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
        if (finishFrom) {
            from.finish();
        }
    }

}
