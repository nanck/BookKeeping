package com.android.book.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.android.book.data.db.entity.Bill;

import java.util.List;

/**
 * Created by sxshi on 2019-4-17.
 */

@Dao
public interface BillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBill(Bill bill);

    @Query("DELETE FROM t_bill where phone_no=:phone")
    void delete(String phone);

    @Query("SELECT * FROM  t_bill where phone_no=:phone")
    LiveData<List<Bill>> getBills(String phone);

    @Query("SELECT * FROM t_bill WHERE phone_no=:phone and add_time BETWEEN datetime(date(datetime('now',strftime('-%w day','now'))),'+1 second') AND datetime(date(datetime('now',(6 - strftime('%w day','now'))||' day','1 day')),'-1 second')")
    LiveData<List<Bill>> getWeekBills(String phone);

    @Query("SELECT * FROM t_bill WHERE phone_no=:phone and  add_time BETWEEN datetime('now','start of month','+1 second') and datetime('now','start of month','+1 month','-1 second')")
    LiveData<List<Bill>> getMonthBills(String phone);
}
