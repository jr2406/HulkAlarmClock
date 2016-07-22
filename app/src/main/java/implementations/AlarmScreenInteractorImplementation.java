package implementations;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

import hulkalarmmanager.android.com.AlarmActivity;
import hulkalarmmanager.android.com.R;
import interactors.AlarmScreenInteractor;
import listeners.AlarmScreenListener;
import pojo.AlarmPojo;
import receiver.AlarmReceiver;
import service.AlarmService;
import utils.PreferenceHandler;

/**
 *
 */
public class AlarmScreenInteractorImplementation implements AlarmScreenInteractor, View.OnClickListener {
    private AlarmActivity alarmActivity;
    private TextView textViewAlarmMsg;
    private Button buttonDismiss, buttonSnooze;
    private AlarmScreenListener alarmScreenListener;
    private Uri uri;
    private Ringtone ringtone;
    private File file;
    private AudioManager audioManager = null;
    private MediaPlayer mediaPlayer = null;

    @Override
    public void doTaskHere(AlarmActivity alarmActivity, TextView textViewAlarmMsg, Button buttonDismiss, Button buttonSnooze, AlarmScreenListener alarmScreenListener) {
        this.alarmActivity = alarmActivity;
        this.textViewAlarmMsg = textViewAlarmMsg;
        this.buttonDismiss = buttonDismiss;
        this.buttonSnooze = buttonSnooze;
        this.alarmScreenListener = alarmScreenListener;
        if (PreferenceHandler.readString(alarmActivity, PreferenceHandler.ALARM_TEXT, "").equalsIgnoreCase("Hulk Say! Wake Up!")) {
            textViewAlarmMsg.setText("Hulk Say! Wake Up!");
        } else if (PreferenceHandler.readString(alarmActivity, PreferenceHandler.ALARM_TEXT, "").equalsIgnoreCase("Hulk is Angry! Wake Up!")) {
            textViewAlarmMsg.setText("Hulk is Angry! Wake Up!");
        } else {
            textViewAlarmMsg.setText("Hulk SMASH! Wake Up!");
        }
        if (PreferenceHandler.readString(alarmActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("ringtone one")) {
            mediaPlayer = MediaPlayer.create(alarmActivity, R.raw.ringtone1);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else if (PreferenceHandler.readString(alarmActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("ringtone two")) {
            mediaPlayer = MediaPlayer.create(alarmActivity, R.raw.ringtone2);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else if (PreferenceHandler.readString(alarmActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("ringtone three")) {
            mediaPlayer = MediaPlayer.create(alarmActivity, R.raw.ringtone3);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else if (PreferenceHandler.readString(alarmActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("vibration")) {
            Vibrator vibrator = (Vibrator) alarmActivity.getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern={0,100,500,100,500,100,500,100,500,100,500};
            vibrator.vibrate(pattern,10);
        } else if(PreferenceHandler.readString(alarmActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("")) {
            mediaPlayer = MediaPlayer.create(alarmActivity, R.raw.ringtone1);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        buttonDismiss.setOnClickListener(this);
        buttonSnooze.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDismiss:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                alarmActivity.finish();
                break;
            case R.id.buttonSnooze:
                Calendar calendar = Calendar.getInstance();
                calendar.get(Calendar.HOUR);
                calendar.get(Calendar.MINUTE);
                alarmActivity.startService(new Intent(alarmActivity.getBaseContext(), AlarmService.class));
                Intent intent = new Intent(alarmActivity, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(alarmActivity, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) alarmActivity.getSystemService(alarmActivity.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis() + 50 * 10000, pendingIntent);
                alarmScreenListener.getResult();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                alarmActivity.finish();
                break;
        }
    }
}
