package com.android.book.ui.model;

import com.android.book.data.db.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;

public class GloabalUtils {
    private static final String KEY = "user";

    public static Map<String, Object> userInfoMap = new HashMap<>();

    public static void saveUser(UserInfo userInfo) {
        if (!userInfoMap.containsKey(KEY)) {
            userInfoMap.put(KEY, userInfo);
        }
    }

    public static UserInfo getUser() {
        return (UserInfo) userInfoMap.get(KEY);
    }

    public static void clear() {
        userInfoMap.remove(KEY);
    }
}
