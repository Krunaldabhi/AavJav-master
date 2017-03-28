package com.aavjaav.qd.aavjaav.presenter;

import android.content.Context;
import android.util.Log;

import com.aavjaav.qd.aavjaav.model.api.RestApiManager;
import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsRequest;
import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel on 3/11/2017.
 */

public class BookingsPresenter implements BookingsContract.BookingsPresenter {

    private static final String TAG = "BookingsPresenter";
    RestApiManager mRestApiManager;
    BookingsContract.BookingsView mView;
    Context mContext;


    public BookingsPresenter(Context mContext, BookingsContract.BookingsView mView) {
        mRestApiManager = new RestApiManager();
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void getBookings(int cid) {
        mRestApiManager.getBookingsApi().getBookings(new BookingsRequest(cid)).enqueue(new Callback<BookingsResponse>() {
            @Override
            public void onResponse(Call<BookingsResponse> call, Response<BookingsResponse> response) {
                if (response.body() == null) {
                    Log.i(TAG, "onResponse: null");
                    return;
                }
                ArrayList<BookingsResponse.Result> currentBookings = new ArrayList<BookingsResponse.Result>();
                ArrayList<BookingsResponse.Result> previousBookings = new ArrayList<BookingsResponse.Result>();

                for (int i = 0; i < response.body().getResult().size(); i++) {
                    if (response.body().getResult().get(i).getStatus().equals("1")) {
                        currentBookings.add(response.body().getResult().get(i));
                    }
                    if (response.body().getResult().get(i).getStatus().equals("2")) {
                        previousBookings.add(response.body().getResult().get(i));
                    }
                }

                mView.showCurrentBookings(currentBookings);
                mView.showPreviousBookings(previousBookings);

            }

            @Override
            public void onFailure(Call<BookingsResponse> call, Throwable t) {

            }
        });
    }
}
