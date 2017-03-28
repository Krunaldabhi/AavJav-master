package com.aavjaav.qd.aavjaav.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.view.fragments.MyAccountFragmentTabs.AccountDetailsFragment;
import com.aavjaav.qd.aavjaav.view.fragments.MyAccountFragmentTabs.PaymentDetailsFragment;

public class MyAccountFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_account, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this.getChildFragmentManager());

        mViewPager = (ViewPager) root.findViewById(R.id.fragment_my_bookings_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return root;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AccountDetailsFragment.newInstance();
                case 1:
                    return PaymentDetailsFragment.newInstance();
                default:
                    return AccountDetailsFragment.newInstance();
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
                    return "MY DETAILS";
                case 1:
                    return "PAYMENT DETAILS";
            }
            return null;
        }
    }
}
