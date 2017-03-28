package com.aavjaav.qd.aavjaav.view.fragments.MyAccountFragmentTabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.cooltechworks.creditcarddesign.CardEditActivity;


public class PaymentDetailsFragment extends Fragment {

    private static final int GET_NEW_CARD = 2;

    private FloatingActionButton mAddPayment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_payment_details, container, false);

        mAddPayment = (FloatingActionButton) root.findViewById(R.id.fragment_payment_add_payment);
        mAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CardEditActivity.class);
                startActivityForResult(intent, GET_NEW_CARD);
            }
        });

        return root;
    }


    public static PaymentDetailsFragment newInstance() {

        Bundle args = new Bundle();

        PaymentDetailsFragment fragment = new PaymentDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_NEW_CARD) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getContext(), "Payment method added ", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getContext(), "Card not added..", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
