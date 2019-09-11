package com.android.book.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.book.R;
import com.android.book.ui.model.RouteManager;
import com.android.book.ui.model.Status;

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
