package com.jingheng.a105project.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jingheng.a105project.R;

@SuppressLint("Registered")
public class CommonActivity extends AppCompatActivity {

    private ProgressDialog pd;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //dialog
    protected void showLoadingDialog() {
        showLoadingDialog(null);
    }

    @SuppressWarnings("SameParameterValue")
    protected void showLoadingDialog(String message) {
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setIndeterminate(true);
            pd.setCancelable(false);
        }
        pd.setMessage(message != null ? message : getString(R.string.loading));
        pd.show();
    }

    protected void dismissLoadingDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    //openActivity
    @SuppressWarnings("SameParameterValue")
    protected void openActivity(Class activityClass) {
        openActivity(activityClass, null);
    }

    @SuppressWarnings("SameParameterValue")
    protected void openActivity(Class activityClass, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @SuppressWarnings("SameParameterValue")
    protected void openActivityForResult(Class activityClass, int request) {
        openActivityForResult(activityClass, request, null);
    }

    @SuppressWarnings("SameParameterValue")
    protected void openActivityForResult(Class activityClass, int request, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, request);
    }

    //intent
    @SuppressWarnings("SameParameterValue")
    protected void newIntent(Context packageContext, Class cla, String name, String text){
        Intent intent = new Intent();
        intent.putExtra(name, text);
        intent.setClass(packageContext, cla);
        startActivity(intent);
    }

    //toast
    protected void t(int textId) {
        t(getString(textId));
    }

    protected void t(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    //alert
    protected android.app.AlertDialog alert(int titleId, int messageId, DialogInterface.OnClickListener posi, DialogInterface.OnClickListener nega) {
        return alert(getString(titleId), getString(messageId), posi, nega);
    }

    protected android.app.AlertDialog alert(String title, String message, DialogInterface.OnClickListener posi, DialogInterface.OnClickListener nega) {
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(this);
        if (title != null) b.setTitle(title);
        if (message != null) b.setMessage(message);
        if (posi != null || nega != null) {
            b.setPositiveButton(R.string.confirm, posi);
            b.setNegativeButton(R.string.cancel, nega);
        }
        return b.show();
    }

    protected android.app.AlertDialog alertWithView(View v, DialogInterface.OnClickListener posi, DialogInterface.OnClickListener nega) {
        return alertWithView(null, v, posi, nega);
    }

    protected android.app.AlertDialog alertWithView(String title, View v, DialogInterface.OnClickListener posi, DialogInterface.OnClickListener nega) {
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(this);
        if (title != null) b.setTitle(title);
        b.setView(v);
        if (posi != null || nega != null) {
            b.setPositiveButton(R.string.confirm, posi);
            b.setNegativeButton(R.string.cancel, nega);
        }
        return b.show();
    }

    protected android.app.AlertDialog alertWithItems(String[] items, DialogInterface.OnClickListener click) {
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(this);
        b.setItems(items, click);
        return b.show();
    }

    protected android.app.AlertDialog alertWithMultiItems(CharSequence[] items, boolean[] isChecked, DialogInterface.OnMultiChoiceClickListener MultiChoicelistener, DialogInterface.OnClickListener listener) {
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(this);
        b.setMultiChoiceItems(items, isChecked, MultiChoicelistener);
        b.setPositiveButton(getResources().getText(R.string.confirm), listener);
        return b.show();
    }

    //隱藏鍵盤
    protected void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    protected void addBackButton(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    protected void addBloodButton(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(CommonActivity.this, BloodActivity.class));
            }
        });
    }

    protected void addBloodSugarButton(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(CommonActivity.this, BloodSugarActivity.class));
            }
        });
    }

    protected void addWeightButton(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(CommonActivity.this, WeightActivity.class));
            }
        });
    }

    protected void addWaterButton(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(CommonActivity.this, WaterActivity.class));
            }
        });
    }

    protected void addMainButton(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(CommonActivity.this, MainActivity.class));
            }
        });
    }
}

