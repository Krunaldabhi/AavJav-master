package com.aavjaav.qd.aavjaav.presenter;

import android.content.Context;
import android.util.Log;

import com.aavjaav.qd.aavjaav.model.api.RestApiManager;
import com.aavjaav.qd.aavjaav.model.pojo.feedback.FeedbackRequest;
import com.aavjaav.qd.aavjaav.model.pojo.feedback.FeedbackResponse;
import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel on 3/13/2017.
 */

public class FeedbackPresenter implements FeedbackContract.FeedbackPresenter {

    private static final String TAG = "FeedbackPresenter";
    private FeedbackContract.FeedbackView mView;
    private RestApiManager mRestApiManager;
    private Context mContext;

    public FeedbackPresenter(FeedbackContract.FeedbackView mView, Context context) {
        mContext = context;
        this.mView = mView;
        mRestApiManager = new RestApiManager();
    }


    @Override
    public void sendMessage(String message) {
        mRestApiManager.getFeedbackApi().postMessage(
                new FeedbackRequest(SharedPrefHelper.getInstance(mContext).getUserId(),
                        message)).enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                if (response.body().getResult().isError()) {
                    mView.onFailure(response.body().getResult().getErrorMsg());
                } else {
                    mView.onSuccess("Thank you for your message!");
                }
            }

            @Override
            public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }
}
