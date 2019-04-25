package com.android.book.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.android.book.AppExecutors;
import com.android.book.data.db.AppDataBase;
import com.android.book.data.db.dao.BillDao;
import com.android.book.data.db.dao.PayTypeDao;
import com.android.book.data.db.dao.TypeDao;
import com.android.book.data.db.dao.UserDao;
import com.android.book.data.db.entity.Bill;
import com.android.book.data.db.entity.PayType;
import com.android.book.data.db.entity.Type;
import com.android.book.data.db.entity.UserInfo;

import java.util.List;

/**
 * Created by sxshi on 2019-4-17.
 */

public class DataRepository {
    private UserDao userDao;

    private BillDao billDao;

    private TypeDao typeDao;

    private PayTypeDao payTypeDao;

    private AppExecutors executors;

    public DataRepository(Application application, AppExecutors executors) {
        AppDataBase dataBase = AppDataBase.getInstance(application);
        userDao = dataBase.userDao();
        billDao = dataBase.billDao();
        typeDao = dataBase.typeDao();
        payTypeDao = dataBase.payTypeDao();
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

    public UserInfo getUserInfo(String userName, String pwd) {
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


    public LiveData<List<Type>> getTypes() {
        return typeDao.getAllTTypes();
    }

    public LiveData<List<PayType>> getPayTypes() {
        return payTypeDao.getAllPayTypes();
    }
}
