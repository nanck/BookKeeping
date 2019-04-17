package com.android.book.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.android.book.AppExecutors;
import com.android.book.data.db.AppDataBase;
import com.android.book.data.db.dao.BillDao;
import com.android.book.data.db.dao.UserDao;
import com.android.book.data.db.entity.Bill;
import com.android.book.data.db.entity.UserInfo;

import java.util.List;

/**
 * Created by sxshi on 2019-4-17.
 */

public class DataRepository {
    private UserDao userDao;

    private BillDao billDao;

    private AppExecutors executors;

    public DataRepository(Application application, AppExecutors executors) {
        AppDataBase dataBase = AppDataBase.getInstance(application);
        userDao = dataBase.userDao();
        billDao = dataBase.billDao();
        this.executors = executors;
    }

    public void addUser(final UserInfo userInfo) {
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDao.addUser(userInfo);
            }
        });
    }

    public LiveData<UserInfo> getUserInfo(String userName, String pwd) {
        return userDao.getUser(userName, pwd);
    }

    public void addBill(final Bill bill) {
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                billDao.insertBill(bill);
            }
        });
    }

    public LiveData<List<Bill>> getBills() {
        return billDao.getBills();
    }
}
