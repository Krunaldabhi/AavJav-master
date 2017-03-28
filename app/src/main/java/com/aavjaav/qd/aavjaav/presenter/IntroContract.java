package com.aavjaav.qd.aavjaav.presenter;

/**
 * Created by Daniel on 2/12/2017.
 */

public interface IntroContract {

    interface IntroView {
        void showIntro();
        void showLogin();
    }

    interface IntroPresenter {
        void isFirstTimeLaunch();
    }

}
