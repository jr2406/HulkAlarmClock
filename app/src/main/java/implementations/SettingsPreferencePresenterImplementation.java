package implementations;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import hulkalarmmanager.android.com.SettingsPreferenceActivity;
import interactors.SettingsPreferenceInteractor;
import listeners.SettingsPreferenceListener;
import presenters.SettingsPreferencePresenter;
import views.SettingsPreferenceView;

/**
 *
 */
public class SettingsPreferencePresenterImplementation implements SettingsPreferencePresenter, SettingsPreferenceListener {
    SettingsPreferenceView settingsPreferenceView;
    SettingsPreferenceInteractor settingsPreferenceInteractor;
    public SettingsPreferencePresenterImplementation(SettingsPreferenceView settingsPreferenceView)
    {
        this.settingsPreferenceView = settingsPreferenceView;
        settingsPreferenceInteractor = new SettingsPreferenceInteractorImplementation();
    }

    @Override
    public void sendViews(SettingsPreferenceActivity settingsPreferenceActivity, SeekBar seekBarVolume, Switch switchVibration, Button buttonPlay1, Button buttonPlay2, Button buttonPlay3, Switch switchRingtone1, Switch switchRingtone2, Switch switchRingtone3, Spinner spinnerAlarmText) {
        settingsPreferenceInteractor.setPreferencesHere(settingsPreferenceActivity,seekBarVolume,switchVibration,buttonPlay1,buttonPlay2,buttonPlay3,switchRingtone1,switchRingtone2,switchRingtone3,spinnerAlarmText,this);
    }

    @Override
    public void gettingResult() {

    }
}
