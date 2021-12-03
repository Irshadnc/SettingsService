package com.codeberry.settingsService;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class SystemSettingsManager {
    private static SystemSettingsManager sSystemSettingsManager;
    private SettingsFormatter mSetingsFormatter;

    private SystemSettingsManager() {

        mSetingsFormatter = new SettingsFormatter();
    }

    /**
     * brief Get instance of the SystemSettingsManager
     *
     * @return
     */
    public static SystemSettingsManager getInstance() {
        if (null == sSystemSettingsManager) {
            sSystemSettingsManager = new SystemSettingsManager();
        }
        return sSystemSettingsManager;
    }

    /**
     * Get vehicle model
     */
    public String getVehicleModel() {

        return mSetingsFormatter.getVehicleModel();
    }
}
