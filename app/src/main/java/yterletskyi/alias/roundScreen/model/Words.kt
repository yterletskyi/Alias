package yterletskyi.alias.roundScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Words(private var mWords: Array<String>) {

    private var mIndex = 0

    fun nextWord(): String {
        return mWords[mIndex++]
    }
}