package com.android.book.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.math.BigDecimal;

/**
 * Created by sxshi on 2019-4-17.
 * 新增一笔消费
 */
@Entity(tableName = "t_bill")
public class Bill {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    //金额
    @ColumnInfo(name = "amount")
    private BigDecimal amcount;

    //记录时间
    @ColumnInfo(name = "add_time")
    private long addTtime;

    //交易方式{0支付宝，1微信，3刷卡，4现金}
    @ColumnInfo(name = "channel_type")
    private int channelType;

    //购物方式{0购物，1旅游，2吃饭，3娱乐，4出行}
    @ColumnInfo(name = "shopping_type")
    private int shoppingType;

    //账单类型{0支出，1收入}
    @ColumnInfo(name = "bill_type")
    private int billType;

    //收入类型{0理财，1收账}
    @ColumnInfo(name = "income_type")
    private int incomeType;

    //交易描述
    @ColumnInfo(name = "desc")
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmcount() {
        return amcount;
    }

    public void setAmcount(BigDecimal amcount) {
        this.amcount = amcount;
    }

    public long getAddTtime() {
        return addTtime;
    }

    public void setAddTtime(long addTtime) {
        this.addTtime = addTtime;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public int getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(int shoppingType) {
        this.shoppingType = shoppingType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
