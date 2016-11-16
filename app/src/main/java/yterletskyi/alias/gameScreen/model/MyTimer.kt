package yterletskyi.alias.gameScreen.model

import android.os.Handler

/**
 * Created by yterletskyi on 16.11.16.
 */
abstract class MyTimer(var mTime: Long) {

    private val mCountDownInterval: Long = 1000;
    private var mHandler: Handler? = null
    private var mRunnable: Runnable? = null

    init {
        initialize()
        mTime -= 1
    }

    fun initialize() {
        mHandler = Handler()
        mRunnable = object : Runnable {

            override fun run() {
                val sec = mTime / 1000
                onTick(sec.toInt())
                if (mTime <= 1000) {
                    onFinish()
                    stop()
                } else {
                    mTime -= mCountDownInterval
                    mHandler!!.postDelayed(this, mCountDownInterval)
                }
            }
        }
    }

    fun pause() {
        mHandler!!.removeCallbacks(mRunnable)
        initialize()
    }

    fun resume() {
        mHandler!!.postDelayed(mRunnable, mCountDownInterval)
    }

    fun stop() {
        mHandler!!.removeCallbacks(mRunnable)
    }

    fun start() {
        mHandler!!.postDelayed(mRunnable, mCountDownInterval)
    }

    abstract fun onTick(secLeft: Int)

    abstract fun onFinish()

}
