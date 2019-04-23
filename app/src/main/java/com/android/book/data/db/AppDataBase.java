package com.android.book.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.android.book.AppExecutors;
import com.android.book.data.db.dao.BillDao;
import com.android.book.data.db.dao.PayTypeDao;
import com.android.book.data.db.dao.TypeDao;
import com.android.book.data.db.dao.UserDao;
import com.android.book.data.db.entity.Bill;
import com.android.book.data.db.entity.PayType;
import com.android.book.data.db.entity.Type;
import com.android.book.data.db.entity.UserInfo;

/**
 * Created by sxshi on 2019-4-17.
 */

@Database(entities = {Bill.class, UserInfo.class, PayType.class, Type.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static final String TAG = "db";

    private static final String DATABASE_NAME = "bookkeeping.db";

    public abstract BillDao billDao();

    public abstract UserDao userDao();

    public abstract TypeDao typeDao();

    public abstract PayTypeDao payTypeDao();

    private static AppDataBase mInstance;

    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LOCK) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, "----start init data----------");
                                            initTypeData();
                                            initPayTypeData();
                                            Log.d(TAG, "----end   init data----------");
                                        }
                                    });
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化 支付类型数据
     */
    private static void initPayTypeData() {
        //0支付宝，1微信，2刷卡，3现金
        PayType payType = new PayType();
        payType.setPayKey(0);
        payType.setPayValue("支付宝");
        mInstance.payTypeDao().insertType(payType);
        payType.setPayKey(1);
        payType.setPayValue("微信");
        mInstance.payTypeDao().insertType(payType);
        payType.setPayKey(2);
        payType.setPayValue("刷卡");
        mInstance.payTypeDao().insertType(payType);
        payType.setPayKey(3);
        payType.setPayValue("现金");
        mInstance.payTypeDao().insertType(payType);
    }

    /**
     * 0购物，1旅游，2吃饭，3娱乐，4出行
     * 初始化 消费类型
     */
    private static void initTypeData() {
        Type type = new Type();
        type.setKey(0);
        type.setValue("购物");
        mInstance.typeDao().insertType(type);
        type.setKey(1);
        type.setValue("旅游");
        mInstance.typeDao().insertType(type);
        type.setKey(2);
        type.setValue("吃饭");
        mInstance.typeDao().insertType(type);
        type.setKey(3);
        type.setValue("娱乐");
        mInstance.typeDao().insertType(type);
        type.setKey(4);
        type.setValue("出行");
        mInstance.typeDao().insertType(type);
    }

    private static Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE t_user ADD COLUMN type_test TEXT");
        }
    };
}
