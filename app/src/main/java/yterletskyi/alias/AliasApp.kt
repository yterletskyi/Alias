package yterletskyi.alias

import android.app.Application
import android.preference.PreferenceManager
import yterletskyi.alias.roundScreen.model.Game
import yterletskyi.alias.roundScreen.model.GameConfigs
import yterletskyi.alias.roundScreen.model.GamePreferences
import yterletskyi.alias.roundScreen.model.Words

/**
 * Created by yterletskyi on 29.11.16.
 */
class AliasApp : Application() {

    lateinit var game: Game

    override fun onCreate() {
        super.onCreate()
        setupGame()
    }

    private fun getGamePreferences(): GamePreferences {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        return GamePreferences(prefs)
    }

    private fun getWords(): Words {
        val words = resources.getStringArray(R.array.words)
        return Words(words)
    }

    private fun setupGame() {
        val gamePreferences = getGamePreferences()
        val teams = gamePreferences.getTeamSaver().getTeams()
        val words = getWords()
        game = Game(teams, words)
        game.gameConfigs = GameConfigs(gamePreferences.getRoundTime(), gamePreferences.getFinishScore());
    }

    fun saveGameConfigToPreferences() {
        val gamePreferences = getGamePreferences()
        gamePreferences.saveGameFinishScore(game.gameConfigs!!.gameFinishScore)
        gamePreferences.saveRoundLengthTime(game.gameConfigs!!.roundLengthSec)
    }


}