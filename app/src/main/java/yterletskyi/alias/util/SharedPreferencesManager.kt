package yterletskyi.alias.util

import android.content.SharedPreferences

/**
 * Created by yterletskyi on 12.11.16.
 */
class SharedPreferencesManager(val mSharedPreferences: SharedPreferences) {


    fun putString(str: String, key: String) {
        val editor = mSharedPreferences.edit()
        editor.putString(key, str)
        editor.apply()
    }

    fun getString(key: String): String {
        return mSharedPreferences.getString(key, "")
    }

    fun getInt(key: String): Int {
        return mSharedPreferences.getInt(key, 0)
    }

    fun putInt(value: Int, s: String) {
        val editor = mSharedPreferences.edit()
        editor.putInt(s, value)
        editor.apply()
    }
}