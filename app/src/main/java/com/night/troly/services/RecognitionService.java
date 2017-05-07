//package com.night.troly.services;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.os.Messenger;
//import android.os.RemoteException;
//import android.speech.RecognizerIntent;
//import android.speech.SpeechRecognizer;
//import android.support.v7.app.NotificationCompat;
//import android.util.Log;
//
//import java.lang.ref.WeakReference;
//
///**
// * Created by night on 06/05/2017.
// */
//
//public class RecognitionService extends Service {
//
//    private static RecognitionService mInstance;
//    public Context mContext;
//    private static AudioManager mAudioManager;
//    private SpeechRecognizer mSpeechRecognizer;
//    private SpeechRecognitionListener mSpeechRecognitionListener;
//    private Intent mSpeechRecognizerIntent;
//    public boolean mIsListening;
//    public volatile boolean mIsCountDownOn = false;
//
//    public static final int MSG_RECOGNIZER_START_LISTENING = 1;
//    public static final int MSG_RECOGNIZER_CANCEL_LISTENING = 2;
//    private static final String TAG = "RecognitionService";
//
//    public static final int START_NOTIFY_ID = 19940911;
//    public NotificationCompat.Builder mBuilder;
//    private NotificationManager mNotificationManager;
//
//    public final Messenger mServerMessenger = new Messenger(new IncomingHandler(this));
//
//
//    public static RecognitionService getInstance() {
//        return mInstance;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mContext = getApplicationContext();
//        mSpeechRecognitionListener = SpeechRecognitionListener.getInstance(this);
//        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//
//        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
//        mSpeechRecognizer.setRecognitionListener(mSpeechRecognitionListener);
//        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_WEB_SEARCH);
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN");
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
//
////        CommandFactory.init(mContext);
////        VoiceFactory.init(this);
////        LevenshteinDistance.init();
//
//
//        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        startForeground(START_NOTIFY_ID, createNotification());
//
//        mInstance = this;
//    }
//
//    private Notification createNotification() {
//        mBuilder = new NotificationCompat.Builder(this);
//        mBuilder.setWhen(System.currentTimeMillis());
//        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        mBuilder.setContentTitle(getString(R.string.app_name));
//        mBuilder.setContentText(getString(R.string.listening));
//        mBuilder.setOngoing(true);
//        mBuilder.setPriority(NotificationCompat.PRIORITY_MIN);
//        mBuilder.setCategory(NotificationCompat.CATEGORY_SERVICE);
//        return mBuilder.build();
//    }
//
//    // Count down timer for Jelly Bean work around
//    public CountDownTimer noLongerDie = new CountDownTimer(60000, 60000) {
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//
//        }
//
//        @Override
//        public void onFinish() {
//            mIsCountDownOn = false;
//            Message message = Message.obtain(null, MSG_RECOGNIZER_CANCEL_LISTENING);
//            try {
//                mServerMessenger.send(message);
//                message = Message.obtain(null, MSG_RECOGNIZER_START_LISTENING);
//                mServerMessenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//    public CountDownTimer mNoSpeechCountDown = new CountDownTimer(5000, 5000) {
//        @Override
//        public void onTick(long millisUntilFinished) {
//
//        }
//
//        @Override
//        public void onFinish() {
//            mIsCountDownOn = false;
//            Message message = Message.obtain(null, MSG_RECOGNIZER_CANCEL_LISTENING);
//            try {
//                mServerMessenger.send(message);
//                message = Message.obtain(null, MSG_RECOGNIZER_START_LISTENING);
//                mServerMessenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.w(TAG, " on start command");
//        int code = super.onStartCommand(intent, flags, startId);
//        try {
//            Message message = Message.obtain(null, MSG_RECOGNIZER_START_LISTENING);
//            mServerMessenger.send(message);
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }
//        return code;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.w(TAG, "on Destroy Service");
//        if (mIsCountDownOn) {
//            mIsCountDownOn = false;
//        }
//        if (mSpeechRecognizer != null) {
//            mSpeechRecognizer.destroy();
//        }
//        if (mNotificationManager != null) {
//            mNotificationManager.cancel(START_NOTIFY_ID);
//            mNotificationManager = null;
//        }
//        stopForeground(true);
//    }
//
//    private static class IncomingHandler extends Handler {
//        private WeakReference<RecognitionService> mTarget;
//
//        IncomingHandler(RecognitionService target) {
//            mTarget = new WeakReference<>(target);
//        }
//
//        @SuppressWarnings("deprecation")
//        @Override
//        public void handleMessage(Message msg) {
//            final RecognitionService target = mTarget.get();
//
//            switch (msg.what) {
//                case MSG_RECOGNIZER_START_LISTENING:
//                    if (!target.mIsListening) {
//                        mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
//                        target.mSpeechRecognizer.startListening(target.mSpeechRecognizerIntent);
//                        target.mIsListening = true;
//                        Log.w(TAG, "Listening");
//                    }
//                    break;
//                case MSG_RECOGNIZER_CANCEL_LISTENING:
//                    target.mSpeechRecognizer.cancel();
//                    target.mIsListening = false;
//                    mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
//                    Log.w(TAG, "Cancel listening");
//                    break;
//            }
//        }
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.w("bind", "on bind");
//        return mServerMessenger.getBinder();
//    }
//}
