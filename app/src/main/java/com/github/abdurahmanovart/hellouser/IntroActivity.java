package com.github.abdurahmanovart.hellouser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.redmadrobot.inputmask.MaskedTextChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class IntroActivity extends AppCompatActivity {

    private static final int LOGIN_MIN_LENGTH = 5;
    private static final int PASSWORD_MIN_LENGTH = 8;

    @BindView(R.id.hint_text_view)
    TextView mHintTextView;

    @BindView(R.id.login_button)
    Button mLoginButton;

    @BindView(R.id.login_edit_text)
    EditText mLoginEditText;

    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    @BindView(R.id.phone_edit_text)
    EditText mPhoneEditText;

    boolean mIsLoginFieldCorrect = false;
    boolean mIsPhoneFieldCorrect = false;
    boolean mIsPasswordCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if (Utils.readIsAlreadyLoggedIn(this)) {
            goToMainActivity();
        }
        ButterKnife.bind(this);
        initPhoneFieldMask();
        setLoginButtonEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    @OnTextChanged(value = R.id.login_edit_text,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterLoginInput(Editable editable) {
        mIsLoginFieldCorrect = editable.length() >= LOGIN_MIN_LENGTH;
    }


    @OnClick(R.id.login_button)
    void login() {
        if (isInputDataCorrect()) {
            saveData();
            goToMainActivity();
        } else {
            Crouton.makeText(this, getString(R.string.invalid_input_data_message), Style.ALERT).show();
        }
    }

    private void saveData() {
        Utils.writeUserLogin(this, mLoginEditText.getText().toString());
        Utils.writeIsAlreadyLoggedIn(this, true);
    }

    @OnTextChanged(value = R.id.password_edit_text,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordInput(Editable editable) {
        mIsPasswordCorrect = editable.length() >= PASSWORD_MIN_LENGTH;
        if (mIsPasswordCorrect) {
            mPasswordEditText.setError(null);
            Utils.writePassword(IntroActivity.this, mPasswordEditText.getText().toString());
        } else {
            mPasswordEditText.setError("password is easy! minimum " + PASSWORD_MIN_LENGTH + " digits");
        }
    }

    //region private methods

    private void initPhoneFieldMask() {
        final MaskedTextChangedListener listener = new MaskedTextChangedListener(
                "+7 ([000]) [000] [00] [00]",
                true,
                mPhoneEditText,
                null,
                new ValueListener()
        );
        mPhoneEditText.addTextChangedListener(listener);
        mPhoneEditText.setOnFocusChangeListener(listener);
        mPhoneEditText.setHint(listener.placeholder());
    }

    private boolean isInputDataCorrect() {
        return mIsLoginFieldCorrect && mIsPhoneFieldCorrect && mIsPasswordCorrect;
    }

    private void setLoginButtonEnabled(boolean enabled) {
        mLoginButton.setEnabled(enabled);
    }

    private void goToMainActivity() {
        startActivity(MainActivity.createExplicitIntent(getApplicationContext(), Utils.readUserLogin(this)));
    }

    //endregion private methods

    private class ValueListener implements MaskedTextChangedListener.ValueListener {
        @Override
        public void onTextChanged(boolean maskFilled, String value) {
            mIsPhoneFieldCorrect = maskFilled;
            if (maskFilled) {
                setLoginButtonEnabled(true);
                mHintTextView.setVisibility(View.INVISIBLE);
                Utils.hideKeyBoard(mPhoneEditText, getApplicationContext());
            } else {
                setLoginButtonEnabled(false);
                mHintTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
