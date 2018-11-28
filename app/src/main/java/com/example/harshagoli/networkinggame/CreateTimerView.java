package com.example.harshagoli.networkinggame;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.ButterKnife;

public class CreateTimerView extends Activity {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_timer_view);
        Button b = findViewById(R.id.setTimerButton);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimer();
                new AlarmReceiver().setAlarm(getBaseContext());
            }
        });

    }

    public void setTimer() {
        alarmMgr = (AlarmManager)this.getSystemService(CreateTimerView.ALARM_SERVICE);
        Intent intent = new Intent(this, CreateTimerView.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 52);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 30, alarmIntent);


//        Intent myIntent = new Intent(this , NotifyService.class);
//        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.AM_PM, Calendar.AM);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*30 , pendingIntent);
    }

    public class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//        String message = "Hellooo, alrm worked ----";
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//        Intent intent2 = new Intent(context, TripNotification.class);
//        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent2);
        }

        public void setAlarm(Context context){
            Log.d("Carbon","Alrm SET !!");

            // get a Calendar object with current time
            Calendar cal = Calendar.getInstance();
            // add 30 seconds to the calendar object
            cal.add(Calendar.SECOND, 30);
            Intent intent = new Intent(context, AlarmReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(context, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Get the AlarmManager service
            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
        }
    }


}

