package com.aavjaav.qd.aavjaav.view.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

import android.widget.TextView;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;
import com.aavjaav.qd.aavjaav.presenter.BookingsContract;
import com.aavjaav.qd.aavjaav.presenter.BookingsPresenter;
import com.aavjaav.qd.aavjaav.view.fragments.MyBookingsFragmentTabs.CurrentBookings;
import com.aavjaav.qd.aavjaav.view.fragments.MyBookingsFragmentTabs.PreviousBookings;

public class MyBookingsFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_bookings, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this.getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) root.findViewById(R.id.fragment_my_bookings_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //TODO Change icon of fab
        /*FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //TODO Make new fragment class for each tab
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CurrentBookings.newInstance();
                case 1:
                    return PreviousBookings.newInstance();
                default:
                    return CurrentBookings.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "CURRENT";
                case 1:
                    return "PREVIOUS";
            }
            return null;
        }
    }
}
