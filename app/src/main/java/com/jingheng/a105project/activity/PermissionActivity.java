package com.jingheng.a105project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.jingheng.a105project.R;
import com.jingheng.a105project.helper.SPHelper;

public class PermissionActivity extends CommonActivity {

    // request
    public static final int PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        checkPermission();
    }

    private void checkPermission() {
        if (SPHelper.getUser(this)) {
            openActivity(MainActivity.class);
        } else {
            openActivity(SettingActivity.class);
        }
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                t(R.string.permission_denied);
                finish();
                return;
            }
        }
        checkPermission();
    }
}
