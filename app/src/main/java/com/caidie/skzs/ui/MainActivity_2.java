package com.caidie.skzs.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.caidie.skzs.NotificationListener;
import com.caidie.skzs.R;
import com.caidie.skzs.view.SimpleDialog;

import java.util.Set;

public class MainActivity_2 extends AppCompatActivity {
    TextView tv;
    public static final String ACTION = "com.codingblock.receive.intent.action.MyReceiver";
    private SimpleDialog simpleDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        tv = findViewById(R.id.tv);
        MyReceiver receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter(ACTION));
        toggleNotificationListenerService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("是否开启：",isNotificationListenerEnabled(this)+"");
        if(!isNotificationListenerEnabled(this)){
            startAuthority();
        }
    }


    private void startAuthority() {
        simpleDialog = new SimpleDialog(this, "需要开启访问通知权限", new SimpleDialog.OnButtonClick() {
            @Override
            public void onNegBtnClick() {

            }

            @Override
            public void onPosBtnClick() {
                startService();
            }
        });
        simpleDialog.show();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("接收到的消息为：" + intent.getStringExtra("data"));
            tv.setText(intent.getStringExtra("data"));
            dialog(intent.getStringExtra("data"),intent.getStringExtra("title"));
        }
    }

    private void dialog(String str,String title) {
        SimpleDialog simpleDialog = new SimpleDialog(this, str,title);
        simpleDialog.show();

    }
    public void startService() {
        try {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private long exitTime = 0;
    /**
     * 连续点击两下物理返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
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


    public void notify(View view){
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background));
        builder.setAutoCancel(true);
        builder.setContentTitle("微信");
        builder.setContentText("内容");
        builder.setVisibility(Notification.VISIBILITY_PUBLIC);

        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(this, MainActivity_2.class);
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    /**
     * 检测通知监听服务是否被授权
     *
     * @param context
     * @return
     */
    public boolean isNotificationListenerEnabled(Context context) {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(context);
        if (packageNames.contains(context.getPackageName())) {
            return true;
        }
        return false;
    }
    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, NotificationListener.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        pm.setComponentEnabledSetting(new ComponentName(this, NotificationListener .class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}
