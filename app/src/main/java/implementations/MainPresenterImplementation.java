package implementations;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import hulkalarmmanager.android.com.MainActivity;
import interactors.MainInteractor;
import listeners.MainListener;
import presenters.MainPresenter;
import views.MainView;

/**
 *
 */
public class MainPresenterImplementation implements MainPresenter, MainListener {
    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImplementation(MainView mainView) {
        this.mainView = mainView;
        mainInteractor = new MainInteractorImplementation();
    }



    @Override
    public void onGettingTime() {
        mainView.showResultInMainActivity();
    }

    @Override
    public void setTime(MainActivity mainActivity, ImageButton imageButtonDate, ImageButton imageButtonDayStatus, TextView textViewSetTime, EditText editTextAlarmText, Button buttonSetAlarm,Button buttonAlarmHistory,Button buttonAlarmSettings) {
        mainInteractor.setAlarm(mainActivity,imageButtonDate,imageButtonDayStatus,textViewSetTime,editTextAlarmText,buttonSetAlarm,buttonAlarmHistory,buttonAlarmSettings,this);
    }
}
