package com.caidie.skzs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.caidie.skzs.R;
import com.caidie.skzs.adapter.MaplistAdapter;

public class InfoListActivity extends AppCompatActivity {
    TextView tv_title;
    RecyclerView home_recy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        tv_title = findViewById(R.id.tv_title);
        home_recy = findViewById(R.id.home_recy);
        String type = getIntent().getStringExtra("type");
        if(type.equals("1")){
            tv_title.setText("微信收款记录");
        }else{
            tv_title.setText("支付宝收款记录");
        }
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        home_recy.setLayoutManager(manager);
//        MaplistAdapter adapter = new MaplistAdapter(context);
//        home_recy.setAdapter(adapter);
//        adapter.setData(list);

    }
}
