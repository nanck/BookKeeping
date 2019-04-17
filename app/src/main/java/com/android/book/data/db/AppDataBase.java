package com.android.book.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.android.book.data.db.dao.BillDao;
import com.android.book.data.db.dao.UserDao;
import com.android.book.data.db.entity.Bill;
import com.android.book.data.db.entity.UserInfo;

/**
 * Created by sxshi on 2019-4-17.
 */

@Database(entities = {Bill.class, UserInfo.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "bookkeeping.db";

    public abstract BillDao billDao();

    public abstract UserDao userDao();

    private static AppDataBase mInstance;

    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LOCK) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return mInstance;
    }
}
