package yterletskyi.alias.gameScreen.model

import android.content.res.Resources
import yterletskyi.alias.R

/**
 * Created by yterletskyi on 14.11.16.
 */
class Words(res: Resources) {

    private var mIndex = 0
    private var words: Array<String>

    init {
        words = res.getStringArray(R.array.words)
    }

    fun nextWord(): String {
        return words[mIndex++]
    }
}