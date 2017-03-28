package com.aavjaav.qd.aavjaav.model.pojo.searchcar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel on 3/14/2017.
 */

public class SearchCarResponse {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("vender_id")
        @Expose
        private String venderId;
        @SerializedName("vendor_name")
        @Expose
        private String vendorName;
        @SerializedName("vehicle_compnay_name")
        @Expose
        private String vehicleCompnayName;
        @SerializedName("vender_car_name")
        @Expose
        private String venderCarName;
        @SerializedName("mbill")
        @Expose
        private String mbill;
        @SerializedName("echarge")
        @Expose
        private String echarge;
        @SerializedName("wtcharge")
        @Expose
        private String wtcharge;
        @SerializedName("rtcharge")
        @Expose
        private String rtcharge;
        @SerializedName("star_rateing")
        @Expose
        private Object starRateing;
        @SerializedName("star_rating_count")
        @Expose
        private String starRatingCount;
        @SerializedName("customer_used_count")
        @Expose
        private String customerUsedCount;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVenderId() {
            return venderId;
        }

        public void setVenderId(String venderId) {
            this.venderId = venderId;
        }

        public String getVendorName() {
            return vendorName;
        }

        public void setVendorName(String vendorName) {
            this.vendorName = vendorName;
        }

        public String getVehicleCompnayName() {
            return vehicleCompnayName;
        }

        public void setVehicleCompnayName(String vehicleCompnayName) {
            this.vehicleCompnayName = vehicleCompnayName;
        }

        public String getVenderCarName() {
            return venderCarName;
        }

        public void setVenderCarName(String venderCarName) {
            this.venderCarName = venderCarName;
        }

        public String getMbill() {
            return mbill;
        }

        public void setMbill(String mbill) {
            this.mbill = mbill;
        }

        public String getEcharge() {
            return echarge;
        }

        public void setEcharge(String echarge) {
            this.echarge = echarge;
        }

        public String getWtcharge() {
            return wtcharge;
        }

        public void setWtcharge(String wtcharge) {
            this.wtcharge = wtcharge;
        }

        public String getRtcharge() {
            return rtcharge;
        }

        public void setRtcharge(String rtcharge) {
            this.rtcharge = rtcharge;
        }

        public Object getStarRateing() {
            return starRateing;
        }

        public void setStarRateing(Object starRateing) {
            this.starRateing = starRateing;
        }

        public String getStarRatingCount() {
            return starRatingCount;
        }

        public void setStarRatingCount(String starRatingCount) {
            this.starRatingCount = starRatingCount;
        }

        public String getCustomerUsedCount() {
            return customerUsedCount;
        }

        public void setCustomerUsedCount(String customerUsedCount) {
            this.customerUsedCount = customerUsedCount;
        }

    }
}
