package yterletskyi.alias.gameScreen.model

import android.content.Context
import yterletskyi.alias.SharedPreferencesManager

/**
 * Created by yterletskyi on 15.11.16.
 */
class GamePreferences(context: Context) {

    private val mPrefManager = SharedPreferencesManager(context)

    fun getGameScore(): Int {
        return mPrefManager.getGameFinishScore()
    }

    fun getGameTime(): Int {
        return mPrefManager.getGameLengthTime()
    }

}