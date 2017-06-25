package com.github.abdurahmanovart.hellouser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    private static final String IMAGE_URL = "http://i.imgur.com/DvpvklR.png";
    private static final String EXTRA_USER_LOGIN = "user_login";
    @BindView(R.id.hello_text_view)
    TextView mUserLoginTextView;


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
        String userLogin = "";
        if (savedInstanceState == null) {
            userLogin = getIntent().getExtras().getString(EXTRA_USER_LOGIN, "world");

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
}
