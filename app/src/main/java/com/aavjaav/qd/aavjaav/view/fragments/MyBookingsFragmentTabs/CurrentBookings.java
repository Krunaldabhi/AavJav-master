package com.aavjaav.qd.aavjaav.view.fragments.MyBookingsFragmentTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.adapter.BookingsAdapter;
import com.aavjaav.qd.aavjaav.model.pojo.mybookings.BookingsResponse;
import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;
import com.aavjaav.qd.aavjaav.model.storage.SingletonStorage;
import com.aavjaav.qd.aavjaav.presenter.BookingsContract;
import com.aavjaav.qd.aavjaav.presenter.BookingsPresenter;

import java.util.ArrayList;


public class CurrentBookings extends Fragment implements BookingsContract.BookingsView {

    private static final String TAG = "CurrentBookings";
    private RecyclerView mRecyclerView;
    private LinearLayout mAddBookingView;
    private BookingsPresenter mPresenter;
    private BookingsAdapter mAdapter;
    private ArrayList<BookingsResponse.Result> currentBookings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_booking, container, false);
        mPresenter.getBookings(SharedPrefHelper.getInstance(getContext()).getUserId());

        mAddBookingView = (LinearLayout) root.findViewById(R.id.current_booking_empty_list_container);
        if (!SingletonStorage.getInstance().getCurrentBookings().isEmpty()) {
            mAddBookingView.setVisibility(View.GONE);
        }

        currentBookings = SingletonStorage.getInstance().getCurrentBookings();
        mAdapter = new BookingsAdapter(getContext(), currentBookings);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.fragment_previous_booking_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BookingsPresenter(getContext(), this);
    }

    public static CurrentBookings newInstance() {
        Bundle args = new Bundle();

        CurrentBookings fragment = new CurrentBookings();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showCurrentBookings(ArrayList<BookingsResponse.Result> current) {
        mAdapter.swap(current);
        mAddBookingView.setVisibility(View.GONE);
    }

    @Override
    public void showPreviousBookings(ArrayList<BookingsResponse.Result> previous) {

    }
}
