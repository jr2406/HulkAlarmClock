package presenters;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import hulkalarmmanager.android.com.SettingsPreferenceActivity;

/**
 *
 */
public interface SettingsPreferencePresenter {
    void sendViews(SettingsPreferenceActivity settingsPreferenceActivity, SeekBar seekBarVolume, Switch switchVibration, Button buttonPlay1, Button buttonPlay2, Button buttonPlay3, Switch switchRingtone1, Switch switchRingtone2, Switch switchRingtone3, Spinner spinnerAlarmText);
}
