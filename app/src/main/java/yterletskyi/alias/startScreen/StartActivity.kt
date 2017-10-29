package yterletskyi.alias.startScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import yterletskyi.alias.R
import yterletskyi.alias.util.replaceFragmentInActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startView = StartFragment.newInstance()
        startView.presenter = StartPresenter(startView)
        replaceFragmentInActivity(startView, R.id.contentFrame)

    }

}
