package com.jingheng.a105project.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.QAAdapter;

import java.util.ArrayList;

public class QAActivity extends CommonActivity {

    private String selectItem;
    private RecyclerView rv;
    private ArrayList<String> list;
    private ArrayList<String> q1List;
    private ArrayList<String> q2List;
    private ArrayList<String> q3List;
    private ArrayList<String> q4List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        MaterialSpinner spinner = findViewById(R.id.qa_spinner);
        addMainButton(R.id.qa_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //init
        list = new ArrayList<>();
        q1List = new ArrayList<>();
        q2List = new ArrayList<>();
        q3List = new ArrayList<>();
        q4List = new ArrayList<>();

        setListContent();

        selectItem = "護腎衛教";
        spinner.setItems("護腎衛教", "腹膜透析", "血液透析", "腎臟移植");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selectItem = item;
                changeQA(selectItem);
            }
        });
        list.addAll(q1List);
        rv = findViewById(R.id.qa_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new QAAdapter(this, list));
    }

    private void changeQA(String selectItem){
        list.clear();
        switch (selectItem) {
            case "護腎衛教":
                list.addAll(q1List);
                break;
            case "腹膜透析":
                list.addAll(q2List);
                break;
            case "血液透析":
                list.addAll(q3List);
                break;
            case "腎臟移植":
                list.addAll(q4List);
                break;
        }
        assert rv.getAdapter() != null;
        rv.getAdapter().notifyDataSetChanged();
    }

    private void setListContent(){
        q1List.add("淺談腎臟");
        q1List.add("何謂慢性腎臟病");
        q1List.add("血脂異常患者，應如何注意自己的腎臟？");
        q1List.add("如何檢測慢性腎臟病?");
        q1List.add("慢性腎臟病分成五期，那如何定義？");
        q1List.add("誰較容易罹患腎臟病呢？");
        q1List.add("針對血壓異常患者，應如何注意自己的腎臟保養?");
        q1List.add("血糖異常患者，應如何注意自己的腎臟保養？");
        q1List.add("如何早期發掘腎臟疾病的症狀?");
        q1List.add("當罹患慢性腎臟病時，要如何自我照護？");
        q2List.add("淺談腹膜透析");
        q2List.add("腹膜透析患者飲食的注意事項");
        q2List.add("腹膜透析患者為什麼必須控制水份？");
        q2List.add("腹膜透析患者如何平衡體內水份？");
        q2List.add("腹膜透析患者如何知道體內已有過多的水份？");
        q2List.add("腹膜透析患者為什麼需要吃高蛋白質食物？");
        q2List.add("腹膜透析患者貧血時的飲食注意事項");
        q2List.add("為什麼按時服藥物很重要？");
        q2List.add("有哪些最常見的藥物？");
        q2List.add("腹膜透析患者為什麼要重視病菌？");
        q2List.add("病菌是從那裡來的？");
        q2List.add("對腹膜透析患者來說為什麼洗手和擦乾雙手是重要的？");
        q2List.add("什麼是腹膜炎？");
        q2List.add("病菌如何進入腹膜腔？");
        q2List.add("如何得知自己可能感染腹膜炎？");
        q2List.add("如何預防腹膜炎和導管出口處發炎？");
        q2List.add("腹膜透析患者可以做運動嗎？");
        q2List.add("腹膜透析患者適合什麼樣的運動？");
        q2List.add("腹膜透析患者可不可以進行性活動？");
        q2List.add("做腹膜透析後，有沒有生育能力？");
        q2List.add("•如何丟棄使用過的透析液及用具？");
        q2List.add("如果腹膜透析患者要出國旅遊的話，有甚麼需特別注意的？");
        q2List.add("護理師告訴你出遊怎麼辦？！");
        q3List.add("蛋白質要多少才夠?");
        q3List.add("如何做血液透析?");
        q3List.add("什麼是人工腎臟??");
        q3List.add("何謂廔管異常?");
        q3List.add("廔管異常怎麼辦?");
        q3List.add("肝炎和非肝炎病患需不需要分開洗腎?");
        q3List.add("洗腎當中可能會有哪些不舒服?");
        q3List.add("如何知道我洗腎洗的好不好？什麼是適當的透析?");
        q3List.add("多少的URR才足夠呢？那Kt／V 要多少才夠?");
        q3List.add("透析不足會有什麼情形發生?");
        q3List.add("腎友如何預防及治療副甲狀腺亢進病變?");
        q3List.add("每次透析前的體重可以上升多少？水分可以攝取多少?");
        q3List.add("洗腎病人的飲食該注意些什麼?");
        q3List.add("什麼是低鉀飲食?");
        q3List.add("如何控制磷?");
        q3List.add("營養不夠會怎麼樣?");
        q4List.add("什麼是存活率?");
        q4List.add("能不能用動物的腎臟移植？");
        q4List.add("腎臟移植是否比人工腎臟血液透析要好？");
        q4List.add("在急性腎衰竭的病人若有尿毒症，是否也適合做腎臟移植？");
        q4List.add("換腎後的前三個月，常發生頻尿情形，是否與手術有關？");
        q4List.add("接受腎臟移植手術後，植入腎臟患側的下肢感覺麻刺感，是什麼原因造成？");
        q4List.add("腎臟移植成功之後，若在一段時間之內呈現穩定，無排斥的現象可否停藥？");
    }
}
