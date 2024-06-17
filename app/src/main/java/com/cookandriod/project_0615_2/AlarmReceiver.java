package com.cookandriod.project_0615_2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String alarmText = intent.getStringExtra("alarmText");

        // Notification을 통해 알림 표시
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle("알람")
                .setContentText(alarmText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        // Android Oreo 이상에서는 채널을 설정해야 함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알람 채널 생성
            NotificationChannel channel = new NotificationChannel("default", "알람 채널", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        // 알림 표시
        notificationManager.notify(0, builder.build());
    }
}
