package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.net.Inet4Address;

import hulkalarmmanager.android.com.AlarmActivity;
import service.AlarmService;

/**
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentAlarmScreen = new Intent(context, AlarmActivity.class);
        intentAlarmScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentAlarmScreen);
        context.stopService(new Intent(context, AlarmService.class));
    }
}
