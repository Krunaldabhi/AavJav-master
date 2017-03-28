package com.aavjaav.qd.aavjaav.view.fragments.DialogFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment {

    TimePickerDialog.OnTimeSetListener mTimeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), mTimeListener, hour, minute, true);
    }

    public void setCallback(TimePickerDialog.OnTimeSetListener listener) {
        mTimeListener = listener;
    }

}
