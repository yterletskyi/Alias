package yterletskyi.alias

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

    fun formatTimeStr(secs: Int): String {
        val minutes = secs / 60
        val seconds = secs - minutes * 60
        return composeTimeStr(minutes, seconds)
    }

}