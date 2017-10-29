package yterletskyi.alias.gameScreen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import yterletskyi.alias.R
import yterletskyi.alias.gameScreen.GameFragment
import yterletskyi.alias.gameScreen.presenter.GamePresenter
import yterletskyi.alias.util.replaceFragmentInActivity

class GameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameView = GameFragment()
        gameView.presenter = GamePresenter(gameView)
        replaceFragmentInActivity(gameView, R.id.contentFrameGame)

    }

}
