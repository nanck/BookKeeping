package com.android.book.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.android.book.R;
import com.android.book.data.db.entity.UserInfo;
import com.android.book.utilitles.Util;
import com.android.book.viewmodel.UserViewModel;

public class RegisiterActivity extends AppCompatActivity {
    private static final String TAG = "ssx";
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView, mConfirmPassword, mPhoneView;
    private View mProgressView;
    private View mLoginFormView;
    private UserRegistTask mRegistTask = null;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisiter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.action_regist));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        mPhoneView = findViewById(R.id.phone);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegist();
                    return true;
                }
                return false;
            }
        });

        Button mRegist = findViewById(R.id.email_regist_button);
        mRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegist();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptRegist() {
        if (mRegistTask != null) {
            return;
        }
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confrimPwd = mConfirmPassword.getText().toString();
        String phone = mPhoneView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.prompt_phone));
            focusView = mPhoneView;
            cancel = true;
        } else if (!Util.isPhoneValid(phone)) {
            mPhoneView.setError(getString(R.string.sure_phone));
            focusView = mPhoneView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !Util.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!password.equals(confrimPwd)) {
            mConfirmPassword.setError(getString(R.string.error_confrim_password));
            focusView = mConfirmPassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!Util.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mRegistTask = new UserRegistTask(email, password, phone);
            mRegistTask.execute((Void) null);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegistTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mPhone;

        UserRegistTask(String email, String password, String phone) {
            mEmail = email;
            mPassword = password;
            mPhone = phone;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //login or regiest
            UserInfo userInfo = new UserInfo();
            userInfo.setPassWord(mPassword);
            userInfo.setUserName(mEmail);
            userInfo.setPhoneNumber(mPhone);
            return userViewModel.addUser(userInfo);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegistTask = null;
            showProgress(false);
            if (success) {
                Toast.makeText(RegisiterActivity.this, getString(R.string.regist_success), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(RegisiterActivity.this, getString(R.string.user_existed), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mRegistTask = null;
            showProgress(false);
        }
    }
}
