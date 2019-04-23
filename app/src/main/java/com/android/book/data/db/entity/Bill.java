package com.android.book.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
    private double amcount;

    //记录时间 yyyy-MM-dd HH:mm:ss
    @ColumnInfo(name = "add_time")
    private String addTtime;

    //交易方式{0支付宝，1微信，2刷卡，3现金}
    @ColumnInfo(name = "pay_type")
    private int payType;

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

    public double getAmcount() {
        return amcount;
    }

    public void setAmcount(double amcount) {
        this.amcount = amcount;
    }

    public String getAddTtime() {
        return addTtime;
    }

    public void setAddTtime(String addTtime) {
        this.addTtime = addTtime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(int shoppingType) {
        this.shoppingType = shoppingType;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public int getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(int incomeType) {
        this.incomeType = incomeType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", amcount=" + amcount +
                ", addTtime='" + addTtime + '\'' +
                ", payType=" + payType +
                ", shoppingType=" + shoppingType +
                ", billType=" + billType +
                ", incomeType=" + incomeType +
                ", desc='" + desc + '\'' +
                '}';
    }
}
