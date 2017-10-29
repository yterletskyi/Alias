package yterletskyi.alias.gameScreen.model

import android.content.SharedPreferences
import yterletskyi.alias.util.SharedPreferencesManager

/**
 * Created by yterletskyi on 15.11.16.
 */
class GamePreferences(sharedPreferences: SharedPreferences) {

    private val ROUND_TIME_LENGTH_KEY: String = "game_time_length_key"
    private val GAME_FINISH_SCORE_KEY: String = "game_finish_score_key"

    private val mPrefManager = SharedPreferencesManager(sharedPreferences)
    private val mTeamSaver = TeamSaver(mPrefManager)

    fun saveRoundLengthTime(seconds: Int) {
        mPrefManager.putInt(seconds, ROUND_TIME_LENGTH_KEY)
    }

    fun getRoundLengthTime(): Int {
        return mPrefManager.getInt(ROUND_TIME_LENGTH_KEY)
    }

    fun saveGameFinishScore(seconds: Int) {
        mPrefManager.putInt(seconds, GAME_FINISH_SCORE_KEY)
    }

    fun getGameFinishScore(): Int {
        return mPrefManager.getInt(GAME_FINISH_SCORE_KEY)
    }

    fun getFinishScore(): Int {
        return (getGameFinishScore() + 1) * Constants.SCORE_STEP
    }

    fun getRoundTime(): Int {
        return (getRoundLengthTime() + 1) * Constants.TIME_STEP
    }

    fun getTeamSaver(): TeamSaver {
        return mTeamSaver
    }
}