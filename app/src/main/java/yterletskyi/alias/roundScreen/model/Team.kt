package yterletskyi.alias.roundScreen.model

/**
 * Created by yterletskyi on 14.11.16.
 */
class Team(val name: String, var winScores: Int, var drawScores: Int) {

    constructor(n: String) : this(n, 0, 0)

}