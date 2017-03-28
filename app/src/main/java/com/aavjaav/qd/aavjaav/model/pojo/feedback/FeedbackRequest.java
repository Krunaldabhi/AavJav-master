package com.aavjaav.qd.aavjaav.model.pojo.feedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 3/13/2017.
 */

public class FeedbackRequest {

    @SerializedName("cid")
    @Expose
    int cid;
    @SerializedName("message")
    @Expose
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FeedbackRequest(int cid, String message) {
        this.cid = cid;
        this.message = message;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
