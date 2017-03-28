package com.aavjaav.qd.aavjaav.model.api;

import com.aavjaav.qd.aavjaav.model.pojo.account.RegisterRequest;
import com.aavjaav.qd.aavjaav.model.pojo.account.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RegisterApi {


    @Headers("Content-Type: application/json")
    @POST("android/register.php")
    Call<RegisterResponse> doRegister(@Body RegisterRequest registerRequest);


}
