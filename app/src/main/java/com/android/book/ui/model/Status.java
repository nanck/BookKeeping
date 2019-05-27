package com.android.book.ui.model;

import android.content.Context;

public interface Status {
    /**
     * 添加一笔记账
     *
     * @param context context
     */
    void addBill(Context context);

    /**
     * 查看基本信息
     *
     * @param context
     */
    void lookBasicInfo(Context context);

    /**
     * 设置界面
     *
     * @param context
     */
    void doSetting(Context context);
}
