package yterletskyi.alias.gameScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Team(n: String, w: Int, d: Int) {

    var name: String = n
        private set

    var winScores: Int = w

    var drawScores: Int = d

}