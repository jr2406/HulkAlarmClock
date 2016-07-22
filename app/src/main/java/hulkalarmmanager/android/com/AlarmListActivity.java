package hulkalarmmanager.android.com;

import android.widget.ListView;

import adapters.AlarmAdapter;
import base.BaseActivity;
import pojo.AlarmPojo;

/**
 *
 */
public class AlarmListActivity extends BaseActivity {
    private ListView listViewAlarm;
    @Override
    protected int getResourceId() {
        return R.layout.alarm_list_activity;
    }

    @Override
    protected void setUp() {
        listViewAlarm = (ListView)findViewById(R.id.listViewAlarm);
        listViewAlarm.setAdapter(new AlarmAdapter(this, AlarmPojo.listAll(AlarmPojo.class)));
    }
}
