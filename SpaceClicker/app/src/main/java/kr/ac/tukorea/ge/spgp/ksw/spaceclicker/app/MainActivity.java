package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.ac.tukorea.ge.spgp.ksw.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.AchievementManager;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object.Player;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.scene.MainScene;
import kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.util.AlarmReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends GameActivity {
    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    public static Context getContext() {
        return activity.getApplicationContext();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainScene().push();

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        mCalender = new GregorianCalendar();
    }

    public void onUpgradeButtonClick(View view) {
        Log.d("MainActivity", "onUpgradeClick");
        onPause();
        startActivity(new Intent(this, UpgradeActivity.class));
    }

    public void onAchievementButtonClick(View view) {
        Log.d("MainActivity", "onAchievementClick");
        onPause();
        startActivity(new Intent(this, AchievementActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setAlarm();
    }

    private void setAlarm() {
        Intent receiverIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Player.leftEventTime);

        Log.d("myAlarm", "setAlarm: " + Player.leftEventTime);

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
    }
}