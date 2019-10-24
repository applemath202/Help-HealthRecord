package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Switch;

import com.jingheng.a105project.R;
import com.jingheng.a105project.helper.PlayReceiver;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    private NotificationManager manager;

    private static final int ACTION_ID = 0;

    // 通知頻道分類代碼
    private String channelId = "net.macdidi.notify02.notify.channel.default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 取得NotificationManager物件
        manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        // 建立與設定Notify channel
        createChannel(channelId, "Default", "Zahamena default notify channel");
    }

    public void clickActionSend(View view) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,1);

        Intent intent = new Intent(this, PlayReceiver.class);
        intent.putExtra("msg", "play_hskay");

        PendingIntent pi = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
        Log.d("testAlarm", "click");
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