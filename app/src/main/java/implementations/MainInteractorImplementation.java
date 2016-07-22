package implementations;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

import hulkalarmmanager.android.com.AlarmListActivity;
import hulkalarmmanager.android.com.MainActivity;
import hulkalarmmanager.android.com.R;
import hulkalarmmanager.android.com.SettingsPreferenceActivity;
import interactors.MainInteractor;
import listeners.MainListener;
import pojo.AlarmPojo;
import presenters.MainPresenter;
import receiver.AlarmReceiver;
import service.AlarmService;

/**
 *
 */
public class MainInteractorImplementation implements MainInteractor, View.OnClickListener {
    private int mHour, mMinute, mDay, mMonth, mYear;
    private MainActivity mainActivity;
    private Calendar calendar;
    private String strAlarmMsg = "Wake up Wake up!", strTime, strDate;
    private ImageButton imageButtonDate, imageButtonDayStatus;
    private TextView textViewSetTime;
    private EditText editTextAlarmMsg;
    private Button buttonSetAlarm,buttonAlarmHistory,buttonAlarmSettings;
    private MainListener mainListener;
    private DatePickerDialog datePickerDialog;

    @Override
    public void setAlarm(MainActivity mainActivity, ImageButton imageButtonDate, ImageButton imageButtonDayStatus, TextView textViewSetTime, EditText editTextAlarmMsg, Button buttonSetAlarm,Button buttonAlarmHistory ,Button buttonAlarmSettings,MainListener mainListener) {
        this.mainActivity = mainActivity;
        this.imageButtonDate = imageButtonDate;
        this.textViewSetTime = textViewSetTime;
        this.mainListener = mainListener;
        this.imageButtonDayStatus = imageButtonDayStatus;
        this.editTextAlarmMsg = editTextAlarmMsg;
        this.buttonSetAlarm = buttonSetAlarm;
        this.buttonAlarmHistory = buttonAlarmHistory;
        this.buttonAlarmSettings = buttonAlarmSettings;
        textViewSetTime.setOnClickListener(this);
        imageButtonDate.setOnClickListener(this);
        buttonSetAlarm.setOnClickListener(this);
        buttonAlarmHistory.setOnClickListener(this);
        buttonAlarmSettings.setOnClickListener(this);
        changeTimeStatusIcon(mainActivity);
    }

    private void changeTimeStatusIcon(MainActivity mainActivity) {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
           imageButtonDayStatus.setBackgroundResource(R.drawable.sunrise);
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            imageButtonDayStatus.setBackgroundResource(R.drawable.day_icon);
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            imageButtonDayStatus.setBackgroundResource(R.drawable.sunset_icon);
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            imageButtonDayStatus.setBackgroundResource(R.drawable.night_icon);
        }
    }

    private void showTimeDialog(MainActivity mainActivity) {
        calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(mainActivity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                strTime = hour + ":" + minute;
                textViewSetTime.setText("Alarm Time - " + hour + ":" + minute);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSetTime:
                showTimeDialog(mainActivity);
                break;
            case R.id.imageButtonDate:
                showDateDialog(mainActivity);
                break;
            case R.id.buttonAlarmHistory:
                if(AlarmPojo.listAll(AlarmPojo.class).size() ==0) {
                    Toast.makeText(mainActivity, "No Alarm has been set So far.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intentAlarmList = new Intent(mainActivity, AlarmListActivity.class);
                    mainActivity.startActivity(intentAlarmList);
                }
                break;
            case R.id.buttonSetAlarm:
                if (!editTextAlarmMsg.getText().toString().equalsIgnoreCase("")) {
                    strAlarmMsg = editTextAlarmMsg.getText().toString().trim();
                }
                if (strTime!=null &&strDate!=null) {
                    mainActivity.startService(new Intent(mainActivity.getBaseContext(), AlarmService.class));
                    Intent intent = new Intent(mainActivity, AlarmReceiver.class);
                    int _id;
                    Random random = new Random();
                    _id = random.nextInt(100);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(mainActivity, _id, intent,0);
                    AlarmManager alarmManager = (AlarmManager) mainActivity.getSystemService(mainActivity.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                    AlarmPojo alarmPojo = new AlarmPojo(strTime+ "," + strDate, strAlarmMsg, _id);
                    alarmPojo.save();
                    mainListener.onGettingTime();
                } else {
                    Toast.makeText(mainActivity, "Please Check Date or Time", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonAlarmSettings:
                Intent intentAlarmSettings = new Intent(mainActivity, SettingsPreferenceActivity.class);
                mainActivity.startActivity(intentAlarmSettings);
                break;
        }
    }

    private void showDateDialog(MainActivity mainActivity) {

        calendar = Calendar.getInstance();

        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(mainActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                calendar.set(Calendar.YEAR, mYear);
                calendar.set(Calendar.DAY_OF_MONTH, mDay);
                calendar.set(Calendar.MONTH, mMonth);
                strDate = mDay + "/" + mMonth + "/" + mYear;
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


}
