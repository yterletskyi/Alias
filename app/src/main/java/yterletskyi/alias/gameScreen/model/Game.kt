package yterletskyi.alias.gameScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Game(gamePreferences: GamePreferences, words: Array<String>) : OnRoundTimeListener {

    lateinit var teamsArrayList: MutableList<Team>
    private val mWords: Words = Words(words)
    private var mCurrentTeam: Team? = null
    var onRoundEndListener: OnRoundTimeListener? = null
    private var mGamePreferences: GamePreferences = gamePreferences
    private var mRound: Round = Round((getRoundLength() * 1000).toLong(), this)

    init {
        setupTeams()
    }

    private fun setupTeams() {
        val teamSaver = mGamePreferences.getTeamSaver()
        teamsArrayList = teamSaver.getTeams()
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
        return ++mCurrentTeam!!.winScores
    }

    fun wrongAnswer(): Int {
        return ++mCurrentTeam!!.drawScores
    }

    fun getRoundLength(): Int {
        return mGamePreferences.getRoundTime()
    }

    fun stop() {
        mRound.stop()
    }
}