package com.codeberry.settingsService;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class SettingsFormatter {

    public SettingsFormatter() {

    }

    /**
     *
     * @return
     */
    public String getVehicleModel() {
        File sdcard = Environment.getExternalStoragePublicDirectory("text");
        File dir = new File(sdcard.getAbsolutePath());
        StringBuilder text = new StringBuilder();
        if (dir.exists()) {
            File file = new File(dir, "sample.txt");
            FileOutputStream os = null;
            text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
                Log.w("suh", e.toString());
                //You'll need to add proper error handling here
            }
        }
        Log.w("suh", "vehicle model is " + text);
        return text.toString();
    }
}
