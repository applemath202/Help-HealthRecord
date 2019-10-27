package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.jingheng.a105project.R;
import com.jingheng.a105project.fragment.SublimePickerFragment;
import com.jingheng.a105project.model.Drug;
import com.jingheng.a105project.sqlite.DAODrug;

public class DrugActivity extends CommonActivity implements View.OnClickListener {

    private TextView tv_drug_drugtime;
    private EditText ed_durg_drugname;

    // data
    SelectedDate mSelectedDate;
    int mHour, mMinute;
    String mRecurrenceOption, mRecurrenceRule;
    private String dateKind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        // ui
        tv_drug_drugtime = findViewById(R.id.tv_drug_drugtime);
        tv_drug_drugtime.setOnClickListener((View.OnClickListener) this);
        ed_durg_drugname = findViewById(R.id.ed_drug_drugname);
        findViewById(R.id.drug_finish).setOnClickListener(this);
        findViewById(R.id.rv_drug_report).setOnClickListener(this);

        // data
        Drug drug = Drug.getOurInstance();
        tv_drug_drugtime.setText(drug.getDrugTime());
        ed_durg_drugname.setText(drug.getDrugName());

        addMainButton(R.id.drug_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void showDatePicker() {
        // DialogFragment to host SublimePicker
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);

        // Options
        Pair<Boolean, SublimeOptions> optionsPair = getOptions();

        if (!optionsPair.first) { // If options are not valid
            Toast.makeText(this, "No pickers activated",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Valid options
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {

        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
            mSelectedDate = selectedDate;
            mHour = hourOfDay;
            mMinute = minute;
            mRecurrenceOption = recurrenceOption != null ? recurrenceOption.name() : "n/a";
            mRecurrenceRule = recurrenceRule != null ? recurrenceRule : "n/a";
            updateInfoView();
        }
    };

    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;
        displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
        options.setPickerToShow(SublimeOptions.Picker.TIME_PICKER);

        options.setDisplayOptions(displayOptions);

        // Enable/disable the date range selection feature
        options.setCanPickDateRange(false);

        return new Pair<>(Boolean.TRUE, options);
    }

    // Show date, time & recurrence options that have been selected
    @SuppressLint("SetTextI18n")
    private void updateInfoView() {
        switch (dateKind) {
            case "drugtime":
                tv_drug_drugtime.setText(mHour + ":" + mMinute);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_drug_report:
                startActivity(new Intent(this, DrugReportActivity.class));
                break;
            case R.id.tv_drug_drugtime:
                dateKind = "drugtime";
                showDatePicker();
                break;
            case R.id.drug_finish:
                String drugname = ed_durg_drugname.getText().toString();
                String drugtime = tv_drug_drugtime.getText().toString();
                if (drugname.isEmpty() || drugtime.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    DAODrug daoDrug = new DAODrug(this);
                    Drug drug = new Drug();
                    drug.setDrugName(drugname);
                    drug.setDrugTime(drugtime);
                    daoDrug.insert(drug);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }
}
