package com.android.book.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.android.book.data.db.entity.PayType;

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
