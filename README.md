# بغدادي — Native Android Java

إعادة بناء أصلية لتطبيق بغدادي بدون HTML/WebView. الواجهة مبنية بـ Android Views وJava، والبيانات عبر Firebase.

## المزايا
- تسجيل/دخول باسم المستخدم وكلمة المرور.
- Firestore للمستخدمين والدردشات والرسائل والإشارات الخاصة بالمكالمات.
- Realtime Database لـ Presence وTyping مع onDisconnect.
- Firebase Storage للصور والفيديو والملفات.
- Firebase Cloud Messaging لإيقاظ التطبيق بالمكالمات الواردة.
- WebRTC Native للصوت والفيديو.
- صلاحيات Android للميكروفون والكاميرا والإشعارات.
- واجهة عربية RTL بتصميم داكن قريب جداً من نسخة الويب.
- لا توجد WebView ولا ملفات HTML في التطبيق.

## إعداد Firebase
بيانات مشروع Firebase الحالية مضمّنة في `FirebaseCore.java` حتى لا يعتمد المشروع على google-services.json.
يجب تفعيل:
- Authentication > Email/Password
- Firestore
- Realtime Database
- Storage
- Cloud Messaging API

## نشر Functions والقواعد
```bash
firebase login
firebase use chat-d61b6
firebase deploy --only firestore:rules,firestore:indexes,database,storage,functions
```

وظيفة `notifyIncomingCall` ترسل FCM عالي الأولوية عند إنشاء مكالمة، حتى يمكن إظهار إشعار المكالمة عندما يكون التطبيق في الخلفية.

## WebRTC / TURN
المشروع يحتوي STUN فقط. لإجبار نجاح الاتصال على شبكات CGNAT/Symmetric NAT يجب إضافة TURN حقيقي إلى `CallActivity.java` في قائمة `IceServer`، مع بيانات اعتماد قصيرة العمر من خادمك. لا تضع مفتاح TURN دائم في التطبيق.

## ملاحظات البناء
- Android Gradle Plugin 8.7.3
- compileSdk/targetSdk 35
- minSdk 24
- Java 17
- Firebase Android BoM 34.16.0
- WebRTC Android M144 من webrtc-sdk

## ملاحظات الصلاحيات
التطبيق يطلب RECORD_AUDIO وCAMERA فقط عند الحاجة. إذا رفض المستخدم الإذن، يوجهه إلى صفحة إعدادات التطبيق بدلاً من إبقاء المكالمة معلقة.
