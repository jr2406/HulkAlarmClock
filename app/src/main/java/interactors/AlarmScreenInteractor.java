package interactors;

import android.widget.Button;
import android.widget.TextView;

import hulkalarmmanager.android.com.AlarmActivity;
import listeners.AlarmScreenListener;

/**
 *
 */
public interface AlarmScreenInteractor {
    void doTaskHere(AlarmActivity alarmActivity, TextView textViewAlarmMsg, Button buttonDismiss, Button buttonSnooze, AlarmScreenListener alarmScreenListener);
}
