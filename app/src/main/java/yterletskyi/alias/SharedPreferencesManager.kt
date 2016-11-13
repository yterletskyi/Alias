package yterletskyi.alias

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by yterletskyi on 12.11.16.
 */
class SharedPreferencesManager(context: Context?) {

    val GAME_TIME_LENGTH_KEY: String = "game_time_length_key"
    val GAME_FINISH_SCORE_KEY: String = "game_finish_score_key"

    var mSharedPreferences: SharedPreferences? = null;

    init {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveGameLengthTime(seconds: Int) {
        val editor = mSharedPreferences!!.edit()
        editor.putInt(GAME_TIME_LENGTH_KEY, seconds)
        editor.apply()
    }

    fun getGameLengthTime(): Int {
        return mSharedPreferences!!.getInt(GAME_TIME_LENGTH_KEY, 3);
    }

    fun saveGameFinishScore(seconds: Int) {
        val editor = mSharedPreferences!!.edit()
        editor.putInt(GAME_FINISH_SCORE_KEY, seconds)
        editor.apply()
    }

    fun getGameFinishScore(): Int {
        return mSharedPreferences!!.getInt(GAME_FINISH_SCORE_KEY, 9);
    }


}