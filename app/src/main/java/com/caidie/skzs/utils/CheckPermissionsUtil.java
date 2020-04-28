package com.caidie.skzs.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import com.caidie.skzs.R;
import com.caidie.skzs.view.SimpleDialog;

import java.util.Set;

public class CheckPermissionsUtil {

    /**
     * 检测通知监听服务是否被授权
     *
     * @param context
     * @return
     */
    public static boolean isNotificationListenerEnabled(Context context) {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(context);
        if (packageNames.contains(context.getPackageName())) {
            return true;
        }
        return false;
    }
    public static void startAuthority(final Context mContext) {
        SimpleDialog simpleDialog = new SimpleDialog(mContext, "需要开启访问通知权限", new SimpleDialog.OnButtonClick() {
            @Override
            public void onNegBtnClick() {

            }

            @Override
            public void onPosBtnClick() {
                startService(mContext);
            }
        });
        simpleDialog.show();
    }
    public static void startService(Context mContext) {
        try {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void notify(View view,Context mContext){
//        Notification.Builder builder = new Notification.Builder(mContext);
//        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, mIntent, 0);
//        builder.setContentIntent(pendingIntent);
//        builder.setSmallIcon(R.drawable.ic_launcher_background);
//        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_background));
//        builder.setAutoCancel(true);
//        builder.setContentTitle("普通通知");
//        builder.setContentText("内容");
//        builder.setVisibility(Notification.VISIBILITY_PUBLIC);
//
//        Intent hangIntent = new Intent();
//        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        hangIntent.setClass(mContext, mContext.getClass());
//        PendingIntent hangPendingIntent = PendingIntent.getActivity(mContext, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//        builder.setFullScreenIntent(hangPendingIntent, true);
//
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());
    }
}
