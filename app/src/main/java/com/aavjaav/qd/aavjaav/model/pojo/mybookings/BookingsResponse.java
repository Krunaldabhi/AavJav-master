package com.aavjaav.qd.aavjaav.model.pojo.mybookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BookingsResponse {

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

        @SerializedName("BookingId")
        @Expose
        private String bookingId;
        @SerializedName("PickUpAddress")
        @Expose
        private String pickUpAddress;
        @SerializedName("DropAddress")
        @Expose
        private String dropAddress;
        @SerializedName("PickUpDate")
        @Expose
        private String pickUpDate;
        @SerializedName("PickUpTime")
        @Expose
        private String pickUpTime;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("Vehicle_Company_Name")
        @Expose
        private String vehicleCompanyName;
        @SerializedName("TypeName")
        @Expose
        private String typeName;
        @SerializedName("rtcharge")
        @Expose
        private Object rtcharge;
        @SerializedName("error")
        @Expose
        private boolean error;

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getPickUpAddress() {
            return pickUpAddress;
        }

        public void setPickUpAddress(String pickUpAddress) {
            this.pickUpAddress = pickUpAddress;
        }

        public String getDropAddress() {
            return dropAddress;
        }

        public void setDropAddress(String dropAddress) {
            this.dropAddress = dropAddress;
        }

        public String getPickUpDate() {
            return pickUpDate;
        }

        public void setPickUpDate(String pickUpDate) {
            this.pickUpDate = pickUpDate;
        }

        public String getPickUpTime() {
            return pickUpTime;
        }

        public void setPickUpTime(String pickUpTime) {
            this.pickUpTime = pickUpTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVehicleCompanyName() {
            return vehicleCompanyName;
        }

        public void setVehicleCompanyName(String vehicleCompanyName) {
            this.vehicleCompanyName = vehicleCompanyName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public Object getRtcharge() {
            return rtcharge;
        }

        public void setRtcharge(Object rtcharge) {
            this.rtcharge = rtcharge;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

    }

}


