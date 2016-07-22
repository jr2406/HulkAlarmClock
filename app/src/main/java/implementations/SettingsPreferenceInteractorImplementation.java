package implementations;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import hulkalarmmanager.android.com.R;
import hulkalarmmanager.android.com.SettingsPreferenceActivity;
import interactors.SettingsPreferenceInteractor;
import listeners.SettingsPreferenceListener;
import utils.PreferenceHandler;
import utils.Utilities;

/**
 *
 */
public class SettingsPreferenceInteractorImplementation implements SettingsPreferenceInteractor, View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    private SettingsPreferenceActivity settingsPreferenceActivity;
    private SeekBar seekBarVolume;
    private Switch switchVibration, switchRingtone1, switchRingtone2, switchRingtone3;
    private Button buttonPlay1, buttonPlay2, buttonPlay3;
    private SettingsPreferenceListener settingsPreferenceListener;
    private AudioManager audioManager = null;
    private MediaPlayer mediaPlayer = null;
    private Spinner spinnerAlarmText;

    @Override
    public void setPreferencesHere(SettingsPreferenceActivity settingsPreferenceActivity, SeekBar seekBarVolume, Switch switchVibration, Button buttonPlay1, Button buttonPlay2, Button buttonPlay3, Switch switchRingtone1, Switch switchRingtone2, Switch switchRingtone3, Spinner spinnerAlarmText, SettingsPreferenceListener settingsPreferenceListener) {
        this.settingsPreferenceActivity = settingsPreferenceActivity;
        this.seekBarVolume = seekBarVolume;
        this.switchVibration = switchVibration;
        this.switchRingtone1 = switchRingtone1;
        this.switchRingtone2 = switchRingtone2;
        this.switchRingtone3 = switchRingtone3;
        this.buttonPlay1 = buttonPlay1;
        this.buttonPlay2 = buttonPlay2;
        this.buttonPlay3 = buttonPlay3;
        this.spinnerAlarmText = spinnerAlarmText;
        this.settingsPreferenceListener = settingsPreferenceListener;
        buttonPlay1.setOnClickListener(this);
        buttonPlay2.setOnClickListener(this);
        buttonPlay3.setOnClickListener(this);
        switchRingtone1.setOnCheckedChangeListener(this);
        switchRingtone2.setOnCheckedChangeListener(this);
        switchRingtone3.setOnCheckedChangeListener(this);
        switchVibration.setOnCheckedChangeListener(this);
        spinnerAlarmText.setOnItemSelectedListener(this);
        if (PreferenceHandler.readString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("ringtone one")) {
            switchRingtone1.setChecked(true);
            switchRingtone2.setChecked(false);
            switchRingtone3.setChecked(false);
            switchVibration.setChecked(false);
        } else if (PreferenceHandler.readString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("ringtone two")) {
            switchRingtone1.setChecked(false);
            switchRingtone2.setChecked(true);
            switchRingtone3.setChecked(false);
            switchVibration.setChecked(false);
        } else if (PreferenceHandler.readString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("ringtone three")) {
            switchRingtone1.setChecked(false);
            switchRingtone2.setChecked(false);
            switchRingtone3.setChecked(true);
            switchVibration.setChecked(false);
        } else if (PreferenceHandler.readString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "").equalsIgnoreCase("vibration")) {
            switchRingtone1.setChecked(false);
            switchRingtone2.setChecked(false);
            switchRingtone3.setChecked(false);
            switchVibration.setChecked(true);
        }
        if (PreferenceHandler.readString(settingsPreferenceActivity, PreferenceHandler.ALARM_TEXT, "").equalsIgnoreCase("Hulk Say! Wake Up!")) {
            spinnerAlarmText.setPrompt("Hulk Say! Wake Up!");
        } else if (PreferenceHandler.readString(settingsPreferenceActivity, PreferenceHandler.ALARM_TEXT, "").equalsIgnoreCase("Hulk is Angry! Wake Up!")) {
            spinnerAlarmText.setPrompt("Hulk is Angry! Wake Up!");
        }
        settingsPreferenceActivity.setVolumeControlStream(AudioManager.STREAM_ALARM);
        initControls(settingsPreferenceActivity);
    }

    private void initControls(final SettingsPreferenceActivity settingsPreferenceActivity) {
        audioManager = (AudioManager) settingsPreferenceActivity.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, PreferenceHandler.readInteger(settingsPreferenceActivity, PreferenceHandler.VOLUME_PROGRESS, 0), 0);
        seekBarVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekBarVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                PreferenceHandler.writeInteger(settingsPreferenceActivity, PreferenceHandler.VOLUME_PROGRESS, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonPlay1:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(settingsPreferenceActivity, R.raw.ringtone1);
                mediaPlayer.start();
                break;
            case R.id.buttonPlay2:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(settingsPreferenceActivity, R.raw.ringtone2);
                mediaPlayer.start();
                break;
            case R.id.buttonPlay3:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(settingsPreferenceActivity, R.raw.ringtone3);
                mediaPlayer.start();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switchVibration:
                if (b) {
                    PreferenceHandler.writeString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "vibration");
                    switchRingtone1.setChecked(false);
                    switchRingtone2.setChecked(false);
                    switchRingtone3.setChecked(false);
                }
                break;
            case R.id.switchRingtone1:
                if (b) {
                    PreferenceHandler.writeString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "ringtone one");
                    switchRingtone2.setChecked(false);
                    switchRingtone3.setChecked(false);
                    switchVibration.setChecked(false);
                }

                break;
            case R.id.switchRingtone2:
                if (b) {
                    PreferenceHandler.writeString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "ringtone two");
                    switchRingtone1.setChecked(false);
                    switchRingtone3.setChecked(false);
                    switchVibration.setChecked(false);
                }
                break;
            case R.id.switchRingtone3:
                if (b) {
                    PreferenceHandler.writeString(settingsPreferenceActivity, PreferenceHandler.RINGTONE_TYPE, "ringtone three");
                    switchRingtone1.setChecked(false);
                    switchRingtone2.setChecked(false);
                    switchVibration.setChecked(false);
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        PreferenceHandler.writeString(settingsPreferenceActivity, PreferenceHandler.ALARM_TEXT, adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
