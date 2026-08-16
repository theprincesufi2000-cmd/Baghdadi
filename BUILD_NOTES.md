## ملاحظة البناء
لا يوجد `google-services.json` لأن المشروع يهيئ Firebase عبر FirebaseOptions مباشرة، لذلك لا يحتاج إلى اسم حزمة مسجل مسبقاً في Google Services.

افتح المجلد في Android Studio، ثم Sync Project with Gradle Files. أو استخدم GitHub Actions المرفق.

قبل التشغيل الفعلي تأكد من نشر قواعد Firebase ووظيفة FCM، ومن إضافة TURN حقيقي في CallActivity لإتاحة أعلى نسبة نجاح على شبكات الهاتف.
