package com.aavjaav.qd.aavjaav.model.api;

import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarRequest;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Daniel on 3/14/2017.
 */

public interface SearchCarApi {

    @Headers("Content-Type: application/json")
    @POST("android/car-list.php")
    Call<SearchCarResponse> searchCar(@Body SearchCarRequest request);

}
