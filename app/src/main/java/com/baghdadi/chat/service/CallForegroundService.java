package com.baghdadi.chat.service;
import android.app.*;import android.content.*;import android.os.*;import com.baghdadi.chat.R;
public class CallForegroundService extends Service {
 public static final String CHANNEL="active_call";
 @Override public void onCreate(){super.onCreate();if(Build.VERSION.SDK_INT>=26){NotificationChannel c=new NotificationChannel(CHANNEL,"المكالمة الحالية",NotificationManager.IMPORTANCE_LOW);((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(c);}Notification.Builder b=Build.VERSION.SDK_INT>=26?new Notification.Builder(this,CHANNEL):new Notification.Builder(this);b.setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("بغدادي").setContentText("المكالمة جارية").setOngoing(true);startForeground(8001,b.build());}
 @Override public int onStartCommand(Intent i,int f,int id){return START_NOT_STICKY;}
 @Override public android.os.IBinder onBind(Intent i){return null;}
}
