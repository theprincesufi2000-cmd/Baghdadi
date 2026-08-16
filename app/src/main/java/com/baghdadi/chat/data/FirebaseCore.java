package com.baghdadi.chat.data;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public final class FirebaseCore {
 public static final String PROJECT_ID="chat-d61b6";
 public static final String API_KEY="AIzaSyAu4gHjpRSHJxHJo132azuWnZDHq_ytn4Q";
 public static final String APP_ID="1:1018554911310:android:34a016bad317c540b0d11f";
 public static final String DB_URL="https://chat-d61b6-default-rtdb.firebaseio.com";
 public static final String STORAGE="chat-d61b6.firebasestorage.app";
 private FirebaseCore(){}
 public static void init(Context c){
   if(!FirebaseApp.getApps(c).isEmpty()) return;
   FirebaseOptions o=new FirebaseOptions.Builder().setProjectId(PROJECT_ID).setApplicationId(APP_ID).setApiKey(API_KEY).setDatabaseUrl(DB_URL).setStorageBucket(STORAGE).build();
   FirebaseApp.initializeApp(c,o);
 }
 public static FirebaseAuth auth(){return FirebaseAuth.getInstance();}
 public static FirebaseFirestore firestore(){return FirebaseFirestore.getInstance();}
 public static FirebaseDatabase rtdb(){return FirebaseDatabase.getInstance(DB_URL);}
 public static FirebaseStorage storage(){return FirebaseStorage.getInstance();}
}
