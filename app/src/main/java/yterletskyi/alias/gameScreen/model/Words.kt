package yterletskyi.alias.gameScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Words(private var mWrods: Array<String>) {

    private var mIndex = 0

    fun nextWord(): String {
        return mWrods[mIndex++]
    }
}