package com.night.troly.ultis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by night on 06/05/2017.
 */

public class PrefsHelper {

    private static PrefsHelper sHelper;

    private static SharedPreferences sPreference;

    private PrefsHelper(Context context) {
        sPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * No need to create instance anymore, use static methods
     */
    @Deprecated
    public static PrefsHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new PrefsHelper(context);
        }

        return sHelper;
    }

    public static void init(Context context) {
        sPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isInitialized() {
        return sPreference != null;
    }

    /*
     * NOTE: apply() is safe enough and faster over commit()
     */

    public static void putInt(String key, int value) {
        sPreference.edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defaultValue) {
        return sPreference.getInt(key, defaultValue);
    }

    public static void putString(String key, String value) {
        sPreference.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return sPreference.getString(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        sPreference.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sPreference.getBoolean(key, defaultValue);
    }
}
