package com.android.book.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.android.book.data.db.entity.Type;

import java.util.List;

@Dao
public interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertType(Type type);

    @Query("DELETE FROM t_type")
    void delete();

    @Query("SELECT * FROM t_type")
    LiveData<List<Type>> getAllTTypes();
}
