package com.aavjaav.qd.aavjaav.model.storage;

import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarResponse;

import java.util.ArrayList;

public class SingletonStorage {
    private ArrayList<BookingsResponse.Result> currentBookings;
    private ArrayList<BookingsResponse.Result> previousBookings;
    private ArrayList<SearchCarResponse.Result> carResults;

    private static SingletonStorage ourInstance = new SingletonStorage();

    public static SingletonStorage getInstance() {
        return ourInstance;
    }

    private SingletonStorage() {
        currentBookings = new ArrayList<BookingsResponse.Result>();
        previousBookings = new ArrayList<BookingsResponse.Result>();
        carResults = new ArrayList<SearchCarResponse.Result>();
    }

    public ArrayList<BookingsResponse.Result> getCurrentBookings() {
        return currentBookings;
    }

    public void setCurrentBookings(ArrayList<BookingsResponse.Result> currentBookings) {
        this.currentBookings.clear();
        this.currentBookings.addAll(currentBookings);
    }

    public ArrayList<BookingsResponse.Result> getPreviousBookings() {
        return previousBookings;
    }

    public void setPreviousBookings(ArrayList<BookingsResponse.Result> previousBookings) {
        this.previousBookings.clear();
        this.previousBookings.addAll(previousBookings);
    }

    public ArrayList<SearchCarResponse.Result> getCarResults() {
        return carResults;
    }

    public void setCarResults(ArrayList<SearchCarResponse.Result> results) {
        carResults.clear();
        carResults.addAll(results);
    }

}
