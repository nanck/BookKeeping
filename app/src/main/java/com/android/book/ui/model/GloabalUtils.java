package com.android.book.ui.model;

import android.content.Context;
import com.android.book.utilitles.PreferenceManager;

public class GloabalUtils {

    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public static void setUserName(Context context, String userName) {
        PreferenceManager.getInstance(context).putString(KEY_NAME, userName);
    }

    public static void setUserPhone(Context context, String phoneNo) {
        PreferenceManager.getInstance(context).putString(KEY_PHONE, phoneNo);
    }

    public static String getUserNme(Context context) {
        return PreferenceManager.getInstance(context).getString(KEY_NAME, "");
    }

    public static String getUserPhone(Context context) {
        return PreferenceManager.getInstance(context).getString(KEY_PHONE, "");
    }

    public static void clear(Context context) {
        PreferenceManager.getInstance(context).remove(KEY_NAME);
        PreferenceManager.getInstance(context).remove(KEY_PHONE);
    }
}
