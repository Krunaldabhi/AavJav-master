package com.aavjaav.qd.aavjaav.model.pojo.feedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FeedbackResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("error")
        @Expose
        private boolean error;
        @SerializedName("error_msg")
        @Expose
        private String errorMsg;

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
