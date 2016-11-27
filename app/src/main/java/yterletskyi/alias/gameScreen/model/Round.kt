package yterletskyi.alias.gameScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Round(length: Long, listener: OnRoundTimeListener) {

    private var mTimer = object : MyTimer(length) {

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