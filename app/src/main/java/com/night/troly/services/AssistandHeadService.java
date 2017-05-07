//package com.night.troly.services;
//
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Binder;
//import android.os.IBinder;
//import android.support.v4.app.NotificationCompat;
//import android.util.DisplayMetrics;
//import android.view.WindowManager;
//
//import java.lang.ref.WeakReference;
//
///**
// * Created by night on 06/05/2017.
// */
//
//public class AssistantHeadService extends Service
//        implements FloatingViewListener {
//
//    private NotificationManager mNotificationManager;
//    private FloatingViewManager mFloatingViewManager;
//    private IBinder mChatHeadServiceBinder;
//    private NotificationCompat.Builder mBuilder;
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // 既にManagerが存在していたら何もしない
//        if (mFloatingViewManager != null) {
//            return START_STICKY;
//        }
//
//        final DisplayMetrics metrics = new DisplayMetrics();
//        final WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        windowManager.getDefaultDisplay().getMetrics(metrics);
//
//        mChatHeadServiceBinder = new SearchHeadServiceBinder(this);
//
//        mFloatingViewManager = new FloatingViewManager(this, this);
//        mFloatingViewManager.setFixedTrashIconImage(R.drawable.icon_trash_with_background);
//        final FloatingViewManager.Options options = new FloatingViewManager.Options();
//        options.shape = FloatingViewManager.SHAPE_CIRCLE;
//        options.overMargin = (int) (5 * metrics.density);
//        mFloatingViewManager.initWindowView(options);
//
////        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////        mBuilder = RecognitionService.getInstance().mBuilder;
////        updateNotification(getResources().getString(R.string.listening));
//        return START_REDELIVER_INTENT;
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mFloatingViewManager != null) {
//            mFloatingViewManager.removeAllViewToWindow();
//            mFloatingViewManager = null;
//        }
//        if (mNotificationManager != null) {
////            updateNotification(getResources().getString(R.string.say_assistant));
//            mNotificationManager = null;
//        }
//
//        super.onDestroy();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return mChatHeadServiceBinder;
//    }
//
//    @Override
//    public void onFinishFloatingView() {
//        stopSelf();
//    }
//
//    private void updateNotification(String text) {
//        if (mBuilder != null) {
//            mBuilder.setContentText(text);
//            mNotificationManager.notify(RecognitionService.START_NOTIFY_ID, mBuilder.build());
//        }
//    }
//
//    public static class SearchHeadServiceBinder extends Binder {
//
//        private final WeakReference<AssistantHeadService> mService;
//
//        SearchHeadServiceBinder(AssistantHeadService service) {
//            mService = new WeakReference<>(service);
//        }
//
//        public AssistantHeadService getService() {
//            return mService.get();
//        }
//    }
//
//}
