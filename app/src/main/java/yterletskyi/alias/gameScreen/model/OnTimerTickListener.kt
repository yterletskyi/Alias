package yterletskyi.alias.gameScreen.model

/**
 * Created by yterletskyi on 16.11.16.
 */
interface OnTimerTickListener {

    fun onTick(secondsLeft: Int)

    fun onFinish()
}