package com.night.troly.ultis;


/**
 * Created by night on 06/05/2017.
 */

public final class SettingManager {

    private static final String CURRENT_DATA_VERSION = "data_ver";

    private SettingManager() {}

    public static boolean isDataUpToDate() {
        return PrefsHelper.getString(CURRENT_DATA_VERSION, "none").equals(Constants.DATA_NEWEST_VERSION);
    }

    public static void markDataUpToDate() {
        PrefsHelper.putString(CURRENT_DATA_VERSION, Constants.DATA_NEWEST_VERSION);
    }
}
