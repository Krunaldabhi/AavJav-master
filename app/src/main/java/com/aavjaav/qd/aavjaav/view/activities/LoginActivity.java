package com.aavjaav.qd.aavjaav.view.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aavjaav.qd.aavjaav.R;
import com.aavjaav.qd.aavjaav.model.storage.SharedPrefHelper;
import com.aavjaav.qd.aavjaav.presenter.LoginContract;
import com.aavjaav.qd.aavjaav.presenter.LoginPresenter;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_GOOGLE_SIGN = 50;
    private static final int REQUEST_REGISTER = 51;

    private static final String TAG = "LoginActivity";
    private EditText mEmail;
    private EditText mPassword;
    private Button mLogin;
    private TextView mForgotPassword;
    private TextView mRegister;
    private ImageView mFacebook;
    private ImageView mGoogle;

    private LoginPresenter mPresenter;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;

    private ProgressDialog mProgress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkShouldShowIntro();
        checkIsLoggedIn();
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this, LoginActivity.this);

        initUI();
        initFacebook();
        initGooglePlus();
    }

    private void initUI() {
        mProgress = new ProgressDialog(LoginActivity.this);
        mProgress.setCancelable(false);

        mEmail = (EditText) findViewById(R.id.login_email);
        mPassword = (EditText) findViewById(R.id.login_passowrd);

        mLogin = (Button) findViewById(R.id.login_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Email is empty..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Password is empty..", Toast.LENGTH_SHORT).show();
                    return;
                }
                mProgress.show();
                mPresenter.doLogin(email, pass);
            }
        });

        mForgotPassword = (TextView) findViewById(R.id.login_forgot_password);
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRegister = (TextView) findViewById(R.id.login_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doRegister();
            }
        });

        mFacebook = (ImageView) findViewById(R.id.login_facebook);
        mFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(
                        LoginActivity.this,
                        Arrays.asList("email", "public_profile")
                );
            }
        });

        mGoogle = (ImageView) findViewById(R.id.login_google);
        mGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, REQUEST_GOOGLE_SIGN);
            }
        });
    }

    //TODO Send information to server when logged with facebook account.
    private void initFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(
                callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // Handle success
                        Log.d(TAG, "onFbSuccess: ");
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "onFbCancel: User canceled Login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "onFbLoginError: ", exception);
                    }
                }
        );
    }

    private void initGooglePlus() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    //TODO Change implementation on LoginSuccess
    @Override
    public void onLoginSuccess() {
        //Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
        showHomeActivity();
        mProgress.dismiss();
    }

    private void showHomeActivity() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }

    @Override
    public void onLoginFailure(String errorMsg) {
        Toast.makeText(this, "Error logging in, please try again.", Toast.LENGTH_SHORT).show();
        mProgress.dismiss();

    }

    @Override
    public void showRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_REGISTER);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void showRetreivePass() {

    }

    //TODO Send information to server when logged with google account.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GOOGLE_SIGN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        if (requestCode == REQUEST_REGISTER) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    String result = data.getStringExtra(RegisterActivity.EXTRA_SUCCESS);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d(TAG, "Error", e);
                }
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            Log.i(TAG, "handleSignInResult: Google Success");
        } else {
            Log.i(TAG, "handleSignInResult: Google Failure");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiConnection failed" + connectionResult.getErrorMessage());
    }

    private void checkIsLoggedIn() {
        if (SharedPrefHelper.getInstance(this).getIsLogged()) {
            showHomeActivity();
        }
    }

    private void checkShouldShowIntro() {
        if (SharedPrefHelper.getInstance(this).getShouldShowIntro()) {
            Intent showIntro = new Intent(LoginActivity.this, SplashActivity.class);
            startActivity(showIntro);
            finish();
        }
    }
}
