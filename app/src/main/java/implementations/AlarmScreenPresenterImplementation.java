package implementations;

import android.widget.Button;
import android.widget.TextView;

import hulkalarmmanager.android.com.AlarmActivity;
import interactors.AlarmScreenInteractor;
import listeners.AlarmScreenListener;
import presenters.AlarmScreenPresenter;
import views.AlarmScreenView;

/**
 *
 */
public class AlarmScreenPresenterImplementation implements AlarmScreenPresenter, AlarmScreenListener {
    private AlarmScreenView alarmScreenView;
    private AlarmScreenInteractor alarmScreenInteractor;
    public  AlarmScreenPresenterImplementation(AlarmScreenView alarmScreenView)
    {
        this.alarmScreenView = alarmScreenView;
        alarmScreenInteractor = new AlarmScreenInteractorImplementation();
    }
    @Override
    public void sendViews(AlarmActivity alarmActivity,TextView textViewAlarmMsg, Button buttonDismiss, Button buttonSnooze) {
        alarmScreenInteractor.doTaskHere(alarmActivity,textViewAlarmMsg,buttonDismiss,buttonSnooze,this);
    }

    @Override
    public void getResult() {
        alarmScreenView.showResult();
    }
}
