package hulkalarmmanager.android.com;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import base.BaseActivity;
import implementations.AlarmScreenPresenterImplementation;
import presenters.AlarmScreenPresenter;
import views.AlarmScreenView;

/**
 *
 */
public class AlarmActivity extends BaseActivity implements AlarmScreenView {
    private TextView textViewAlarmMsg;
    private Button buttonDismiss,buttonSnooze;
    private AlarmScreenPresenter alarmScreenPresenter;

    @Override
    protected int getResourceId() {
        return R.layout.alarm_screen;
    }

    @Override
    protected void setUp() {
        textViewAlarmMsg = (TextView) findViewById(R.id.textViewAlarmMsg);
        buttonDismiss = (Button)findViewById(R.id.buttonDismiss);
        buttonSnooze = (Button)findViewById(R.id.buttonSnooze);
        alarmScreenPresenter = new AlarmScreenPresenterImplementation(this);
        alarmScreenPresenter.sendViews(this,textViewAlarmMsg,buttonDismiss,buttonSnooze);

    }

    @Override
    public void showResult() {
        Toast.makeText(AlarmActivity.this, "Snooze Activity", Toast.LENGTH_SHORT).show();
    }
}
