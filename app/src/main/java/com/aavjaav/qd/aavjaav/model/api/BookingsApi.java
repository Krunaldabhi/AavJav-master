package com.aavjaav.qd.aavjaav.model.api;

import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsRequest;
import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Daniel on 3/11/2017.
 */

public interface BookingsApi {


    @Headers("Content-Type: application/json")
    @POST("android/all_booking_details.php")
    Call<BookingsResponse> getBookings(@Body BookingsRequest request);

}
