package com.github.abdurahmanovart.hellouser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "http://i.imgur.com/DvpvklR.png";
    private static final String EXTRA_USER_LOGIN = "user_login";
    @BindView(R.id.hello_text_view)
    TextView mUserLoginTextView;

    @BindView(R.id.basic_layout)
    LinearLayout mBasicLayout;

    @BindView(R.id.password_layout)
    LinearLayout mPasswordLayout;

    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    private boolean isPasswordCorrect;


    public static Intent createExplicitIntent(Context context, String userLogin) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_USER_LOGIN, userLogin);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        String userLogin = "Hello,";
        if (savedInstanceState == null) {
            userLogin += getIntent().getExtras().getString(EXTRA_USER_LOGIN, "world");

        } else {
            userLogin += Utils.readUserLogin(this);
        }
        mUserLoginTextView.setText(userLogin);

        ImageView imageView = (ImageView) findViewById(R.id.android_image_view);

        Picasso.with(this)
                .load(IMAGE_URL)
                .resize(500, 500)
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error)
                .into(imageView);
    }

    @Override
    public void onBackPressed() {
        if (Utils.readIsAlreadyLoggedIn(this))
            super.onBackPressed();
    }

    @OnTextChanged(value = R.id.password_edit_text,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordInput(Editable editable) {
        isPasswordCorrect = Utils.isPasswordMatching(MainActivity.this, editable.toString());
        if (isPasswordCorrect) {
            showMainLayout();
        }
    }


    //>>> private methods

    private void showMainLayout() {
        Utils.hideKeyBoard(mPasswordEditText, getApplicationContext());
        mBasicLayout.setVisibility(View.VISIBLE);
        mPasswordLayout.setVisibility(View.GONE);
    }


}
