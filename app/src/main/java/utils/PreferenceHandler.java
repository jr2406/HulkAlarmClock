package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class PreferenceHandler {
    public static final String PREF_NAME = "AlARM_PREFERENCES";
    public static final int MODE = Context.MODE_PRIVATE;
    public static final String VOLUME_PROGRESS = "volume_progress";
    public static final String RINGTONE_TYPE = "ringtone_type";
    public static final String ALARM_TEXT = "alarm_text";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    // integer
    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    // string
    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }
}
