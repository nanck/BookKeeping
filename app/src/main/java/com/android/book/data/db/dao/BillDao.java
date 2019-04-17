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
}
