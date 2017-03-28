package com.aavjaav.qd.aavjaav.presenter;

/**
 * Created by Daniel on 2/14/2017.
 */

public interface RegisterContract {

    interface RegisterPresenter {
        void doRegister(String email, String password);
    }

    interface RegisterView {
        void onSuccess();
        void onFailure();
    }

}
