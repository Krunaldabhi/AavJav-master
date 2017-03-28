package com.aavjaav.qd.aavjaav.view.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.adapter.CarsResultAdapter;
import com.aavjaav.qd.aavjaav.model.pojo.searchcar.SearchCarResponse;
import com.aavjaav.qd.aavjaav.model.storage.SingletonStorage;
import com.aavjaav.qd.aavjaav.presenter.SearchCarContract;
import com.aavjaav.qd.aavjaav.presenter.SearchCarPresenter;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class CarsResultActivity extends AppCompatActivity implements SearchCarContract.SearchCarView {

    public static final String EXTRA_CITY = "city_extra";
    public static final int REQUEST_FILTER = 50;
    private String mCity;
    private SearchCarPresenter mPresenter;
    private RecyclerView mRecycler;
    private ArrayList<SearchCarResponse.Result> mCars;
    private CarsResultAdapter mAdapter;
    private TextView mFilter;
    private LinearLayout mSort;
    private boolean[] filterResults;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mProgress = new ProgressDialog(CarsResultActivity.this);
        mProgress.setCancelable(false);
        mProgress.show();

        mCars = SingletonStorage.getInstance().getCarResults();
        mAdapter = new CarsResultAdapter(mCars, this);

        mPresenter = new SearchCarPresenter(this, this);
        mCity = getIntent().getStringExtra(EXTRA_CITY);
        mPresenter.searchCar(mCity);

        mRecycler = (RecyclerView) findViewById(R.id.activity_cars_result_recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);

        mFilter = (TextView) findViewById(R.id.activity_cars_result_filter);
        mFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterActivity = new Intent(CarsResultActivity.this, FilterActivity.class);
                if (filterResults != null) {
                    filterActivity.putExtra(FilterActivity.EXTRA_FILTER, filterResults);
                }
                startActivityForResult(filterActivity, REQUEST_FILTER);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        mSort = (LinearLayout) findViewById(R.id.activity_cars_result_sort_method);
        mSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FILTER) {
            if (resultCode == RESULT_OK) {
                StringBuilder sb = new StringBuilder();
                filterResults = data.getBooleanArrayExtra(FilterActivity.EXTRA_FILTER);

                for (int i = 0; i < filterResults.length; i++) {
                    if (filterResults[i]) {
                        sb.append(FilterActivity.CAR_TYPES[i]);
                        sb.append("-");
                    }
                }
                mAdapter.getFilter().filter(sb.toString());


            } else {
                Toast.makeText(this, "Error filtering results..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(item));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onSuccess(List<SearchCarResponse.Result> result) {
        mProgress.dismiss();
        mAdapter.swap((ArrayList<SearchCarResponse.Result>) result);
    }

    @Override
    public void onFailure() {
        mProgress.dismiss();
        Toast.makeText(this, "There's been an error. Try again.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
