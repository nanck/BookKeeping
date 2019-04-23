package com.android.book.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.android.book.data.db.entity.PayType;
import com.android.book.data.db.entity.Type;

import java.util.List;

@Dao
public interface PayTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertType(PayType payType);

    @Query("DELETE FROM t_paytype")
    void delete();

    @Query("SELECT * FROM t_paytype")
    LiveData<List<PayType>> getAllPayTypes();
}
