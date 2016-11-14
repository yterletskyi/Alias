package yterletskyi.alias.gameScreen.presenter

import android.content.Context
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.model.Game
import yterletskyi.alias.gameScreen.view.GameView

/**
 * Created by yterletskyi on 13.11.16.
 */
class GamePresenter(context: Context, val mView: GameView) {

    private var mGame: Game = Game(context)

    fun onCreate() {
        mView.showSnackbar(R.string.tap_to_start, R.string.start)
        mView.setupActionBar(mGame.getCurrentTeam().name)
    }

    fun startGame() {
        setWord()
        enableUi()
        mGame.start()
    }

    private fun setWord() {
        val word = mGame.getCurrentWord()
        mView.setWord(word)
    }

    private fun enableUi() {

    }

    fun correctAnswer() {
        mGame.correctAnswer()
    }

    fun wrongAnswer() {
        mGame.wrongAnswer()
    }
}