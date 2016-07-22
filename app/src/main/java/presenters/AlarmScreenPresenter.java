package presenters;

import android.widget.Button;
import android.widget.TextView;

import hulkalarmmanager.android.com.AlarmActivity;

/**
 *
 */
public interface AlarmScreenPresenter {
    void sendViews(AlarmActivity alarmActivity,TextView textViewAlarmMsg, Button buttonDismiss, Button buttonSnooze);
}
