package com.aavjaav.qd.aavjaav.view.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.utils.DateUtils;
import com.aavjaav.qd.aavjaav.view.activities.CarsResultActivity;
import com.aavjaav.qd.aavjaav.view.fragments.DialogFragments.DatePickerFragment;
import com.aavjaav.qd.aavjaav.view.fragments.DialogFragments.TimePickerFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class BookCarFragment extends Fragment {

    private static final String TAG = "BookCarFragment";
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 99;
    private LinearLayout mPickStartDate;
    private LinearLayout mPickStartTime;
    private TextView mStartDay;
    private TextView mStartDate;
    private TextView mStartMonth;

    private LinearLayout mPickEndDate;
    private LinearLayout mPickEndTime;
    private TextView mEndDay;
    private TextView mEndDate;
    private TextView mEndMonth;

    private TextView mStartTime;
    private TextView mEndTime;

    private EditText mPickLocationEdit;

    private TextView mSearch;

    private long startMinimumDate;
    private long endMinimumDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_car, container, false);

        initUI(root);

        return root;
    }


    private void initUI(View root) {
        mPickStartDate = (LinearLayout) root.findViewById(R.id.fragment_book_car_pick_start_date);
        mPickStartTime = (LinearLayout) root.findViewById(R.id.fragment_book_car_pick_start_time);
        mStartDate = (TextView) root.findViewById(R.id.fragment_book_car_start_date);
        mStartDay = (TextView) root.findViewById(R.id.fragment_book_car_start_day);
        mStartMonth = (TextView) root.findViewById(R.id.fragment_book_car_start_month);
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        setStartDate(year, month, day);

        startMinimumDate = c.getTimeInMillis();

        mPickEndDate = (LinearLayout) root.findViewById(R.id.fragment_book_car_pick_end_date);
        mPickEndTime = (LinearLayout) root.findViewById(R.id.fragment_book_car_pick_end_time);
        mEndDate = (TextView) root.findViewById(R.id.fragment_book_car_end_date);
        mEndDay = (TextView) root.findViewById(R.id.fragment_book_car_end_day);
        mEndMonth = (TextView) root.findViewById(R.id.fragment_book_car_end_month);

        setEndDate(year, month, day);
        endMinimumDate = c.getTimeInMillis();

        mStartTime = (TextView) root.findViewById(R.id.fragment_book_car_start_time);
        mEndTime = (TextView) root.findViewById(R.id.fragment_book_car_end_time);

        mPickLocationEdit = (EditText) root.findViewById(R.id.fragment_book_car_pick_location);
        mPickLocationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .build();

                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .setFilter(typeFilter)
                                    .build(getActivity());

                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                    Log.i(TAG, "GooglePlayServices " + e);
                }
            }
        });


        mPickStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(startDateListener, startMinimumDate);
            }
        });
        mPickStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(startTimeListener);
            }
        });

        mPickEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(endDateListener, endMinimumDate);
            }
        });
        mPickEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(endTimeListener);
            }
        });

        mSearch = (TextView) root.findViewById(R.id.fragment_book_car_search);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mPickLocationEdit.getText().toString())) {
                    Toast.makeText(getContext(), "Please pick a location..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mStartTime.getText().toString()) || TextUtils.isEmpty(mEndTime.getText().toString())) {
                    Toast.makeText(getContext(), "Please pick a valid time..", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent carResults = new Intent(getContext(), CarsResultActivity.class);
                carResults.putExtra(CarsResultActivity.EXTRA_CITY, mPickLocationEdit.getText().toString());
                startActivity(carResults);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.i(TAG, "Place: " + place.getName());
                mPickLocationEdit.setText(place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void showDatePicker(DatePickerDialog.OnDateSetListener listener, long minimumDate) {
        DatePickerFragment date = new DatePickerFragment();
        date.setCallback(listener);
        date.setMinimumDate(minimumDate);
        date.show(getFragmentManager(), "Date Picker");
    }

    private void showTimePicker(TimePickerDialog.OnTimeSetListener listener) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setCallback(listener);
        timePickerFragment.show(getFragmentManager(), "TimePicker");
    }


    DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month += 1;
            setEndDate(year, month, dayOfMonth);
        }
    };

    DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            setStartDate(year, (month + 1), dayOfMonth);
            endMinimumDate = DateUtils.getDate(year, month, (dayOfMonth + 1)).getTime();
        }
    };

    TimePickerDialog.OnTimeSetListener startTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setStartTime(hourOfDay, minute);
        }
    };

    TimePickerDialog.OnTimeSetListener endTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setEndTime(hourOfDay, minute);
        }
    };

    private void setStartDate(int year, int month, int dayOfMonth) {
        String startDayName = DateUtils.dayName(dayOfMonth + "/" + month + "/" + year, "dd/MM/yyyy");
        String startMonthName = DateUtils.monthName(dayOfMonth + "/" + month + "/" + year, "dd/MM/yyyy");

        mStartDate.setText(String.valueOf(dayOfMonth));
        mStartDay.setText(startDayName);
        mStartMonth.setText(startMonthName);
    }

    private void setEndDate(int year, int month, int dayOfMonth) {
        String endDayName = DateUtils.dayName(dayOfMonth + "/" + month + "/" + year, "dd/MM/yyyy");
        String endMonthName = DateUtils.monthName(dayOfMonth + "/" + month + "/" + year, "dd/MM/yyyy");

        mEndDate.setText(String.valueOf(dayOfMonth));
        mEndDay.setText(endDayName);
        mEndMonth.setText(endMonthName);
    }

    private void setStartTime(int hour, int minute) {
        mStartTime.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute));
    }

    private void setEndTime(int hour, int minute) {
        mEndTime.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute));
    }

    public static BookCarFragment newInstance() {

        Bundle args = new Bundle();

        BookCarFragment fragment = new BookCarFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
