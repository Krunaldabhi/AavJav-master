package com.aavjaav.qd.aavjaav.model.api;

import com.aavjaav.qd.aavjaav.model.pojo.feedback.FeedbackRequest;
import com.aavjaav.qd.aavjaav.model.pojo.feedback.FeedbackResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Daniel on 3/13/2017.
 */

public interface FeedbackApi {

    @Headers("Content-Type: application/json")
    @POST("android/app_feedback.php")
    Call<FeedbackResponse> postMessage(@Body FeedbackRequest request);

}
