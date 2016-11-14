package yterletskyi.alias.gameScreen.model

import android.content.Context
import android.util.Log

/**
 * Created by yterletskyi on 14.11.16.
 */
class Game(context: Context) : OnRoundTimeListener {

    private lateinit var mTeamsArrayList: MutableList<Team>
    private lateinit var mWords: Words
    private var mRound: Round? = null
    private var mCurrentTeam: Team? = null

    init {
        setupTeams(context)
        setupWords(context)
    }

    private fun setupWords(context: Context) {
        mWords = Words(context.resources)
    }

    private fun setupTeams(context: Context) {
//        val teamSaver = TeamSaver(context)
//        mTeamsArrayList = teamSaver.getTeams()
        mTeamsArrayList = arrayListOf(Team("team1", 0, 0), Team("team2", 0, 0));
        mCurrentTeam = mTeamsArrayList.get(0)
    }

    fun start() {
        mRound = Round(this)
        mRound!!.start()
    }

    override fun onRoundEnded() {
        mCurrentTeam!!.drawScores = mRound!!.draws
        mCurrentTeam!!.winScores = mRound!!.wins
        Log.i("info", "round ended")
    }

    override fun onSecondElapsed() {
        Log.i("info", "one second elapsed")
    }

    fun getCurrentTeam(): Team {
        return mCurrentTeam!!
    }

    fun getCurrentWord(): String {
        return mWords.nextWord()
    }

    fun correctAnswer() {
        mRound!!.wins++
    }

    fun wrongAnswer() {
        mRound!!.draws++
    }
}