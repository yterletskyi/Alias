package yterletskyi.alias.gameScreen.model

import android.os.CountDownTimer

/**
 * Created by yterletskyi on 14.11.16.
 */
class Round(listener: OnRoundTimeListener) {

    var wins: Int = 0
    var draws: Int = 0
    private var mTimer = object : CountDownTimer(5000, 1000) {
        override fun onFinish() {
            listener.onRoundEnded()
        }

        override fun onTick(millisUntilFinished: Long) {
            listener.onSecondElapsed()
        }
    }

    fun start() {
        mTimer.start()
    }

}