package com.android.book.utilitles;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sxshi on 2019-4-10.
 */

public class Util {

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Get the directory for the app's private pictures directory.
     *
     * @param context
     * @param albumName picture directory
     * @return
     */
    public static File getPrivateAlbumStorageDir(Context context, String albumName) {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("ssx", "Directory not created");
        }
        return file;
    }

    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
    };

    /**
     * format date show with yyyy-MM-dd HH:mm:ss
     *
     * @param date current date
     * @return
     */
    public static String formatDate(Date date) {
        return formatter.get().format(date);
    }

    /**
     * 金额保留两位小数
     *
     * @param amount
     * @return
     */
    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        return df.format(amount);
    }

    /**
     * 手机验证
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneValid(String phone) {
        String check = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        return !TextUtils.isEmpty(phone) && matcher.matches();
    }

    /**
     * 邮箱验证
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return !TextUtils.isEmpty(email) && matcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 5;
    }
}
