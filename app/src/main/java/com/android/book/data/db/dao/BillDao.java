package com.android.book.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.android.book.data.db.entity.Bill;

import java.util.List;

/**
 * Created by sxshi on 2019-4-17.
 */

@Dao
public interface BillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBill(Bill bill);

    @Query("DELETE FROM t_bill")
    void delete();

    @Query("SELECT * FROM  t_bill")
    LiveData<List<Bill>> getBills();

    @Query("SELECT * FROM t_bill WHERE add_time BETWEEN datetime(date(datetime('now',strftime('-%w day','now'))),'+1 second') AND datetime(date(datetime('now',(6 - strftime('%w day','now'))||' day','1 day')),'-1 second')")
    LiveData<List<Bill>>getWeekBills();

    @Query("SELECT * FROM t_bill WHERE add_time BETWEEN datetime('now','start of month','+1 second') and datetime('now','start of month','+1 month','-1 second')")
    LiveData<List<Bill>>getMonthBills();
}
