package com.aavjaav.qd.aavjaav.presenter;

import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/11/2017.
 */

public interface BookingsContract {

    interface BookingsView {
        void showCurrentBookings(ArrayList<BookingsResponse.Result> current);
        void showPreviousBookings(ArrayList<BookingsResponse.Result> previous);
    }

    interface BookingsPresenter {
        void getBookings(int cid);
    }

}
