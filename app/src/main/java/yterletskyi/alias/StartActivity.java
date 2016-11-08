package yterletskyi.alias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity implements StartView {

    private static final String TAG = StartActivity.class.getSimpleName();
    private StartPresenter mStartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        mStartPresenter = new StartPresenter(this);
    }

    @Override
    public void showGame() {
        Log.i(TAG, "showGame: ");
        // TODO: 09.11.16 go to Game Activity
    }

    @Override
    public void showSettings() {
        Log.i(TAG, "showSettings: ");
        // TODO: 09.11.16 go to Settings Activity
    }

    @OnClick(R.id.btn_game)
    public void onGameBtnClicked() {
        mStartPresenter.onGameBtnClicked();
    }

    @OnClick(R.id.btn_settings)
    public void onSettingsBtnClicked() {
        mStartPresenter.onSettingsBtnClicked();
    }

}
