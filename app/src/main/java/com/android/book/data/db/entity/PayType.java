package com.android.book.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_paytype")
public class PayType {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "paytype_key")
    private int payKey;

    @ColumnInfo(name = "paytype_value")
    private String payValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayKey() {
        return payKey;
    }

    public void setPayKey(int payKey) {
        this.payKey = payKey;
    }

    public String getPayValue() {
        return payValue;
    }

    public void setPayValue(String payValue) {
        this.payValue = payValue;
    }

    @Override
    public String toString() {
        return "PayType{" +
                "id=" + id +
                ", payKey=" + payKey +
                ", payValue='" + payValue + '\'' +
                '}';
    }
}
