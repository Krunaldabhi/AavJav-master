package com.aavjaav.qd.aavjaav.presenter;

import android.util.Log;

import com.aavjaav.qd.aavjaav.model.api.RestApiManager;
import com.aavjaav.qd.aavjaav.model.pojo.account.RegisterRequest;
import com.aavjaav.qd.aavjaav.model.pojo.account.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    RegisterContract.RegisterView mView;
    RestApiManager mRestApiManager;

    public RegisterPresenter(RegisterContract.RegisterView mView) {
        this.mView = mView;
        mRestApiManager = new RestApiManager();
    }

    @Override
    public void doRegister(String email, String password) {
        mRestApiManager.getRegisterApi().doRegister(new RegisterRequest(email,password)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.body() == null) {
                    return;
                }
                if(response.body().isError()) {
                    mView.onFailure();
                } else {
                    mView.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ", t);
            }
        });
    }
}
