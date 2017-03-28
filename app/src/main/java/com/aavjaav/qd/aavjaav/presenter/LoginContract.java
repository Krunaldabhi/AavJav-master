package com.aavjaav.qd.aavjaav.presenter;

/**
 * Created by Daniel on 2/13/2017.
 */

public interface LoginContract {

    interface LoginView {
        void onLoginSuccess();
        void onLoginFailure(String errorMsg);
        void showRegister();
        void showRetreivePass();
    }

    interface LoginPresenter {
        void doLogin(String email, String password);
        void doRegister();
        void doFacebookLogin();
        void doGoogleLogin();
        void doRetreivePass();
    }





}
