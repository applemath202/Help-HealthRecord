package com.jingheng.a105project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.CommonRVAdapter;
import com.jingheng.a105project.helper.DemoBase;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Pee;
import com.jingheng.a105project.model.Sport;
import com.jingheng.a105project.model.Water;
import com.jingheng.a105project.model.Weight;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOBloodSugar;
import com.jingheng.a105project.sqlite.DAOFood;
import com.jingheng.a105project.sqlite.DAOPee;
import com.jingheng.a105project.sqlite.DAOSport;
import com.jingheng.a105project.sqlite.DAOWater;
import com.jingheng.a105project.sqlite.DAOWeight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class LineGraphActivity extends DemoBase implements View.OnClickListener, OnChartGestureListener, OnChartValueSelectedListener {

    // ui
    private LineChart chart;

    private String selectItem;

    // data-date
    private String date;
    private ArrayList<String> dateList;

    // data-blood
    private ArrayList<Blood> bloodList;
    private ArrayList<Blood> bloodDao;
    private ArrayList<BloodSugar> bloodsugarList;
    private ArrayList<BloodSugar> bloodsugarDao;
    private ArrayList<Weight> weightList;
    private ArrayList<Weight> weightDao;
    private ArrayList<Sport> sportList;
    private ArrayList<Sport> sportDao;
    private ArrayList<Water> waterList;
    private ArrayList<Water> waterDao;
    private ArrayList<Pee> peeList;
    private ArrayList<Pee> peeDao;
    private ArrayList<Food> foodList;
    private ArrayList<Food> foodDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);
        addMainButton(R.id.line_graph_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // ui
        MaterialSpinner spinner = findViewById(R.id.food_spinner);
        chart = findViewById(R.id.chart);

        //init
        selectItem = "血壓";
        initChart();
        setChartValues();
        setChart();
        spinner.setItems("血壓", "血糖", "體重", "運動", "水分","尿液","飲食");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selectItem = item;
                setChart();
            }
        });
    }

    private final int[] colors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };

    private void setChartValues() {
        dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN);
        Date d = new Date();
        for (int i = 1; i <= 7; i++) {
            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
            try {
                cal.setTime(sdf.parse(String.valueOf(d)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, -i);
            dateList.add(sdf.format(cal.getTime()));
        }

        bloodList = new ArrayList<>();
        bloodDao = new ArrayList<>();
        DAOBlood daoBlood = new DAOBlood(this);
        bloodDao.addAll(daoBlood.getAll());
        for (Blood blood : bloodDao) {
            for (String day : dateList) {
                if (blood.getCreateDate().substring(0, 10).equals(day)) {
                    bloodList.add(blood);
                }
            }
        }
        bloodsugarList =new ArrayList<>();
        bloodsugarDao = new ArrayList<>();
        DAOBloodSugar daoBloodSugar = new DAOBloodSugar(this);
        bloodsugarDao.addAll(daoBloodSugar.getAll());
        for (BloodSugar bloodSugar : bloodsugarDao) {
            for (String day : dateList) {
                if (bloodSugar.getCreateDate().substring(0, 10).equals(day)) {
                    bloodsugarList.add(bloodSugar);
                }
            }
        }
        weightList = new ArrayList<>();
        weightDao = new ArrayList<>();
        DAOWeight daoWeight = new DAOWeight(this);
        weightDao.addAll(daoWeight.getAll());
        for (Weight weight : weightDao) {
            for (String day : dateList) {
                if (weight.getCreateDate().substring(0, 10).equals(day)) {
                    weightList.add(weight);
                }
            }
        }
        foodList = new ArrayList<>();
        foodDao = new ArrayList<>();
        DAOFood daoFood = new DAOFood(this);
        foodDao.addAll(daoFood.getAll());
        for (Food food : foodDao) {
            for (String day : dateList) {
                if (food.getCreateDate().substring(0, 10).equals(day)) {
                    foodList.add(food);
                }
            }
        }

        sportList = new ArrayList<>();
        sportDao = new ArrayList<>();
        DAOSport daoSport = new DAOSport(this);
        sportDao.addAll(daoSport.getAll());
        for (Sport sport : sportDao) {
            for (String day : dateList) {
                if (sport.getCreateDate().substring(0, 10).equals(day)) {
                    sportList.add(sport);
                }
            }
        }
        waterList = new ArrayList<>();
        waterDao = new ArrayList<>();
        DAOWater daoWater = new DAOWater(this);
        waterDao.addAll(daoWater.getAll());
        for (Water water : waterDao) {
            for (String day : dateList) {
                if (water.getCreateDate().substring(0, 10).equals(day)) {
                    waterList.add(water);
                }
            }
        }
        peeList = new ArrayList<>();
        peeDao = new ArrayList<>();
        DAOPee daoPee = new DAOPee(this);
        peeDao.addAll(daoPee.getAll());
        for (Pee pee : peeDao) {
            for (String day : dateList) {
                if (pee.getCreateDate().substring(0, 10).equals(day)) {
                    peeList.add(pee);
                }
            }
        }


    }

    private void setChart() {
        chart.resetTracking();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        switch (selectItem){
            case "血壓":
                // blood
                ArrayList<Entry> blood_pressure_values = new ArrayList<>();
                for (int i = 0; i < bloodList.size(); i++) {
                    Blood blood = bloodList.get(i);
                    blood_pressure_values.add(new Entry(i, Float.valueOf(blood.getBloodPressure())));
                }

                LineDataSet blood_pressure_d = new LineDataSet(blood_pressure_values, "舒張壓");
                blood_pressure_d.setLineWidth(2.5f);
                blood_pressure_d.setCircleRadius(4f);

                int blood_pressure_color = colors[1 % colors.length];
                blood_pressure_d.setColor(blood_pressure_color);
                blood_pressure_d.setCircleColor(blood_pressure_color);
                dataSets.add(blood_pressure_d);

                ArrayList<Entry> blood_pressure_2_values = new ArrayList<>();
                for (int i = 0; i < bloodList.size(); i++) {
                    Blood blood = bloodList.get(i);
                    blood_pressure_2_values.add(new Entry(i, Float.valueOf(blood.getBloodPressure_2())));
                }

                LineDataSet blood_pressure_2_d = new LineDataSet(blood_pressure_2_values, "收縮壓");
                blood_pressure_2_d.setLineWidth(2.5f);
                blood_pressure_2_d.setCircleRadius(4f);

                int color = colors[2 % colors.length];
                blood_pressure_2_d.setColor(color);
                blood_pressure_2_d.setCircleColor(color);
                dataSets.add(blood_pressure_2_d);
                break;
            case "血糖":
                // bloodsugar
                ArrayList<Entry> blood_sugar_values = new ArrayList<>();
                for (int i = 0; i < bloodsugarList.size(); i++) {
                    BloodSugar bloodSugar = bloodsugarList.get(i);
                    blood_sugar_values.add(new Entry(i, Float.valueOf(bloodSugar.getBloodsugar())));
                }

                LineDataSet blood_sugar_d = new LineDataSet(blood_sugar_values, "血糖");
                blood_sugar_d.setLineWidth(2.5f);
                blood_sugar_d.setCircleRadius(4f);

                int blood_sugar_color = colors[1 % colors.length];
                blood_sugar_d.setColor(blood_sugar_color);
                blood_sugar_d.setCircleColor(blood_sugar_color);
                dataSets.add(blood_sugar_d);
                break;
            case "體重":
                // weight
                ArrayList<Entry> weight_values = new ArrayList<>();
                for (int i = 0; i < weightList.size(); i++) {
                    Weight weight = weightList.get(i);
                    weight_values.add(new Entry(i, Float.valueOf(weight.getWeight())));
                }

                LineDataSet weight_d = new LineDataSet(weight_values, "體重");
                weight_d.setLineWidth(2.5f);
                weight_d.setCircleRadius(4f);

                int weight_color = colors[1 % colors.length];
                weight_d.setColor(weight_color);
                weight_d.setCircleColor(weight_color);
                dataSets.add(weight_d);

                break;
            case "運動":
                // sport
                ArrayList<Entry> sport_values = new ArrayList<>();
                for (int i = 0; i < sportList.size(); i++) {
                    Sport sport = sportList.get(i);
                    sport_values.add(new Entry(i, Float.valueOf(sport.getSportTime())));
                }

                LineDataSet sport_d = new LineDataSet(sport_values, "運動時數");
                sport_d.setLineWidth(2.5f);
                sport_d.setCircleRadius(4f);

                int sport_color = colors[1 % colors.length];
                sport_d.setColor(sport_color);
                sport_d.setCircleColor(sport_color);
                dataSets.add(sport_d);
                break;
            case "水分":
                // water
                ArrayList<Entry> water_values = new ArrayList<>();
                for (int i = 0; i < waterList.size(); i++) {
                    Water water = waterList.get(i);
                    water_values.add(new Entry(i, Float.valueOf(water.getWater())));
                }

                LineDataSet water_d = new LineDataSet(water_values, "水分");
                water_d.setLineWidth(2.5f);
                water_d.setCircleRadius(4f);

                int water_color = colors[1 % colors.length];
                water_d.setColor(water_color);
                water_d.setCircleColor(water_color);
                dataSets.add(water_d);
                break;
            case "尿液":
                // wpee
                ArrayList<Entry> pee_values = new ArrayList<>();
                for (int i = 0; i < peeList.size(); i++) {
                    Pee pee = peeList.get(i);
                    pee_values.add(new Entry(i, Float.valueOf(pee.getPee())));
                }

                LineDataSet pee_d = new LineDataSet(pee_values, "尿液量");
                pee_d.setLineWidth(2.5f);
                pee_d.setCircleRadius(4f);

                int pee_color = colors[1 % colors.length];
                pee_d.setColor(pee_color);
                pee_d.setCircleColor(pee_color);
                dataSets.add(pee_d);
                break;
            case "飲食":
                // food
                ArrayList<Entry> food_values = new ArrayList<>();
                for (int i = 0; i < foodList.size(); i++) {
                    Food food = foodList.get(i);
                    food_values.add(new Entry(i, Float.valueOf(food.getHot())));
                }

                LineDataSet food_d = new LineDataSet(food_values, "熱量");
                food_d.setLineWidth(2.5f);
                food_d.setCircleRadius(4f);

                int food_color = colors[1 % colors.length];
                food_d.setColor(food_color);
                food_d.setCircleColor(food_color);
                dataSets.add(food_d);
                break;
        }

        //blood sugar

        // make the first DataSet dashed
        ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
        ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate();
    }

    // 初始化圖表
    public void initChart() {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.line, menu);
        menu.removeItem(R.id.actionToggleIcons);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/MultiLineChartActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                chart.invalidate();
                break;
            }
            /*
            case R.id.actionToggleIcons: { break; }
             */
            case R.id.actionTogglePinch: {
                if (chart.isPinchZoomEnabled())
                    chart.setPinchZoom(false);
                else
                    chart.setPinchZoom(true);

                chart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                chart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (chart.getData() != null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionToggleFilled: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.CUBIC_BEZIER);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleStepped: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.STEPPED);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleHorizontalCubic: {
                List<ILineDataSet> sets = chart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.HORIZONTAL_BEZIER);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
            case R.id.animateX: {
                chart.animateX(2000);
                break;
            }
            case R.id.animateY: {
                chart.animateY(2000);
                break;
            }
            case R.id.animateXY: {
                chart.animateXY(2000, 2000);
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "MultiLineChartActivity");
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            chart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart long pressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart fling. VelocityX: " + velocityX + ", VelocityY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {

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
                startActivity(new Intent(LineGraphActivity.this, MainActivity.class));
            }
        });
    }
}
