package com.android.book.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.android.book.R;
import com.android.book.ui.model.RouteManager;
import com.android.book.ui.model.Status;
import com.android.book.ui.widget.CustomTextView;

public class SettingActivity extends AppCompatActivity {
    private Status userStatus = new RouteManager.LogoutStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.setting));
        setSupportActionBar(toolbar);



    }
}
