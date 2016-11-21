package yterletskyi.alias.gameScreen.model

import android.content.Context

/**
 * Created by yterletskyi on 14.11.16.
 */
class Game(context: Context) : OnRoundTimeListener {

    lateinit var teamsArrayList: MutableList<Team>
    private lateinit var mWords: Words
    private var mCurrentTeam: Team? = null
    var onRoundEndListener: OnRoundTimeListener? = null
    private var mGamePreferences: GamePreferences = GamePreferences(context)
    private var mRound: Round = Round((getRoundLength() * 1000).toLong(), this)

    init {
        setupTeams(context)
        setupWords(context)
    }

    private fun setupWords(context: Context) {
        mWords = Words(context.resources)
    }

    private fun setupTeams(context: Context) {
//        val teamSaver = TeamSaver(context)
//        teamsArrayList = teamSaver.getTeams()
        teamsArrayList = arrayListOf(Team("team1", 0, 0), Team("team2", 0, 0));
        mCurrentTeam = teamsArrayList.get(0)
    }

    fun start() {
        mRound.start()
    }

    fun pause() {
        mRound.pause()
    }

    fun resume() {
        mRound.resume()
    }

    override fun onRoundEnded() {
        mCurrentTeam!!.drawScores = mRound.draws
        mCurrentTeam!!.winScores = mRound.wins
        onRoundEndListener!!.onRoundEnded()
    }

    override fun onSecondElapsed(time: Int) {
        onRoundEndListener!!.onSecondElapsed(time)
    }

    fun getCurrentTeam(): Team {
        return mCurrentTeam!!
    }

    fun getNextWord(): String {
        return mWords.nextWord()
    }

    fun correctAnswer(): Int {
        return ++mRound.wins
    }

    fun wrongAnswer(): Int {
        return ++mRound.draws
    }

    fun getRoundLength(): Int {
        return mGamePreferences.getRoundTime()
    }

    fun stop() {
        mRound.stop()
    }
}