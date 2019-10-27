package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.jingheng.a105project.R;
import com.jingheng.a105project.fragment.SublimePickerFragment;
import com.jingheng.a105project.helper.PlayReceiver;
import com.jingheng.a105project.helper.SPHelper;
import com.jingheng.a105project.model.Alarm;
import com.jingheng.a105project.model.Person;
import com.jingheng.a105project.sqlite.DAOAlarm;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SettingActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_sex, tv_weight, tv_height, tv_birth, tv_wake_up_plus,
            tv_sleep_plus, tv_breakfast_plus, tv_lunch_plus, tv_dinner_plus,
            tv_sport_plus, tv_weiht_plus;

    private ImageView iv;

    // data
    SelectedDate mSelectedDate;
    String mHour, mMinute;
    String mRecurrenceOption, mRecurrenceRule;
    private String dateKind;
    private boolean isSetting;
    private boolean isCreated = false;
    private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // extra
        isSetting = getIntent().getBooleanExtra("isSetting", true);

        // ui
        tv_sex = findViewById(R.id.setting_tv_sex);
        tv_sex.setOnClickListener(this);
        tv_weight = findViewById(R.id.setting_tv_weight);
        tv_weight.setOnClickListener(this);
        tv_height = findViewById(R.id.setting_tv_height);
        tv_height.setOnClickListener(this);
        tv_birth = findViewById(R.id.setting_tv_birth);
        tv_birth.setOnClickListener(this);
        tv_wake_up_plus = findViewById(R.id.setting_wakeup_plus);
        tv_wake_up_plus.setOnClickListener(this);
        tv_breakfast_plus = findViewById(R.id.setting_breakfast_plus);
        tv_breakfast_plus.setOnClickListener(this);
        tv_lunch_plus = findViewById(R.id.setting_lunch_plus);
        tv_lunch_plus.setOnClickListener(this);
        tv_dinner_plus = findViewById(R.id.setting_dinner_plus);
        tv_dinner_plus.setOnClickListener(this);
        tv_sleep_plus = findViewById(R.id.setting_sleep_plus);
        tv_sleep_plus.setOnClickListener(this);
        tv_sport_plus = findViewById(R.id.setting_sport_plus);
        tv_sport_plus.setOnClickListener(this);
        tv_weiht_plus = findViewById(R.id.setting_weight_plus);
        tv_weiht_plus.setOnClickListener(this);
        findViewById(R.id.setting_finish).setOnClickListener(this);
        iv = findViewById(R.id.setting_person);
        iv.setOnClickListener(this);

        // data
        Person person = Person.getInstance();
        tv_sex.setText(person.getSex());
        tv_weight.setText(person.getWeight());
        tv_height.setText(person.getHeight());
        tv_birth.setText(person.getBirth());

        if (isSetting) {
            findViewById(R.id.ll_setting).setVisibility(View.VISIBLE);
            findViewById(R.id.ll_alarm).setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        } else {
            addBackButton(R.id.setting_toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            findViewById(R.id.ll_setting).setVisibility(View.GONE);
            findViewById(R.id.ll_alarm).setVisibility(View.VISIBLE);
            DAOAlarm daoAlarm = new DAOAlarm(this);
            Log.d("testSetting", "" + daoAlarm.getCount());
            if (daoAlarm.getCount() != 0) {
                alarm = daoAlarm.get(1);
                tv_wake_up_plus.setText(alarm.getWakeup());
                tv_breakfast_plus.setText(alarm.getBreakfast());
                tv_lunch_plus.setText(alarm.getLunch());
                tv_dinner_plus.setText(alarm.getDinner());
                tv_sleep_plus.setText(alarm.getSleep());
                tv_sport_plus.setText(alarm.getSport());
                tv_weiht_plus.setText(alarm.getWeight());
            }
            iv.setImageResource(R.drawable.ic_person);
        }
    }

    private void showScrollPicker(final String kind) {
        final Dialog dialog = new Dialog(this);
        //dialog.setTitle("Title");
        dialog.setContentView(R.layout.dialog_picker);
        Button dp_bt_cancel = dialog.findViewById(R.id.dp_bt_cancel);
        Button dp_bt_set = dialog.findViewById(R.id.dp_bt_set);
        final NumberPicker numberPicker = dialog.findViewById(R.id.dp_np);
        final String[] sex = getResources().getStringArray(R.array.sex);
        if (kind.equals("weight")) {
            numberPicker.setMinValue(30);
            numberPicker.setMaxValue(120);
            numberPicker.setValue(50); // 設定預設位置
        } else if (kind.equals("height")) {
            numberPicker.setMinValue(100);
            numberPicker.setMaxValue(200);
            numberPicker.setValue(170); // 設定預設位置
        } else {
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(sex.length - 1);
            numberPicker.setDisplayedValues(sex);
            numberPicker.setValue(0); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯
        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("weight")) {
                    tv_weight.setText(String.valueOf(numberPicker.getValue()));
                } else if (kind.equals("height")) {
                    tv_height.setText(String.valueOf(numberPicker.getValue()));
                } else {
                    tv_sex.setText(String.valueOf(sex[numberPicker.getValue()]));
                }
                dialog.dismiss();
            }
        });
        dp_bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
            mHour = String.valueOf(hourOfDay);
            if(mHour.length() == 1){
                mHour = "0" + hourOfDay;
            }
            mMinute = String.valueOf(minute);
            if(mMinute.length() == 1){
                mMinute = "0" + minute;
            }
            mRecurrenceOption = recurrenceOption != null ? recurrenceOption.name() : "n/a";
            mRecurrenceRule = recurrenceRule != null ? recurrenceRule : "n/a";
            updateInfoView();
        }
    };

    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;
        if ("birth".equals(dateKind)) {
            displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
            options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        } else {
            displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
            options.setPickerToShow(SublimeOptions.Picker.TIME_PICKER);
        }

        options.setDisplayOptions(displayOptions);

        // Enable/disable the date range selection feature
        options.setCanPickDateRange(false);

        return new Pair<>(Boolean.TRUE, options);
    }

    // Show date, time & recurrence options that have been selected
    @SuppressLint("SetTextI18n")
    private void updateInfoView() {
        switch (dateKind) {
            case "wakeUp":
                tv_wake_up_plus.setText(mHour + ":" + mMinute);
                Log.d("settingAlarm", "wakeUp");
                break;
            case "breakfast":
                tv_breakfast_plus.setText(mHour + ":" + mMinute);
                break;
            case "lunch":
                tv_lunch_plus.setText(mHour + ":" + mMinute);
                break;
            case "dinner":
                tv_dinner_plus.setText(mHour + ":" + mMinute);
                break;
            case "sleep":
                tv_sleep_plus.setText(mHour + ":" + mMinute);
                break;
            case "sport":
                tv_sport_plus.setText(mHour + ":" + mMinute);
                break;
            case "weight_plus":
                tv_weiht_plus.setText(mHour + ":" + mMinute);
                break;
        }

        if (mSelectedDate != null) {
            if (mSelectedDate.getType() == SelectedDate.Type.SINGLE) {
                int month = mSelectedDate.getStartDate().get(Calendar.MONTH) + 1;
                tv_birth.setText(mSelectedDate.getStartDate().get(Calendar.YEAR)
                        + "-" + month + "-"
                        + mSelectedDate.getStartDate().get(Calendar.DAY_OF_MONTH));

            }
        }
    }

    private void setCal(Alarm alarm) {
        Calendar cal_wake_up = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_wake_up.add(Calendar.DATE, 0);
        cal_wake_up.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getWakeup().substring(0,2)));
        cal_wake_up.set(Calendar.MINUTE, Integer.valueOf(alarm.getWakeup().substring(3,5)));
        cal_wake_up.set(Calendar.SECOND, 0);

        Log.d("testAlarm", "time:" + Integer.valueOf(alarm.getWakeup().substring(0,2)) + Integer.valueOf(alarm.getWakeup().substring(3,5)));
        setAlarm("wakeUp", cal_wake_up);

        Calendar cal_sleep = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_sleep.add(Calendar.DATE, 0);
        cal_sleep.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getSleep().substring(0,2)));
        cal_sleep.set(Calendar.MINUTE, Integer.valueOf(alarm.getSleep().substring(3,5)));
        cal_sleep.set(Calendar.SECOND, 0);
        setAlarm("sleep", cal_sleep);

        Calendar cal_breakfast = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_breakfast.add(Calendar.DATE, 0);
        cal_breakfast.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getBreakfast().substring(0,2)));
        cal_breakfast.set(Calendar.MINUTE, Integer.valueOf(alarm.getBreakfast().substring(3,5)));
        cal_breakfast.set(Calendar.SECOND, 0);
        setAlarm("breakfast", cal_breakfast);

        Calendar cal_lunch = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_lunch.add(Calendar.DATE, 0);
        cal_lunch.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getLunch().substring(0,2)));
        cal_lunch.set(Calendar.MINUTE, Integer.valueOf(alarm.getLunch().substring(3,5)));
        cal_lunch.set(Calendar.SECOND, 0);
        setAlarm("lunch", cal_lunch);

        Calendar cal_dinner = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_dinner.add(Calendar.DATE, 0);
        cal_dinner.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getDinner().substring(0,2)));
        cal_dinner.set(Calendar.MINUTE, Integer.valueOf(alarm.getDinner().substring(3,5)));
        cal_dinner.set(Calendar.SECOND, 0);
        setAlarm("dinner", cal_dinner);

        Calendar cal_sport = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_sport.add(Calendar.DATE, 0);
        cal_sport.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getSport().substring(0,2)));
        cal_sport.set(Calendar.MINUTE, Integer.valueOf(alarm.getSport().substring(3,5)));
        cal_sport.set(Calendar.SECOND, 0);
        setAlarm("sport", cal_sport);

        Calendar cal_weight = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        cal_weight.add(Calendar.DATE, 0);
        cal_weight.set(Calendar.HOUR_OF_DAY, Integer.valueOf(alarm.getWeight().substring(0,2)));
        cal_weight.set(Calendar.MINUTE, Integer.valueOf(alarm.getWeight().substring(3,5)));
        cal_weight.set(Calendar.SECOND, 0);
        setAlarm("weight", cal_weight);
    }

    private void setAlarm(String title, Calendar cal) {
        Intent intent = new Intent(this, PlayReceiver.class);
        intent.addCategory(title);

        intent.putExtra("title", "activity_app");
        intent.putExtra("kind", title);

        PendingIntent pi = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Log.d("testAlarm", "" + cal.getTimeInMillis());

        AlarmManager am = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_tv_sex:
                showScrollPicker("sex");
                break;
            case R.id.setting_tv_weight:
                showScrollPicker("weight");
                break;
            case R.id.setting_tv_height:
                showScrollPicker("height");
                break;
            case R.id.setting_tv_birth:
                dateKind = "birth";
                showDatePicker();
                break;
            case R.id.setting_wakeup_plus:
                dateKind = "wakeUp";
                showDatePicker();
                break;
            case R.id.setting_breakfast_plus:
                dateKind = "breakfast";
                showDatePicker();
                break;
            case R.id.setting_lunch_plus:
                dateKind = "lunch";
                showDatePicker();
                break;
            case R.id.setting_dinner_plus:
                dateKind = "dinner";
                showDatePicker();
                break;
            case R.id.setting_sleep_plus:
                dateKind = "sleep";
                showDatePicker();
                break;
            case R.id.setting_sport_plus:
                dateKind = "sport";
                showDatePicker();
                break;
            case R.id.setting_weight_plus:
                dateKind = "weight_plus";
                showDatePicker();
                break;
            case R.id.setting_finish:
                if (isSetting) {
                    String sex = tv_sex.getText().toString();
                    String weight = tv_weight.getText().toString();
                    String height = tv_height.getText().toString();
                    String birth = tv_birth.getText().toString();
                    if (sex.isEmpty() || weight.isEmpty() || height.isEmpty() || birth.isEmpty()) {
                        Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                    } else {
                        Person person = Person.getInstance();
                        person.setSex(sex);
                        person.setHeight(height);
                        person.setWeight(weight);
                        person.setBirth(birth);
                        SPHelper.setUser(SettingActivity.this);
                        startActivity(new Intent(this, MainActivity.class));
                    }
                } else {
                    String wakeUp = tv_wake_up_plus.getText().toString();
                    String breakfast = tv_breakfast_plus.getText().toString();
                    String lunch = tv_lunch_plus.getText().toString();
                    String dinner = tv_dinner_plus.getText().toString();
                    String sleep = tv_sleep_plus.getText().toString();
                    String sport = tv_sport_plus.getText().toString();
                    String weight_plus = tv_weiht_plus.getText().toString();
                    if (wakeUp.isEmpty() || breakfast.isEmpty() || lunch.isEmpty() || dinner.isEmpty() ||
                            sleep.isEmpty() || sport.isEmpty() || weight_plus.isEmpty()) {
                        Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                    } else {
                        DAOAlarm daoAlarm = new DAOAlarm(this);
                        if (daoAlarm.getCount() != 0) {
                            alarm.setAlarmId(1);
                            alarm.setWakeup(wakeUp);
                            alarm.setBreakfast(breakfast);
                            alarm.setLunch(lunch);
                            alarm.setDinner(dinner);
                            alarm.setSleep(sleep);
                            alarm.setSport(sport);
                            alarm.setWeight(weight_plus);
                            daoAlarm.update(alarm);
                            isCreated = false;
                            setCal(alarm);
                        } else {
                            alarm = new Alarm();
                            alarm.setWakeup(wakeUp);
                            alarm.setBreakfast(breakfast);
                            alarm.setLunch(lunch);
                            alarm.setDinner(dinner);
                            alarm.setSleep(sleep);
                            alarm.setSport(sport);
                            alarm.setWeight(weight_plus);
                            daoAlarm.insert(alarm);
                            isCreated = true;
                            setCal(alarm);
                        }
                        startActivity(new Intent(this, MainActivity.class));
                    }
                }
                break;
            case R.id.setting_person:
                if (isSetting) {
                    findViewById(R.id.ll_setting).setVisibility(View.GONE);
                    findViewById(R.id.ll_alarm).setVisibility(View.VISIBLE);
                    isSetting = false;
                    iv.setImageResource(R.drawable.ic_person);
                } else {
                    findViewById(R.id.ll_setting).setVisibility(View.VISIBLE);
                    findViewById(R.id.ll_alarm).setVisibility(View.GONE);
                    isSetting = true;
                    iv.setImageResource(R.drawable.ic_alarm);
                }
                break;
        }
    }
}
