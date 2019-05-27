package com.android.book.utilitles;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences的工具类
 *
 * @author ssx
 */
public class PreferenceManager {
    private static final String PREFERENCE_NAME = "bookkeeping";
    private static SharedPreferences shareditorPreferences;
    public static Editor editor;
    private static PreferenceManager instance;

    private PreferenceManager(Context context) {
        if (shareditorPreferences == null || editor == null) {
            try {
                shareditorPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
                editor = shareditorPreferences.edit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized PreferenceManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferenceManager.class) {
                if (instance == null) {
                    instance = new PreferenceManager(context.getApplicationContext());
                }
            }

        }
        return instance;
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public int getInt(String key, int value) {
        return shareditorPreferences.getInt(key, value);
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public String getString(String key, String value) {
        return shareditorPreferences.getString(key, value);
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public long getLong(String key, long value) {
        return shareditorPreferences.getLong(key, value);
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public boolean getBoolean(String key, boolean value) {
        return shareditorPreferences.getBoolean(key, value);
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public void putInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public void putString(String key, String value) {
        editor.putString(key, value).commit();
    }

    /**
     * @param key   key
     * @param value value
     * @return 根据key获取value的值
     */
    public void putLong(String key, long value) {
        editor.putLong(key, value).commit();
    }

    /**
     * @param key   key
     * @param value
     * @return 根据key获取value的值
     */
    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    /**
     * 根据key删除数据
     *
     * @param key key
     */
    public void remove(String key) {
        editor.remove(key).commit();
    }

    /**
     * 判断key是否存在
     *
     * @param key key
     */
    public boolean contains(String key) {

        return shareditorPreferences.contains(key);
    }
}
