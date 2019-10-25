package com.jingheng.a105project.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.BloodActivity;

import androidx.core.app.NotificationCompat;

public class PlayReceiver extends BroadcastReceiver {

    private NotificationManager manager;

    private static final int ACTION_ID = 0;

    // 通知頻道分類代碼
    private String channelId = "net.macdidi.notify02.notify.channel.default";

    @Override
    public void onReceive(Context context, Intent intent) {

        // 取得NotificationManager物件
        manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 建立與設定Notify channel
        createChannel(channelId, "Default", "Zahamena default notify channel");


        Bundle bData = intent.getExtras();
        if (bData.get("msg").equals("play_hskay")) {
            Log.d("testAlarm","success");
            // 建立NotificationCompat.Builder物件
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, channelId);

            // 設定小圖示、時間、標題和訊息
            builder.setSmallIcon(R.drawable.notify_big_picture)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("量血壓")
                    .setContentText("該量血壓，量完記得記錄唷");



            // 建立點擊通知需要的PendingIntent物件
            // 第三個參數指定點擊以後要啟動的Activity元件，
            // 使用getIntent()設定為自己，就是Notify02Activity
            Intent i = new Intent(context, BloodActivity.class);
            PendingIntent forwardIntent =
                    PendingIntent.getActivity(context,0,i,0);
            // 設定點擊通知的PendingIntent物件
            builder.setContentIntent(forwardIntent);

            // 設定通知為可以清除
            builder.setOngoing(false);
            // 建立通知物件
            Notification notification = builder.build();
            // 使用ACTION_ID為編號發出通知
            manager.notify(ACTION_ID, notification);
        }
    }

    // 建立與設定Notify channel
    // 加入裝置版本的判斷，應用程式就不用把最低版本設定為API level 26
    private void createChannel(String id, String name, String desc) {
        // 如果系統版本低於 Android 8.0 （API level 26）就不執行設定
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        // 建立channel物件，參數依序為channel代碼、名稱與等級
        NotificationChannel nChannel = new NotificationChannel(
                id, name, NotificationManager.IMPORTANCE_DEFAULT);
        // 設定channel的說明
        nChannel.setDescription(desc);
        // 設定channel物件
        manager.createNotificationChannel(nChannel);
    }
}
