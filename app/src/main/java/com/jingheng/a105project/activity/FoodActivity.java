package com.jingheng.a105project.activity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.MealsAdapter;
import com.jingheng.a105project.model.Meal;

import java.util.ArrayList;

public class FoodActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private RecyclerView rv;
    private TextView tv_select;

    // data
    private ArrayList<Meal> meals;
    private ArrayList<Meal> selectMeals;
    private ArrayList<Meal> mainMeals;
    private ArrayList<Meal> vegeMeals;
    private ArrayList<Meal> meatMeals;
    private ArrayList<Meal> fishMeals;
    private ArrayList<Meal> fruitMeals;
    private ArrayList<Meal> orders;
    private String selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        addMainButton(R.id.food_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // ui
        MaterialSpinner spinner = findViewById(R.id.food_spinner);
        rv = findViewById(R.id.rv_food);
        tv_select = findViewById(R.id.tv_scroll_view);
        findViewById(R.id.food_next).setOnClickListener(this);
        findViewById(R.id.rv_food_report).setOnClickListener(this);

        // init
        meals = new ArrayList<>();
        selectMeals = new ArrayList<>();
        mainMeals = new ArrayList<>();
        vegeMeals = new ArrayList<>();
        meatMeals = new ArrayList<>();
        fishMeals = new ArrayList<>();
        fruitMeals = new ArrayList<>();
        orders = new ArrayList<>();
        selectItem = "主食";

        addMeals();
        loadingMeals();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new MealsAdapter(this, selectMeals));

        spinner.setItems("主食", "蔬菜", "肉類", "海鮮", "水果");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selectItem = item;
                changeMeals(selectItem);
            }
        });
    }

    private void changeMeals(String selectItem) {
        selectMeals.clear();
        switch (selectItem) {
            case "主食":
                selectMeals.addAll(mainMeals);
                break;
            case "蔬菜":
                selectMeals.addAll(vegeMeals);
                break;
            case "肉類":
                selectMeals.addAll(meatMeals);
                break;
            case "海鮮":
                selectMeals.addAll(fishMeals);
                break;
            case "水果":
                selectMeals.addAll(fruitMeals);
                break;
        }
        rv.getAdapter().notifyDataSetChanged();
    }

    private void addMeals() {
        Meal meal_1 = new Meal();
        meal_1.setId(1);
        meal_1.setName("白飯");
        meal_1.setPicture(R.drawable.rice);
        meal_1.setHot(280);
        meals.add(meal_1);
        Meal meal_2 = new Meal();
        meal_2.setId(2);
        meal_2.setName("麵");
        meal_2.setPicture(R.drawable.noodle);
        meal_2.setHot(150);
        meals.add(meal_2);
        Meal meal_3 = new Meal();
        meal_3.setId(3);
        meal_3.setName("饅頭");
        meal_3.setPicture(R.drawable.mantall);
        meal_3.setHot(300);
        meals.add(meal_3);
        Meal meal_4 = new Meal();
        meal_4.setId(4);
        meal_4.setName("番薯");
        meal_4.setPicture(R.drawable.potato);
        meal_4.setHot(83);
        meals.add(meal_4);
        Meal meal_5 = new Meal();
        meal_5.setId(5);
        meal_5.setName("菠菜");
        meal_5.setPicture(R.drawable.bo);
        meal_5.setHot(0);
        meals.add(meal_5);
        Meal meal_6 = new Meal();
        meal_6.setId(6);
        meal_6.setName("芹菜");
        meal_6.setPicture(R.drawable.chi);
        meal_6.setHot(0);
        meals.add(meal_6);
        Meal meal_7 = new Meal();
        meal_7.setId(7);
        meal_7.setName("地瓜葉");
        meal_7.setPicture(R.drawable.diguaya);
        meal_7.setHot(0);
        meals.add(meal_7);
        Meal meal_8 = new Meal();
        meal_8.setId(8);
        meal_8.setName("花椰菜");
        meal_8.setPicture(R.drawable.flower);
        meal_8.setHot(0);
        meals.add(meal_8);
        Meal meal_9 = new Meal();
        meal_9.setId(9);
        meal_9.setName("高麗菜");
        meal_9.setPicture(R.drawable.kuoli);
        meal_9.setHot(0);
        meals.add(meal_9);
        Meal meal_10 = new Meal();
        meal_10.setId(10);
        meal_10.setName("牛肉");
        meal_10.setPicture(R.drawable.beef);
        meal_10.setHot(266);
        meals.add(meal_10);
        Meal meal_11 = new Meal();
        meal_11.setId(11);
        meal_11.setName("雞肉");
        meal_11.setPicture(R.drawable.chicken);
        meal_11.setHot(177);
        meals.add(meal_11);
        Meal meal_12 = new Meal();
        meal_12.setId(12);
        meal_12.setName("鴨肉");
        meal_12.setPicture(R.drawable.duck);
        meal_12.setHot(105);
        meals.add(meal_12);
        Meal meal_13 = new Meal();
        meal_13.setId(13);
        meal_13.setName("鵝肉");
        meal_13.setPicture(R.drawable.goose);
        meal_13.setHot(193);
        meals.add(meal_13);
        Meal meal_14 = new Meal();
        meal_14.setId(14);
        meal_14.setName("豬肉");
        meal_14.setPicture(R.drawable.pig);
        meal_14.setHot(262);
        meals.add(meal_14);
        Meal meal_15 = new Meal();
        meal_15.setId(15);
        meal_15.setName("羊肉");
        meal_15.setPicture(R.drawable.sheep);
        meal_15.setHot(192);
        meals.add(meal_15);
        Meal meal_16 = new Meal();
        meal_16.setId(16);
        meal_16.setName("草蝦");
        meal_16.setPicture(R.drawable.chaoshoe);
        meal_16.setHot(98);
        meals.add(meal_16);
        Meal meal_17 = new Meal();
        meal_17.setId(17);
        meal_17.setName("鯖魚");
        meal_17.setPicture(R.drawable.chifish);
        meal_17.setHot(417);
        meals.add(meal_17);
        Meal meal_18 = new Meal();
        meal_18.setId(18);
        meal_18.setName("秋刀魚");
        meal_18.setPicture(R.drawable.choudou);
        meal_18.setHot(314);
        meals.add(meal_18);
        Meal meal_19 = new Meal();
        meal_19.setId(19);
        meal_19.setName("花枝");
        meal_19.setPicture(R.drawable.flower_);
        meal_19.setHot(74);
        meals.add(meal_19);
        Meal meal_20 = new Meal();
        meal_20.setId(20);
        meal_20.setName("劍蝦");
        meal_20.setPicture(R.drawable.janshoe);
        meal_20.setHot(79);
        meals.add(meal_20);
        Meal meal_21 = new Meal();
        meal_21.setId(21);
        meal_21.setName("蛤蠣");
        meal_21.setPicture(R.drawable.kali);
        meal_21.setHot(25);
        meals.add(meal_21);
        Meal meal_22 = new Meal();
        meal_22.setId(22);
        meal_22.setName("鮭魚");
        meal_22.setPicture(R.drawable.koafish);
        meal_22.setHot(230);
        meals.add(meal_22);
        Meal meal_23 = new Meal();
        meal_23.setId(23);
        meal_23.setName("牡蠣");
        meal_23.setPicture(R.drawable.mooli);
        meal_23.setHot(77);
        meals.add(meal_23);
        Meal meal_24 = new Meal();
        meal_24.setId(24);
        meal_24.setName("生蠔");
        meal_24.setPicture(R.drawable.sanhoa);
        meal_24.setHot(83);
        meals.add(meal_24);
        Meal meal_25 = new Meal();
        meal_25.setId(25);
        meal_25.setName("虱目魚");
        meal_25.setPicture(R.drawable.simo);
        meal_25.setHot(200);
        meals.add(meal_25);
        Meal meal_26 = new Meal();
        meal_26.setId(26);
        meal_26.setName("文蛤");
        meal_26.setPicture(R.drawable.word);
        meal_26.setHot(69);
        meals.add(meal_26);
        Meal meal_27 = new Meal();
        meal_27.setId(27);
        meal_27.setName("吳郭魚");
        meal_27.setPicture(R.drawable.wuguo);
        meal_27.setHot(107);
        meals.add(meal_27);
        Meal meal_28 = new Meal();
        meal_28.setId(28);
        meal_28.setName("蘋果");
        meal_28.setPicture(R.drawable.apple);
        meal_28.setHot(60);
        meals.add(meal_28);
        Meal meal_29 = new Meal();
        meal_29.setId(29);
        meal_29.setName("香蕉");
        meal_29.setPicture(R.drawable.banana);
        meal_29.setHot(120);
        meals.add(meal_29);
        Meal meal_30 = new Meal();
        meal_30.setId(30);
        meal_30.setName("芭樂");
        meal_30.setPicture(R.drawable.guva);
        meal_30.setHot(60);
        meals.add(meal_30);
        Meal meal_31 = new Meal();
        meal_31.setId(31);
        meal_31.setName("桃子");
        meal_31.setPicture(R.drawable.peach);
        meal_31.setHot(60);
        meals.add(meal_31);
        Meal meal_32 = new Meal();
        meal_32.setId(32);
        meal_32.setName("水梨");
        meal_32.setPicture(R.drawable.pear);
        meal_32.setHot(75);
        meals.add(meal_32);
    }

    private void loadingMeals() {

        for (int i = 0; i < 4; i++) {
            mainMeals.add(meals.get(i));
        }

        for (int i = 4; i < 9; i++) {
            vegeMeals.add(meals.get(i));
        }

        for (int i = 10; i < 15; i++) {
            meatMeals.add(meals.get(i));
        }

        for (int i = 16; i < 27; i++) {
            fishMeals.add(meals.get(i));
        }

        for (int i = 28; i < 32; i++) {
            fruitMeals.add(meals.get(i));
        }

        selectMeals.addAll(mainMeals);
    }

    public void addMealToOrder(Meal meal) {
        switch (selectItem) {
            case "主食":
                mainMeals.remove(meal);
                break;
            case "蔬菜":
                vegeMeals.remove(meal);
                break;
            case "肉類":
                meatMeals.remove(meal);
                break;
            case "海鮮":
                fishMeals.remove(meal);
                break;
            case "水果":
                fruitMeals.remove(meal);
                break;
        }
        changeMeals(selectItem);
        meal.setAmount(1);
        orders.add(meal);
        String tv = tv_select.getText().toString();
        if (tv.equals("餐點:")) {
            tv_select.setText(tv + meal.getName());
        } else {
            tv_select.setText(tv + "，" + meal.getName());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_food_report:
                startActivity(new Intent(this, FoodReportActivity.class));
                break;
            case R.id.food_next:
                startActivity(new Intent(this, FoodInformationActivity.class)
                        .putExtra("orders", orders)
                        .putExtra("meals", tv_select.getText().toString()));
                break;
        }
    }
}

