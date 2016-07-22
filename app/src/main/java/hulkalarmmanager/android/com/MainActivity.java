package hulkalarmmanager.android.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import base.BaseActivity;
import implementations.MainPresenterImplementation;
import presenters.MainPresenter;
import views.MainView;

public class MainActivity extends BaseActivity implements MainView {
    private ImageButton imageButtonDate, imageButtonDayStatus;
    private TextView textViewSetTime;
    private MainPresenter mainPresenter;
    private EditText editTextAlarmMsg;
    private Button buttonSetAlarm,buttonAlarmHistory,buttonAlarmSettings;


    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUp() {
        imageButtonDate = (ImageButton) findViewById(R.id.imageButtonDate);
        imageButtonDayStatus = (ImageButton) findViewById(R.id.imageButtonDayStatus);
        textViewSetTime = (TextView) findViewById(R.id.textViewSetTime);
        editTextAlarmMsg = (EditText) findViewById(R.id.editTextCustomMsg);
        buttonSetAlarm = (Button) findViewById(R.id.buttonSetAlarm);
        buttonAlarmHistory = (Button)findViewById(R.id.buttonAlarmHistory);
        buttonAlarmSettings = (Button)findViewById(R.id.buttonAlarmSettings);
        mainPresenter = new MainPresenterImplementation(this);
        mainPresenter.setTime(this, imageButtonDate, imageButtonDayStatus, textViewSetTime, editTextAlarmMsg, buttonSetAlarm,buttonAlarmHistory,buttonAlarmSettings);
    }


    @Override
    public void showResultInMainActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intentAlarmList = new Intent(MainActivity.this, AlarmListActivity.class);
                startActivity(intentAlarmList);

                Toast.makeText(MainActivity.this, "Alarm has been set", Toast.LENGTH_LONG).show();
            }
        });
    }
}
