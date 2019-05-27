package com.android.book.ui.model;

import android.content.Context;
import android.content.Intent;
import com.android.book.ui.BasicInfoActivity;
import com.android.book.ui.LoginActivity;
import com.android.book.ui.NewBillActivity;
import com.android.book.ui.SettingActivity;

public class RouteManager {

    private Status userStatus = new LogoutStatus();

    private volatile static RouteManager instance;

    private static final Object lock = new Object();

    private RouteManager() {

    }

    public static RouteManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new RouteManager();
                }
            }
        }
        return instance;
    }

    public Status getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(Status userStatus) {
        this.userStatus = userStatus;
    }

    public void addBill(Context context) {
        userStatus.addBill(context);
    }

    public static class LoginStatus implements Status {

        @Override
        public void addBill(Context context) {
            Intent intent = new Intent(context, NewBillActivity.class);
            context.startActivity(intent);
        }

        @Override
        public void lookBasicInfo(Context context) {
            Intent intent = new Intent(context, BasicInfoActivity.class);
            context.startActivity(intent);
        }

        @Override
        public void doSetting(Context context) {
            Intent intent = new Intent(context, SettingActivity.class);
            context.startActivity(intent);
        }
    }

    public static class LogoutStatus implements Status {

        @Override
        public void addBill(Context context) {
            doLogin(context);
        }

        @Override
        public void lookBasicInfo(Context context) {
            doLogin(context);
        }

        @Override
        public void doSetting(Context context) {
            doLogin(context);
        }

        private void doLogin(Context context) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }
}
