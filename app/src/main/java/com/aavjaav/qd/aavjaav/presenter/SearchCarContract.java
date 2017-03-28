package com.aavjaav.qd.aavjaav.presenter;

import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarResponse;

import java.util.List;

/**
 * Created by Daniel on 3/14/2017.
 */

public interface SearchCarContract {

    interface SearchCarView {
        void onSuccess(List<SearchCarResponse.Result> result);
        void onFailure();
    }

    interface SearchCarPresenter {
        void searchCar(String city);
    }

}
