package com.aavjaav.qd.aavjaav.presenter;

/**
 * Created by Daniel on 3/13/2017.
 */

public interface FeedbackContract {

    interface FeedbackPresenter {
        void sendMessage(String message);
    }

    interface FeedbackView {
        void onSuccess(String s);
        void onFailure(String errorMsg);
    }

}
