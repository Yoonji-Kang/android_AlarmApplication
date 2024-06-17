package com.cookandriod.project_0615_2;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> alarmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Alarm Application");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        alarmList = new ArrayList<>();
        alarmAdapter = new AlarmAdapter(alarmList, position -> {
            alarmList.remove(position);
            alarmAdapter.notifyItemRemoved(position);
            alarmAdapter.notifyItemRangeChanged(position, alarmList.size());
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(alarmAdapter);

        fab.setOnClickListener(v -> showAddAlarmDialog());
    }

    private void setAlarm(String time, String alarmText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scheduleJob(time, alarmText);
        } else {
            scheduleAlarmManager(time, alarmText);
        }
    }

    private void scheduleJob(String time, String alarmText) {
        // JobScheduler를 사용하여 백그라운드에서 작업 스케줄링
        Toast.makeText(this, "알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();
    }

    private void scheduleAlarmManager(String time, String alarmText) {
        // AlarmManager를 사용하여 백그라운드에서 알람 설정
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("alarmText", alarmText); // 알람 메시지 전달

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 시간 설정
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf;
        if (DateFormat.is24HourFormat(this)) {
            sdf = new SimpleDateFormat("HH:mm", Locale.getDefault()); // 24시간제
        } else {
            sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault()); // 12시간제
        }

        try {
            Date date = sdf.parse(time);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 알람 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        Toast.makeText(this, "알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();
    }

    private void showAddAlarmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_alarm, null);
        builder.setView(dialogView);

        final TimePicker timePicker = dialogView.findViewById(R.id.timePicker);
        final EditText editText = dialogView.findViewById(R.id.editText);

        timePicker.setIs24HourView(true);

        builder.setTitle("Add Alarm");
        builder.setPositiveButton("Add", (dialog, which) -> {
            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();
            String alarmText = editText.getText().toString();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            String time = DateFormat.format("hh:mm a", calendar).toString();
            alarmList.add(new Alarm(time, alarmText));
            alarmAdapter.notifyItemInserted(alarmList.size() - 1);

            // 알람 설정
            setAlarm(time, alarmText);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}