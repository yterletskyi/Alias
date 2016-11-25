package yterletskyi.alias.gameScreen.model

import android.content.Context
import yterletskyi.alias.SharedPreferencesManager

/**
 * Created by yterletskyi on 15.11.16.
 */
class GamePreferences(context: Context) {

    private val mPrefManager = SharedPreferencesManager(context)

    fun getFinishScore(): Int {
        return (mPrefManager.getGameFinishScore() + 1) * Constants.SCORE_STEP
    }

    fun getRoundTime(): Int {
        return (mPrefManager.getRoundLengthTime() + 1) * Constants.TIME_STEP
    }
}