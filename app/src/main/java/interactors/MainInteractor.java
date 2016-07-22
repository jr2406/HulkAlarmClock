package interactors;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import hulkalarmmanager.android.com.MainActivity;
import listeners.MainListener;

/**
 *
 */
public interface MainInteractor {
    void setAlarm(MainActivity mainActivity, ImageButton imageButtonDate, ImageButton imageButtonDayStatus, TextView textViewSetTime, EditText editTextAlarmText, Button buttonSetAlarm,Button buttonAlarmHistory ,Button buttonAlarmSettings,MainListener mainListener);
}
