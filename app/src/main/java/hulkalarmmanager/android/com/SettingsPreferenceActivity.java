package hulkalarmmanager.android.com;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import base.BaseActivity;
import implementations.SettingsPreferencePresenterImplementation;
import interactors.SettingsPreferenceInteractor;
import presenters.SettingsPreferencePresenter;
import views.SettingsPreferenceView;

/**
 *
 */
public class SettingsPreferenceActivity extends BaseActivity implements SettingsPreferenceView {
    private SeekBar seekBarVolume;
    private Switch switchVibration, switchRingtone1, switchRingtone2, switchRingtone3;
    private Button buttonPlay1, buttonPlay2, buttonPlay3;
    private SettingsPreferencePresenter settingsPreferencePresenter;
    private Spinner spinnerAlarmText;

    @Override
    protected int getResourceId() {
        return R.layout.set_preference_activity;
    }

    @Override
    protected void setUp() {
        seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);
        switchVibration = (Switch) findViewById(R.id.switchVibration);
        switchRingtone1 = (Switch) findViewById(R.id.switchRingtone1);
        switchRingtone2 = (Switch) findViewById(R.id.switchRingtone2);
        switchRingtone3 = (Switch) findViewById(R.id.switchRingtone3);
        buttonPlay1 = (Button) findViewById(R.id.buttonPlay1);
        buttonPlay2 = (Button) findViewById(R.id.buttonPlay2);
        buttonPlay3 = (Button) findViewById(R.id.buttonPlay3);
        spinnerAlarmText = (Spinner)findViewById(R.id.spinnerAlarmText);
        settingsPreferencePresenter = new SettingsPreferencePresenterImplementation(this);
        settingsPreferencePresenter.sendViews(this, seekBarVolume, switchVibration, buttonPlay1, buttonPlay2, buttonPlay3, switchRingtone1, switchRingtone2, switchRingtone3,spinnerAlarmText);
    }

    @Override
    public void showResult() {

    }
}
