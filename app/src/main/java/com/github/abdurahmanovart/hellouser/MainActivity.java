package com.github.abdurahmanovart.hellouser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

    private static final String IMAGE_URL = "http://i.imgur.com/DvpvklR.png";

    public static Intent createExplicitIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
