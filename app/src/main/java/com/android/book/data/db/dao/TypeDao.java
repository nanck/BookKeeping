package com.android.book.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
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
