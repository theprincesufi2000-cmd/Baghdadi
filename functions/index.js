const { onDocumentCreated } = require('firebase-functions/v2/firestore');
const { initializeApp } = require('firebase-admin/app');
const { getFirestore } = require('firebase-admin/firestore');
const { getMessaging } = require('firebase-admin/messaging');

initializeApp();
const db = getFirestore();

exports.notifyIncomingCall = onDocumentCreated('calls/{callId}', async (event) => {
  const snap = event.data;
  if (!snap) return;
  const call = snap.data();
  if (!call || call.status !== 'preparing' || !call.calleeId) return;

  const userSnap = await db.collection('users').doc(call.calleeId).get();
  const user = userSnap.data();
  if (!user || !user.fcmToken) return;

  const callerSnap = await db.collection('users').doc(call.callerId).get();
  const caller = callerSnap.data() || {};

  await getMessaging().send({
    token: user.fcmToken,
    data: {
      kind: 'call',
      callId: snap.id,
      callerId: call.callerId,
      callerName: String(caller.username || 'مستخدم'),
      type: String(call.type || 'audio')
    },
    android: { priority: 'high', ttl: 45000 }
  });
});
