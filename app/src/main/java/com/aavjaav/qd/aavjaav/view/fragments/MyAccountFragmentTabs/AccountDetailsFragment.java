package com.aavjaav.qd.aavjaav.view.fragments.MyAccountFragmentTabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;
import com.aavjaav.qd.aavjaav.view.activities.AccountDetailsActivity;
import com.aavjaav.qd.aavjaav.view.activities.ChangePasswordActivity;
import com.aavjaav.qd.aavjaav.view.activities.LoginActivity;

/**
 * Created by Daniel on 2/22/2017.
 */

public class AccountDetailsFragment extends Fragment {

    LinearLayout mChangePassword;
    LinearLayout mAccountDetails;
    FrameLayout mSignOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_details, container, false);

        mChangePassword = (LinearLayout) root.findViewById(R.id.fragment_account_change_password);
        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePassword = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(changePassword);
            }
        });

        mAccountDetails = (LinearLayout) root.findViewById(R.id.fragment_account_details);
        mAccountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountDetails = new Intent(getActivity(), AccountDetailsActivity.class);
                startActivity(accountDetails);
            }
        });

        mSignOut = (FrameLayout) root.findViewById(R.id.fragment_account_details_sign_out);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });


        return root;
    }

    private void performLogout() {
        String[] keysToRemove = new String[]{
                SharedPrefHelper.Key.FIRST_NAME,
                SharedPrefHelper.Key.LAST_NAME,
                SharedPrefHelper.Key.EMAIL_ADDRESS,
                SharedPrefHelper.Key.USER_ID};
        SharedPrefHelper.getInstance(getContext()).remove(keysToRemove);
        SharedPrefHelper.getInstance(getContext()).setIsLogged(false);

        Intent loginActivity = new Intent(getContext(), LoginActivity.class);
        loginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        loginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(loginActivity);
        getActivity().finish();
    }


    public static AccountDetailsFragment newInstance() {

        Bundle args = new Bundle();

        AccountDetailsFragment fragment = new AccountDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
