package com.github.abdurahmanovart.hellouser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.redmadrobot.inputmask.MaskedTextChangedListener;

public class IntroActivity extends AppCompatActivity {

    private static final int MIN_LOGIN_LENGTH = 5;

    private TextView mHintTextView;
    private Button mLoginButton;
    private EditText mLoginEditText;
    private EditText mPhoneEditText;
    boolean isLoginFieldCorrect = false;
    boolean isPhoneFieldCorrect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        initViews();
        initMaskPhoneField();
        initLoginButton();
        initLoginField();


    }
    //>>> private methods
    private void initLoginField() {
        mLoginEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //no need to implement this method
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //no need to implement this method
            }

            @Override
            public void afterTextChanged(Editable s) {
                isLoginFieldCorrect = s.length() >= MIN_LOGIN_LENGTH;
            }
        });
    }

        private boolean isInputDataCorrect(){
            return isLoginFieldCorrect&&isPhoneFieldCorrect;

        }

    private void initLoginButton() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: 11.06.17 hide keyboard here!
                if(isInputDataCorrect())
                startActivity(MainActivity.createExplictIntent(getApplicationContext()));
                else
                    Toast.makeText(getApplicationContext(),getString(R.string.invalid_input_data_message),Toast.LENGTH_LONG).show();
            }
        });
        mLoginButton.setEnabled(false);
    }


    private void initMaskPhoneField() {
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

    private void initViews() {
        mPhoneEditText = (EditText) findViewById(R.id.edit_text);
        mLoginEditText = (EditText)findViewById(R.id.login_edit_text);
        mHintTextView = (TextView)findViewById(R.id.hint_text_view);
        mLoginButton = (Button)findViewById(R.id.login_button);

        mHintTextView.setText(getString(R.string.hint));
        mHintTextView.setVisibility(View.INVISIBLE);
    }

    //<<< private methods

    private class ValueListener implements MaskedTextChangedListener.ValueListener{

        @Override
        public void onTextChanged(boolean maskFilled, String value) {
            isPhoneFieldCorrect = maskFilled;
        if(maskFilled){
            mLoginButton.setEnabled(true);
            mHintTextView.setVisibility(View.INVISIBLE);
        } else {
            mLoginButton.setEnabled(false);
            mHintTextView.setVisibility(View.VISIBLE);
        }
        }
    }
}
