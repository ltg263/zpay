package com.caidie.skzs.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.caidie.skzs.R;
import com.caidie.skzs.adapter.MaplistAdapter;
import com.caidie.skzs.bean.GeneralIncomeBean;
import com.caidie.skzs.config.Constant;
import com.caidie.skzs.utils.CheckPermissionsUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    RecyclerView home_recy;
    RecyclerView home_recy_b;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        home_recy = findViewById(R.id.home_recy);
        home_recy_b = findViewById(R.id.home_recy_b);
        home_recy.setNestedScrollingEnabled(false);
        home_recy_b.setNestedScrollingEnabled(false);
        init();
    }


    private void init() {
        receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter(Constant.ACTION));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        home_recy.setLayoutManager(manager);
        LinearLayoutManager manager_b = new LinearLayoutManager(this);
        manager_b.setOrientation(LinearLayoutManager.VERTICAL);
        home_recy_b.setLayoutManager(manager_b);

        MaplistAdapter adapter = new MaplistAdapter(this);
        home_recy.setAdapter(adapter);

        MaplistAdapter adapter_b = new MaplistAdapter(this);
        home_recy_b.setAdapter(adapter_b);
        ArrayList<GeneralIncomeBean> list = new ArrayList<>();
        for(int i=0;i<6;i++){
            GeneralIncomeBean generalIncomeBean = new GeneralIncomeBean();
            if (i==0){
                generalIncomeBean.setTime("今日");
                generalIncomeBean.setSdd((i+2)+"");
                generalIncomeBean.setFkdd((i+1)+"");
                generalIncomeBean.setZsr("￥"+(i+1*1));
            }else if(i==1){
                generalIncomeBean.setTime("昨日");
                generalIncomeBean.setSdd((i+2)+"");
                generalIncomeBean.setFkdd((i+1)+"");
                generalIncomeBean.setZsr("￥"+(i+1*1));
            }else if(i==2){
                generalIncomeBean.setTime("本周");
                generalIncomeBean.setSdd((i+2)+"");
                generalIncomeBean.setFkdd((i+1)+"");
                generalIncomeBean.setZsr("￥"+(i+1*1));
            }else if(i==3){
                generalIncomeBean.setTime("上周");
                generalIncomeBean.setSdd((i+2)+"");
                generalIncomeBean.setFkdd((i+1)+"");
                generalIncomeBean.setZsr("￥"+(i+1*1));
            }else if(i==4){
                generalIncomeBean.setTime("本月");
                generalIncomeBean.setSdd((i+2)+"");
                generalIncomeBean.setFkdd((i+1)+"");
                generalIncomeBean.setZsr("￥"+(i+1*1));
            }else if(i==5){
                generalIncomeBean.setTime("上月");
                generalIncomeBean.setSdd((i+2)+"");
                generalIncomeBean.setFkdd((i+1)+"");
                generalIncomeBean.setZsr("￥"+(i+1*1));
            }
            list.add(generalIncomeBean);
            Log.w("list111",generalIncomeBean.toString());
        }
        adapter.setData(list);
        adapter_b.setData(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w("是否开启：", CheckPermissionsUtil.isNotificationListenerEnabled(this)+"");
        if(!CheckPermissionsUtil.isNotificationListenerEnabled(this)){
            CheckPermissionsUtil.startAuthority(this);
        }
    }


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("接收到的消息为：" + intent.getStringExtra("data"));
            tv.setText(intent.getStringExtra("data"));
        }
    }


    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次后退键将退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
