package com.aavjaav.qd.aavjaav.model.pojo.searchcar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 3/14/2017.
 */

public class SearchCarRequest {

    @SerializedName("city")
    @Expose
    String city;

    public SearchCarRequest(String city) {
        this.city = city;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
