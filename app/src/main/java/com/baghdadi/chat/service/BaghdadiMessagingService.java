package com.baghdadi.chat.service;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.os.Build;
import com.baghdadi.chat.R;
import com.baghdadi.chat.data.FirebaseCore;
import com.baghdadi.chat.ui.CallActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.*;

public class BaghdadiMessagingService extends FirebaseMessagingService {
 private static final String CHANNEL="calls";
 @Override public void onNewToken(String token){FirebaseCore.init(this);FirebaseUser u=FirebaseAuth.getInstance().getCurrentUser();if(u!=null)FirebaseCore.firestore().collection("users").document(u.getUid()).update("fcmToken",token);}
 @Override public void onMessageReceived(RemoteMessage m){FirebaseCore.init(this);Map<String,String>d=m.getData();if("call".equals(d.get("kind"))&&d.get("callId")!=null){showCall(d);}else if(m.getNotification()!=null)showMessage(m.getNotification().getTitle(),m.getNotification().getBody());}
 private void showCall(Map<String,String>d){ensureChannel();Intent i=new Intent(this, CallActivity.class);i.putExtra("callId",d.get("callId"));i.putExtra("role","callee");i.putExtra("type",d.getOrDefault("type","audio"));i.putExtra("peerUid",d.get("callerId"));i.putExtra("peerName",d.getOrDefault("callerName","مكالمة واردة"));i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);PendingIntent pi=PendingIntent.getActivity(this,Objects.hash(d.get("callId")),i,PendingIntent.FLAG_UPDATE_CURRENT|(Build.VERSION.SDK_INT>=23?PendingIntent.FLAG_IMMUTABLE:0));Notification.Builder b=Build.VERSION.SDK_INT>=26?new Notification.Builder(this,CHANNEL):new Notification.Builder(this);b.setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(d.getOrDefault("callerName","مكالمة واردة")).setContentText("📞 مكالمة "+("video".equals(d.get("type"))?"فيديو":"صوتية")).setAutoCancel(true).setCategory(Notification.CATEGORY_CALL).setPriority(Notification.PRIORITY_MAX).setFullScreenIntent(pi,true).setContentIntent(pi);((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).notify(7001,b.build());}
 private void showMessage(String t,String body){ensureChannel();Notification.Builder b=Build.VERSION.SDK_INT>=26?new Notification.Builder(this,CHANNEL):new Notification.Builder(this);b.setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(t==null?"بغدادي":t).setContentText(body).setAutoCancel(true);((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).notify((int)System.currentTimeMillis(),b.build());}
 private void ensureChannel(){if(Build.VERSION.SDK_INT>=26){NotificationChannel c=new NotificationChannel(CHANNEL,"المكالمات",NotificationManager.IMPORTANCE_HIGH);c.setDescription("مكالمات بغدادي الواردة");c.enableVibration(true);((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(c);}}
}
