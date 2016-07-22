package pojo;

import com.orm.SugarRecord;

/**
 *
 */
public class AlarmPojo extends SugarRecord {
    String time;
    String message;
    int pendingIntentId;

    public int getPendingIntentId() {
        return pendingIntentId;
    }

    public void setPendingIntentId(int pendingIntentId) {
        this.pendingIntentId = pendingIntentId;
    }


    public AlarmPojo() {

    }

    public AlarmPojo(String time, String message, int pendingIntentId) {
        this.time = time;
        this.message = message;
        this.pendingIntentId = pendingIntentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public Long getId() {
        return super.getId();
    }
}
