package yterletskyi.alias.gameScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import yterletskyi.alias.R

class GameActivity : AppCompatActivity(), GameView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}
