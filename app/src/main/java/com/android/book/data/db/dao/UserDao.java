package com.android.book.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.android.book.data.db.entity.UserInfo;

/**
 * Created by sxshi on 2019-4-17.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserInfo userInfo);

    @Query("DELETE FROM t_user")
    void deleteUser();

    @Query("SELECT * FROM t_user where phone_no=:phone and pwd=:pwd")
    UserInfo getUser(String phone, String pwd);

    @Query("SELECT * FROM t_user where phone_no=:phone")
    UserInfo isExited(String phone);
}
