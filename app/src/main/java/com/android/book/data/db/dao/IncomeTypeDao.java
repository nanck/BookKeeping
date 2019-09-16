package com.android.book.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
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
