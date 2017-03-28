package com.aavjaav.qd.aavjaav.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.presenter.FeedbackContract;
import com.aavjaav.qd.aavjaav.presenter.FeedbackPresenter;

/**
 * Created by Daniel on 3/3/2017.
 */

public class FeedbackFragment extends Fragment implements FeedbackContract.FeedbackView {


    private EditText mMessage;
    private TextView mMessageButton;
    private FeedbackPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_feedback, container, false);

        mMessage = (EditText) root.findViewById(R.id.fragment_feedback_message);
        mMessageButton = (TextView) root.findViewById(R.id.fragment_feedback_send_message);
        mMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FeedbackPresenter(this, getContext());
    }

    private void sendMessage() {
        if (TextUtils.isEmpty(mMessage.getText().toString())) {
            Toast.makeText(getContext(), "Message is empty..", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.sendMessage(mMessage.getText().toString());
    }

    @Override
    public void onSuccess(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        mMessage.setText("");
    }

    @Override
    public void onFailure(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}
