package yterletskyi.alias.gameScreen.presenter

import android.view.animation.AnimationUtils
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.view.GameView

/**
 * Created by yterletskyi on 13.11.16.
 */
class GamePresenter(val mView: GameView) {

    fun onCreate() {
        mView.showSnackbar(R.string.tap_to_start, R.string.start)
        mView.setupActionBar("Random Team Name here!");
    }

    fun startGame() {
        setWord()
        enableUi()
        startTimer()
        startClockAnimation()
    }

    private fun setWord() {

    }

    private fun startClockAnimation() {

    }

    private fun startTimer() {

    }

    private fun enableUi() {

    }


}