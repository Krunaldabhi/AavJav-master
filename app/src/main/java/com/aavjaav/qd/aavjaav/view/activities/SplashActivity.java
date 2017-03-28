package com.aavjaav.qd.aavjaav.view.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;
import com.andraskindler.parallaxviewpager.ParallaxViewPager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Button mProceedButton;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
    }

    private void initUI() {
        pDialog = new ProgressDialog(SplashActivity.this);
        pDialog.setCancelable(false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ParallaxViewPager parallaxViewPager = (ParallaxViewPager) findViewById(R.id.parallaxviewpager);
        parallaxViewPager.setOverlapPercentage(0.5f);
        parallaxViewPager.setScaleType(ParallaxViewPager.FIT_HEIGHT);

        parallaxViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(parallaxViewPager, true);

        mProceedButton = (Button) findViewById(R.id.intro_button_login);
        mProceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                SharedPrefHelper.getInstance(SplashActivity.this).putShouldShowIntro(false);
                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            }
        });

    }

    private void showProgress() {
        pDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView mIntroText;
        private CharSequence mIntroValue;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
            mIntroText = (TextView) rootView.findViewById(R.id.fragment_splash_intro_text);

            int introNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (introNumber) {
                case 1:
                    mIntroValue = getResources().getText(R.string.intro_text_1);
                    break;
                case 2:
                    mIntroValue = getResources().getText(R.string.intro_text_2);
                    break;
                case 3:
                    mIntroValue = getResources().getText(R.string.intro_text_3);
                    break;
            }

            mIntroText.setText(mIntroValue);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
            }
            return null;
        }
    }
}
