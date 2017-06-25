package com.github.abdurahmanovart.hellouser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Abdurakhmanov on 14.06.17
 */

public class Utils {

    private static final String IS_LOGGED_IN = "logged_in";
    private static final String USER_LOGIN = "user_login";

    public static void hideKeyBoard(@NonNull View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void writeIsAlreadyLoggedIn(Activity activity, boolean loggedIn) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_LOGGED_IN, loggedIn);
        editor.apply();
    }

    public static boolean readIsAlreadyLoggedIn(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        boolean defaultValue = false;
        return sharedPref.getBoolean(IS_LOGGED_IN, defaultValue);

    }

    public static void writeUserLogin(Activity activity, @NonNull String userLogin) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_LOGIN, " " + userLogin);
        editor.apply();
    }

    public static String readUserLogin(Activity activity) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "world";
        return " " + sharedPreferences.getString(USER_LOGIN, defaultValue);

    }

}
