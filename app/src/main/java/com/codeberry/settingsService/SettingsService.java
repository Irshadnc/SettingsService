package com.codeberry.settingsService;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.codeberry.settingsinterfacemanager.ISettinsgInterface;

import java.util.Random;

public class SettingsService extends Service {
    private SystemSettingsManager mSystemSettingsManager;
    private String mVehicleModel;
    //DbHelper db;



    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mSystemSettingsManager = SystemSettingsManager.getInstance();
        mVehicleModel = mSystemSettingsManager.getVehicleModel();
        Toast.makeText(getApplicationContext(), " oncreate", Toast.LENGTH_LONG).show();
        Log.w("Veh", "Service OnCrete Called");
        Toast.makeText(getApplicationContext(), "string is" + mVehicleModel, Toast.LENGTH_SHORT).show();


        DbHelper db=new DbHelper(this);
        Data data1=new Data(100,0);
        Data data2=new Data(200,0);
        Data data3=new Data(300,0);
        Data data4=new Data(400,0);
        Data data5=new Data(500,3);
        Data data6=new Data(600,3);

        db.insertdata(data1);
        db.insertdata(data2);
        db.insertdata(data3);
        db.insertdata(data4);
        db.insertdata(data5);
        db.insertdata(data6);




    }






    /**
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w("Veh", "Service onBind Called");
        return settingInterface;
    }

    /**
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.w("Veh", "Service onStartCommand Called");
        startServiceAsForegroundService();
        return START_STICKY;

    }

    /**
     *
     */
    private final ISettinsgInterface.Stub settingInterface = new ISettinsgInterface.Stub() {
        @Override
        public long generateRandomNumber() throws RemoteException {
            return new Random().nextLong();
        }

        @Override
        public String getVehicleModel() throws RemoteException {
            return (null == mVehicleModel ? mSystemSettingsManager.getVehicleModel() : mVehicleModel);
        }
    };

    /**
     *
     */
    private void startServiceAsForegroundService() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.w("Veh", "Snotfication channel");
            String NOTIFICATION_CHANNEL_ID = "com.codeberry.settingsService";
            String channelName = "My Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            startForeground(2, notification);
        }





    }
}