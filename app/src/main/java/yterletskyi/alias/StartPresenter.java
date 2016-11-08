package yterletskyi.alias;

/**
 * Created by yterletskyi on 09.11.16.
 */

public class StartPresenter {

    private StartView mView;

    public StartPresenter(StartView view) {
        mView = view;
    }

    public void onGameBtnClicked() {
        mView.showGame();
    }

    public void onSettingsBtnClicked() {
        mView.showSettings();
    }




}
