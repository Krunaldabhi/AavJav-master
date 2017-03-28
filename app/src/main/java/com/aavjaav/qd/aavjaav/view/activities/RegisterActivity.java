package com.aavjaav.qd.aavjaav.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.presenter.RegisterContract;
import com.aavjaav.qd.aavjaav.presenter.RegisterPresenter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Daniel on 2/14/2017.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView {

    public static final String EXTRA_SUCCESS = "success_extra";
    private static final String RESULT_SUCCESS = "Account created successfully";
    private static final String RESULT_FAILURE = "Account failed to create";

    private EditText mEmailAddress;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mRegisterButton;
    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPresenter = new RegisterPresenter(this);

        initUI();
    }

    private void initUI() {
        mEmailAddress = (EditText) findViewById(R.id.register_email);

        mPassword = (EditText) findViewById(R.id.register_passowrd);
        mPassword.setTypeface(mEmailAddress.getTypeface());

        mConfirmPassword = (EditText) findViewById(R.id.register_passowrd_retype);
        mConfirmPassword.setTypeface(mEmailAddress.getTypeface());

        mRegisterButton = (Button) findViewById(R.id.register_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailAddress.getText().toString();
                String password = mPassword.getText().toString();
                String confirm = mConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Email is empty..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(RegisterActivity.this, "Email format invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!TextUtils.equals(password, confirm)){
                    Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.doRegister(email, password);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onSuccess() {
        Intent result = new Intent();
        result.putExtra(EXTRA_SUCCESS, RESULT_SUCCESS);
        setResult(Activity.RESULT_OK, result);
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    public void onFailure() {
        getIntent().putExtra(EXTRA_SUCCESS, RESULT_FAILURE);
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
