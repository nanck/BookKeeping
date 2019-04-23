package com.android.book.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.android.book.data.db.entity.IncomeType;
import com.android.book.data.db.entity.Type;

import java.util.List;

@Dao
public interface IncomeTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertType(Type type);

    @Query("DELETE FROM t_incometype")
    void delete();

    @Query("SELECT * FROM t_incometype")
    LiveData<List<IncomeType>> getAllTTypes();
}
