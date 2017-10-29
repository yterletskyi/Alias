package yterletskyi.alias.util

/**
 * Created by yterletskyi on 19.11.16.
 */
class TimeFormatter {


    private fun composeTimeStr(min: Int, sec: Int): String {
        var secStr = sec.toString()
        if (sec < 10) {
            secStr = "0" + secStr
        }
        return min.toString() + ":" + secStr
    }

    fun formatTimeStr(seconds: Int): String {
        val minutes = seconds / 60
        val seconds = seconds - minutes * 60
        return composeTimeStr(minutes, seconds)
    }

}