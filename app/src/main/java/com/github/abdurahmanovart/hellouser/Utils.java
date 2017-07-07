package com.github.abdurahmanovart.hellouser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.jasypt.util.password.BasicPasswordEncryptor;


/**
 * @author Abdurakhmanov on 14.06.17
 */

public class Utils {

    private static final String IS_LOGGED_IN = "logged_in";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASSWORD = "user_password";

    private static BasicPasswordEncryptor mEncryptor;

    private Utils() {
        throw new IllegalStateException("Can't create object");
    }

    public static void hideKeyBoard(@NonNull View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void writeIsAlreadyLoggedIn(Activity activity, boolean loggedIn) {
        SharedPreferences.Editor editor = getSharedPreferencesPrivateEditor(activity);
        editor.putBoolean(IS_LOGGED_IN, loggedIn);
        editor.apply();
    }

    public static boolean readIsAlreadyLoggedIn(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getBoolean(IS_LOGGED_IN, false);
    }

    public static void writeUserLogin(Activity activity, @NonNull String userLogin) {
        SharedPreferences.Editor editor = getSharedPreferencesPrivateEditor(activity);
        editor.putString(USER_LOGIN, userLogin);
        editor.apply();
    }

    public static String readUserLogin(Activity activity) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "world";
        return " " + sharedPreferences.getString(USER_LOGIN, defaultValue);
    }

    public static void writePassword(Activity activity, @NonNull String password) {
        SharedPreferences.Editor editor = getSharedPreferencesDefaultEditor(activity);
        editor.putString(USER_PASSWORD, makeEncryptedPassword(password));
        editor.apply();
    }

    public static boolean isPasswordMatching(Activity activity, @NonNull String newPassword) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String defValue = "qwea#@<wmel213@aA";
        String savedPasswordEncrypted = sharedPreferences.getString(USER_PASSWORD, defValue);
        return getEncryptor().checkPassword(newPassword, savedPasswordEncrypted);
    }

    private static String makeEncryptedPassword(String password) {
        return getEncryptor().encryptPassword(password);
    }

    private static BasicPasswordEncryptor getEncryptor() {
        if (mEncryptor == null) {
            mEncryptor = new BasicPasswordEncryptor();
        }
        return mEncryptor;
    }

    private static SharedPreferences.Editor getSharedPreferencesPrivateEditor(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.edit();
    }

    private static SharedPreferences.Editor getSharedPreferencesDefaultEditor(Activity activity) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPref.edit();
    }


}
