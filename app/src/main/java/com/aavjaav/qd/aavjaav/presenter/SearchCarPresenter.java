package com.aavjaav.qd.aavjaav.presenter;

import android.content.Context;
import android.util.Log;

import com.aavjaav.qd.aavjaav.model.api.RestApiManager;
import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarRequest;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel on 3/14/2017.
 */

public class SearchCarPresenter implements SearchCarContract.SearchCarPresenter {

    private static final String TAG = "SearchCarPresenter";
    private Context mContext;
    private RestApiManager mRestApiManager;
    private SearchCarContract.SearchCarView mView;

    public SearchCarPresenter(Context mContext, SearchCarContract.SearchCarView mView) {
        this.mContext = mContext;
        this.mView = mView;
        mRestApiManager = new RestApiManager();
    }

    @Override
    public void searchCar(String city) {
        mRestApiManager.getSearchCarApi().searchCar(new SearchCarRequest(city)).enqueue(new Callback<SearchCarResponse>() {
            @Override
            public void onResponse(Call<SearchCarResponse> call, Response<SearchCarResponse> response) {
                if (response.body() == null) {
                    mView.onFailure();
                    Log.e(TAG, "onResponse: body is null SearchCar Response");
                } else {
                    mView.onSuccess(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<SearchCarResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
