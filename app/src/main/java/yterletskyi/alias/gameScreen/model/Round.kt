package yterletskyi.alias.gameScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Round(time: Long, listener: OnRoundTimeListener) {

    var wins: Int = 0
    var draws: Int = 0

    private var mTimer = object : MyTimer(time) {

        override fun onTick(secLeft: Int) {
            listener.onSecondElapsed(secLeft)
        }

        override fun onFinish() {
            listener.onRoundEnded()
        }
    }

    fun start() {
        mTimer.start()
    }

    fun pause() {
        mTimer.pause()
    }

    fun resume() {
        mTimer.resume()
    }

    fun stop() {
        mTimer.stop()
    }

}