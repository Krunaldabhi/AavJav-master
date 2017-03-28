package com.aavjaav.qd.aavjaav.model.api;


import com.aavjaav.qd.aavjaav.model.pojo.account.LoginRequest;
import com.aavjaav.qd.aavjaav.model.pojo.account.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("android/login.php")
    Call<LoginResponse> doLogin(@Body LoginRequest request);

}
