package com.aavjaav.qd.aavjaav.model.api;

import com.aavjaav.qd.aavjaav.model.pojo.feedback.FeedbackRequest;
import com.aavjaav.qd.aavjaav.model.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private BookingsApi mBookingsApi;
    private LoginApi mLoginApi;
    private RegisterApi mRegisterApi;
    private FeedbackApi mFeedbackApi;
    private SearchCarApi mSearchCarApi;

    public BookingsApi getBookingsApi() {
        if (mBookingsApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .build();

            mBookingsApi = client.create(BookingsApi.class);
        }

        return mBookingsApi;
    }

    public SearchCarApi getSearchCarApi() {
        if (mSearchCarApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .build();

            mSearchCarApi = client.create(SearchCarApi.class);
        }

        return mSearchCarApi;
    }

    public FeedbackApi getFeedbackApi() {
        if (mFeedbackApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .build();

            mFeedbackApi = client.create(FeedbackApi.class);
        }
        return mFeedbackApi;
    }

    public LoginApi getLoginApi() {
        if (mLoginApi == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(clientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .build();

            mLoginApi = client.create(LoginApi.class);
        }
        return mLoginApi;
    }

    public RegisterApi getRegisterApi() {
        if (mRegisterApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .build();

            mRegisterApi = client.create(RegisterApi.class);
        }
        return mRegisterApi;
    }

}
