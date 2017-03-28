package com.aavjaav.qd.aavjaav.view.fragments.DialogFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import com.aavjaav.qd.aavjaav.R;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener mDateListener;
    long minimumDate;

    public DatePickerFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mDateListener, year, month, day);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMinDate(minimumDate - 1000);

        return datePickerDialog;
    }

    public void setCallback(DatePickerDialog.OnDateSetListener listener) {
        mDateListener = listener;
    }

    public void setMinimumDate(long minimumDate) {
        this.minimumDate = minimumDate;
    }


}
