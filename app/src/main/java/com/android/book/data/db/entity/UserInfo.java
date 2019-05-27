package com.android.book.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by sxshi on 2019-4-17.
 */

@Entity(tableName = "t_user")
public class UserInfo {
    @PrimaryKey
    @ColumnInfo(name = "phone_no")
    @NonNull
    private String phoneNumber;

    @ColumnInfo(name = "name")
    private String userName;

    @ColumnInfo(name = "pwd")
    private String passWord;

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
