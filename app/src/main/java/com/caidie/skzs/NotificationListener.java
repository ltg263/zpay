package com.caidie.skzs;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.caidie.skzs.config.Constant;
import com.caidie.skzs.payutils.OrderMatcher;


public class NotificationListener extends NotificationListenerService {

    OrderMatcher matcher = new OrderMatcher();

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("send message");
        toggleNotificationListenerService();
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        //当连接成功时调用，一般在开启监听后会回调一次该方法
    }


    //  当收到一条消息时回调，sbn里面带有这条消息的具体信息
    //    getPackageName(); //获取发送通知的应用程序包名
    //    isClearable(); //通知是否可被清除
    //    getId(); //获取通知id
    //    getKey(); //获取通知的key
    //    getPostTime(); //通知的发送时间
    //    getNotification(); //获取Notification
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        System.out.println("send message:"+sbn.toString());
        Bundle extras = sbn.getNotification().extras;
        String pkg = sbn.getPackageName();
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);
        System.out.println("send message"+title);
        System.out.println("send messagetext："+text);
        if(pkg.contains("com.tencent.mm") || pkg.contains("com.eg.android.AlipayGphone")){
            if(title.equals("微信支付")){
                Intent i = new Intent(Constant.ACTION);
                i.putExtra("data", text);
                i.putExtra("title", "微信支付");
                sendBroadcast(i);
            }else if( title.equals("收款通知")){
                Intent i = new Intent(Constant.ACTION);
                i.putExtra("data", text);
                i.putExtra("title", "支付宝支付");
                sendBroadcast(i);
            }
        }
//        Order order = matcher.match(pkg, title, text);
//        if (order != null) {
//            OrderQueue.getQueue().put(order);
//        }
    }

    //当移除一条消息的时候回调，sbn是被移除的消息
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        System.out.println("send message");
    }

    @Override
    public void onRebind(Intent intent) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toggleNotificationListenerService() {
        System.out.println("send message");
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, NotificationListener.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, NotificationListener.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

}
