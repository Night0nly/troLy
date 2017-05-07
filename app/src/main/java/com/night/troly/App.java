package com.night.troly;

import android.app.Application;

import com.night.troly.ultis.PrefsHelper;
import com.night.troly.ultis.SettingManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by night on 07/05/2017
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrefsHelper.init(this);
        if (SettingManager.isDataUpToDate()) {
            FlowManager.init(new FlowConfig.Builder(this).build());
        }
    }
}
