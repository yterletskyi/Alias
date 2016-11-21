package yterletskyi.alias

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by yterletskyi on 12.11.16.
 */
class SharedPreferencesManager(context: Context?) {

    private val ROUND_TIME_LENGTH_KEY: String = "game_time_length_key"
    private val GAME_FINISH_SCORE_KEY: String = "game_finish_score_key"

    private var mSharedPreferences: SharedPreferences? = null;

    init {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveGameLengthTime(seconds: Int) {
        val editor = mSharedPreferences!!.edit()
        editor.putInt(ROUND_TIME_LENGTH_KEY, seconds)
        editor.apply()
    }

    fun getRoundLengthTime(): Int {
        return mSharedPreferences!!.getInt(ROUND_TIME_LENGTH_KEY, 3);
    }

    fun saveGameFinishScore(seconds: Int) {
        val editor = mSharedPreferences!!.edit()
        editor.putInt(GAME_FINISH_SCORE_KEY, seconds)
        editor.apply()
    }

    fun getGameFinishScore(): Int {
        return mSharedPreferences!!.getInt(GAME_FINISH_SCORE_KEY, 9);
    }

    fun saveTeamsCount(count: Int) {
        // todo implement
    }

    fun getTeamsCount(): Int {
        // todo implement
        return 2
    }

    fun getString(key: String): String {
        return mSharedPreferences!!.getString(key, "")
    }

    fun getInt(key: String): Int {
        return mSharedPreferences!!.getInt(key, 0)
    }


}