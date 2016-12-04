package yterletskyi.alias.roundScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Game(teams: MutableList<Team>, val words: Words) : OnRoundTimeListener {

    var onRoundEndListener: OnRoundTimeListener? = null
    var gameConfigs: GameConfigs? = null

    private var mRound: Round? = null
    val teamManager: TeamManager = TeamManager(teams)

    fun startWithNewRound() {
        mRound = Round(gameConfigs!!.roundLengthSec, this)
        mRound!!.start()
    }

    fun pause() {
        mRound!!.pause()
    }

    fun resume() {
        mRound!!.resume()
    }

    fun stop() {
        if (mRound != null) {
            mRound!!.stop()
        }
    }

    override fun onRoundEnded() {
        teamManager.currentTeam.winScores += mRound!!.wins
        teamManager.currentTeam.drawScores += mRound!!.draws
        onRoundEndListener!!.onRoundEnded()
    }

    override fun onSecondElapsed(time: Int) {
        onRoundEndListener!!.onSecondElapsed(time)
    }

    fun answerCorrect(): Int {
        return ++mRound!!.wins
//        return ++teamManager.currentTeam.winScores
    }

    fun answerWrong(): Int {
        return ++mRound!!.draws
//        return ++teamManager.currentTeam.drawScores
    }
}