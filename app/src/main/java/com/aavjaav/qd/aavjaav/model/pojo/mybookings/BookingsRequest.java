package com.aavjaav.qd.aavjaav.model.pojo.mybookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 3/11/2017.
 */

public class BookingsRequest {

    @SerializedName("cid")
    @Expose
    int cid;

    public BookingsRequest(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
