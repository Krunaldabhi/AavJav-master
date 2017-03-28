package com.aavjaav.qd.aavjaav.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aavjaav.qd.aavjaav.R;


public class FilterActivity extends AppCompatActivity {

    public static final String EXTRA_FILTER = "filter_extra";
    public static final String[] CAR_TYPES = new String[]{"Hatchback", "Suv", "Sedan", "Luxury"};
    private CheckBox mHatchback, mSuv, mSedan, mLuxury;
    private TextView mApply;
    boolean[] filterResults;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Filter by");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getBooleanArrayExtra(EXTRA_FILTER) != null) {
            filterResults = getIntent().getBooleanArrayExtra(EXTRA_FILTER);
        }


        initUI();
    }

    private void initUI() {
        mHatchback = (CheckBox) findViewById(R.id.hatchback);
        mSuv = (CheckBox) findViewById(R.id.suv);
        mSedan = (CheckBox) findViewById(R.id.sedan);
        mLuxury = (CheckBox) findViewById(R.id.luxury);

        if (filterResults != null) {
            mHatchback.setChecked(filterResults[0]);
            mSuv.setChecked(filterResults[1]);
            mSedan.setChecked(filterResults[2]);
            mLuxury.setChecked(filterResults[3]);

        }

        mApply = (TextView) findViewById(R.id.activity_filter_apply);
        mApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] result = new boolean[]{
                        mHatchback.isChecked(),
                        mSuv.isChecked(),
                        mSedan.isChecked(),
                        mLuxury.isChecked()};

                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_FILTER, result);
                setResult(RESULT_OK, resultIntent);
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        return super.onOptionsItemSelected(item);
    }
}
