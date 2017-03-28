package com.aavjaav.qd.aavjaav.presenter;

import android.content.Context;

import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;

/**
 * Created by Daniel on 2/12/2017.
 */

public class IntroPresenter implements IntroContract.IntroPresenter {

    private Context mContext;
    private IntroContract.IntroView mIntroView;
    private boolean mIsFirstTime = false;


    public IntroPresenter(Context mContext, IntroContract.IntroView view) {
        this.mContext = mContext;
        mIntroView = view;
    }

    @Override
    public void isFirstTimeLaunch() {
        mIsFirstTime = SharedPrefHelper.getInstance(mContext).getBoolean(SharedPrefHelper.Key.IS_FIRST_LAUNCH);

        if(mIsFirstTime) {
            mIntroView.showIntro();
        } else {
            mIntroView.showLogin();
        }
    }
}
