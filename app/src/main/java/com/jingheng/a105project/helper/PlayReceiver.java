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
import com.jingheng.a105project.activity.BloodSugarActivity;
import com.jingheng.a105project.activity.SportActivity;
import com.jingheng.a105project.activity.WeightActivity;
import com.jingheng.a105project.model.BloodSugar;

import androidx.core.app.NotificationCompat;

public class PlayReceiver extends BroadcastReceiver {

    private NotificationManager manager;

    // id
    private static final int ACTION_ID = 0;
    private static final int WAKEUP_ID = 1;
    private static final int SLEEP_ID = 2;
    private static final int BREAKFAST_ID = 3;
    private static final int LUNCH_ID = 4;
    private static final int DINNER_ID = 5;
    private static final int SPORT_ID = 6;
    private static final int WEIGHT_ID = 7;

    // 通知頻道分類代碼
    private String wakeUpId = "notify.channel.wake.up";
    private String sleepId = "notify.channel.sleep";
    private String breakfastId = "notify.channel.breakfast";
    private String lunchId = "notify.channel.lunch";
    private String dinnerId = "notify.channel.dinner";
    private String sportId = "notify.channel.sport";
    private String weightId = "notify.channel.weight";

    @Override
    public void onReceive(Context context, Intent intent) {

        // 取得NotificationManager物件
        manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bundle bData = intent.getExtras();
        if (bData.get("title").equals("activity_app")) {
            switch (String.valueOf(bData.get("kind"))) {
                case "wakeUp":
                    // 建立與設定Notify channel
                    createChannel(wakeUpId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_wakeup =
                            new NotificationCompat.Builder(context, wakeUpId);

                    // 設定小圖示、時間、標題和訊息
                    builder_wakeup.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("血壓")
                            .setContentText("起床後要量血壓，量完記得記錄唷");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_wakeup = new Intent(context, BloodActivity.class);
                    PendingIntent forwardIntent_wakeup =
                            PendingIntent.getActivity(context, 0, i_wakeup, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_wakeup.setContentIntent(forwardIntent_wakeup);

                    // 設定通知為可以清除
                    builder_wakeup.setOngoing(false);
                    // 建立通知物件
                    Notification notification_wake = builder_wakeup.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(WAKEUP_ID, notification_wake);
                    break;
                case "sleep":
                    // 建立與設定Notify channel
                    createChannel(sleepId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_sleep =
                            new NotificationCompat.Builder(context, sleepId);

                    // 設定小圖示、時間、標題和訊息
                    builder_sleep.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("量血壓")
                            .setContentText("睡前要記得量血壓，量完記得記錄唷");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_sleep = new Intent(context, BloodActivity.class);
                    PendingIntent forwardIntent_sleep =
                            PendingIntent.getActivity(context, 0, i_sleep, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_sleep.setContentIntent(forwardIntent_sleep);

                    // 設定通知為可以清除
                    builder_sleep.setOngoing(false);
                    // 建立通知物件
                    Notification notification_sleep = builder_sleep.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(SLEEP_ID, notification_sleep);
                    break;
                case "breakfast":
                    // 建立與設定Notify channel
                    createChannel(breakfastId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_breakfast =
                            new NotificationCompat.Builder(context, breakfastId);

                    // 設定小圖示、時間、標題和訊息
                    builder_breakfast.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("量血糖")
                            .setContentText("吃完早餐記得量血糖，量完記得記錄唷");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_breakfast = new Intent(context, BloodSugarActivity.class);
                    PendingIntent forwardIntent_breakfast =
                            PendingIntent.getActivity(context, 0, i_breakfast, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_breakfast.setContentIntent(forwardIntent_breakfast);

                    // 設定通知為可以清除
                    builder_breakfast.setOngoing(false);
                    // 建立通知物件
                    Notification notification_breakfast = builder_breakfast.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(BREAKFAST_ID, notification_breakfast);
                    break;
                case "lunch":
                    // 建立與設定Notify channel
                    createChannel(lunchId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_lunch =
                            new NotificationCompat.Builder(context, lunchId);

                    // 設定小圖示、時間、標題和訊息
                    builder_lunch.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("量血糖")
                            .setContentText("吃完中餐記得量血糖，量完記得記錄唷");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_lunch = new Intent(context, BloodSugarActivity.class);
                    PendingIntent forwardIntent_lunch =
                            PendingIntent.getActivity(context, 0, i_lunch, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_lunch.setContentIntent(forwardIntent_lunch);

                    // 設定通知為可以清除
                    builder_lunch.setOngoing(false);
                    // 建立通知物件
                    Notification notification_lunch = builder_lunch.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(LUNCH_ID, notification_lunch);
                    break;
                case "dinner":
                    // 建立與設定Notify channel
                    createChannel(dinnerId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_dinner =
                            new NotificationCompat.Builder(context, dinnerId);

                    // 設定小圖示、時間、標題和訊息
                    builder_dinner.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("量血糖")
                            .setContentText("吃完晚餐記得量血糖，量完記得記錄唷");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_dinner = new Intent(context, BloodSugarActivity.class);
                    PendingIntent forwardIntent_dinner =
                            PendingIntent.getActivity(context, 0, i_dinner, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_dinner.setContentIntent(forwardIntent_dinner);

                    // 設定通知為可以清除
                    builder_dinner.setOngoing(false);
                    // 建立通知物件
                    Notification notification_dinner = builder_dinner.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(DINNER_ID, notification_dinner);
                    break;
                case "sport":
                    // 建立與設定Notify channel
                    createChannel(sportId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_sport =
                            new NotificationCompat.Builder(context, sportId);

                    // 設定小圖示、時間、標題和訊息
                    builder_sport.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("運動")
                            .setContentText("死胖子動起來");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_sport = new Intent(context, SportActivity.class);
                    PendingIntent forwardIntent_sport =
                            PendingIntent.getActivity(context, 0, i_sport, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_sport.setContentIntent(forwardIntent_sport);

                    // 設定通知為可以清除
                    builder_sport.setOngoing(false);
                    // 建立通知物件
                    Notification notification_sport = builder_sport.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(SPORT_ID, notification_sport);
                    break;

                case "weight":
                    // 建立與設定Notify channel
                    createChannel(weightId, "Default", "Zahamena default notify channel");
                    // 建立NotificationCompat.Builder物件
                    NotificationCompat.Builder builder_weight =
                            new NotificationCompat.Builder(context, weightId);

                    // 設定小圖示、時間、標題和訊息
                    builder_weight.setSmallIcon(R.drawable.bloodsugar)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentTitle("體重")
                            .setContentText("死胖子量體重拉");

                    // 建立點擊通知需要的PendingIntent物件
                    // 第三個參數指定點擊以後要啟動的Activity元件，
                    // 使用getIntent()設定為自己，就是Notify02Activity
                    Intent i_weight = new Intent(context, WeightActivity.class);
                    PendingIntent forwardIntent_weight =
                            PendingIntent.getActivity(context, 0, i_weight, 0);
                    // 設定點擊通知的PendingIntent物件
                    builder_weight.setContentIntent(forwardIntent_weight);

                    // 設定通知為可以清除
                    builder_weight.setOngoing(false);
                    // 建立通知物件
                    Notification notification_weight = builder_weight.build();
                    // 使用ACTION_ID為編號發出通知
                    manager.notify(WEIGHT_ID, notification_weight);
                    break;
            }
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
