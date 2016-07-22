package adapters;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hulkalarmmanager.android.com.MainActivity;
import hulkalarmmanager.android.com.R;
import pojo.AlarmPojo;
import receiver.AlarmReceiver;
import service.AlarmService;

/**
 *
 */
public class AlarmAdapter extends BaseAdapter {
    private Activity activity;
    private List<AlarmPojo> alarmList;
    private static LayoutInflater inflater = null;
    private AlarmPojo book;

    public AlarmAdapter(Activity activity, List<AlarmPojo> alarmList) {
        this.activity = activity;
        this.alarmList = alarmList;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alarmList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            view = inflater.inflate(R.layout.alarm_list_items, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.textViewAlarmTitle = (TextView) view.findViewById(R.id.textViewAlarmTitle);
            holder.textViewAlarmTime = (TextView) view.findViewById(R.id.textViewAlarmTime);
            holder.buttonCancelAlarm = (Button) view.findViewById(R.id.buttonCancelAlarm);

            /************  Set holder with LayoutInflater ************/
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.textViewAlarmTitle.setText(alarmList.get(position).getMessage());
        holder.textViewAlarmTime.setText(alarmList.get(position).getTime());
        holder.buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(activity, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, alarmList.get(position).getPendingIntentId(), myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarmManager = (AlarmManager) activity.getSystemService(activity.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                long id = alarmList.get(position).getId();
                book = AlarmPojo.findById(AlarmPojo.class,id );
                book.delete();
                alarmList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(activity, "Alarm has been Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView textViewAlarmTitle, textViewAlarmTime;
        public Button buttonCancelAlarm;

    }
}
