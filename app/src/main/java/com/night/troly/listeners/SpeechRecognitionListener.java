//package com.night.troly.listeners;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.os.RemoteException;
//import android.speech.RecognitionListener;
//import android.speech.SpeechRecognizer;
//import android.util.Log;
//import android.widget.Toast;
//
////import com.night.troly.services.RecognitionService;
//
//import java.util.ArrayList;
//
///**
// * Created by night on 06/05/2017.
// */
//
//public class SpeechRecognitionListener implements RecognitionListener {
//
//    private RecognitionService mService;
//    private static SpeechRecognitionListener mInstance;
//    private Handler mHandler = new Handler();
//
//    private static final String TAG = "SpeechRecognition";
//
//    public static SpeechRecognitionListener getInstance(RecognitionService service) {
//        if (mInstance == null) {
//            mInstance = new SpeechRecognitionListener(service);
//        }
//        return mInstance;
//    }
//
//    private SpeechRecognitionListener(RecognitionService service) {
//        mService = service;
//    }
//
//    @Override
//    public void onReadyForSpeech(Bundle params) {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
//            mService.mIsCountDownOn =  true;
//            mService.mNoSpeechCountDown.start();
//        }
//        mService.noLongerDie.cancel();
//        mService.noLongerDie.start();
//        Log.w(TAG, "onReadyForSpeech");
//    }
//
//    @Override
//    public void onBeginningOfSpeech() {
//        if( mService.mIsCountDownOn){
//            mService.mIsCountDownOn = false;
//            mService.mNoSpeechCountDown.cancel();
//        }
//        Log.w(TAG, "onBeginningOfSpeech");
//    }
//
//    @Override
//    public void onRmsChanged(float rmsdB) {
//    }
//
//    @Override
//    public void onBufferReceived(byte[] buffer) {
//        Log.w(TAG, "onBufferReceived");
//    }
//
//    @Override
//    public void onEndOfSpeech() {
//        Log.w(TAG, "onEndOfSpeech");
//    }
//
//    @Override
//    public void onError(int error) {
//        Log.w(TAG, "onError");
//        Message message = Message.obtain(null, RecognitionService.MSG_RECOGNIZER_CANCEL_LISTENING);
//        try {
//            mService.mServerMessenger.send(message);
//            message = Message.obtain(null, RecognitionService.MSG_RECOGNIZER_START_LISTENING);
//            mService.mServerMessenger.send(message);
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }
//
//        switch (error) {
//            case SpeechRecognizer.ERROR_AUDIO:
//                Log.w(TAG, "error = ERROR_AUDIO");
//                break;
//            case SpeechRecognizer.ERROR_CLIENT:
//                Log.w(TAG, "error = ERROR_CLIENT");
//                break;
//            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
//                Log.w(TAG, "error = ERROR_INSUFFICIENT_PERMISSIONS");
//                break;
//            case SpeechRecognizer.ERROR_NETWORK:
//                Log.w(TAG, "error = ERROR_NETWORK");
//                break;
//            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
//                Log.w(TAG, "error = ERROR_NETWORK_TIMEOUT");
//                break;
//            case SpeechRecognizer.ERROR_NO_MATCH:
//                Log.w(TAG, "error = ERROR_NO_MATCH");
//                break;
//            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
//                Log.w(TAG, "error = ERROR_RECOGNIZER_BUSY");
//                break;
//            case SpeechRecognizer.ERROR_SERVER:
//                Log.w(TAG, "error = ERROR_SERVER");
//                break;
//            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
//                Log.w(TAG, "error = ERROR_SPEECH_TIMEOUT");
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public void onResults(Bundle results) {
//        Log.d(TAG, "onResults");
//        ArrayList<String> texts = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//        Toast.makeText(mService.mContext, "" + texts, Toast.LENGTH_LONG).show();
//        Log.w("SpeechRecognition", "" + texts);
////        if (texts != null && texts.size() > 0) {
//////            VoiceFactory.onProcess(mService.mContext, texts);
////        }
//        Message message = Message.obtain(null, RecognitionService.MSG_RECOGNIZER_CANCEL_LISTENING);
//        try {
//            mService.mServerMessenger.send(message);
//            message = Message.obtain(null, RecognitionService.MSG_RECOGNIZER_START_LISTENING);
//            mService.mServerMessenger.send(message);
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onPartialResults(Bundle partialResults) {
//        Log.w(TAG, "onPartialResults");
//    }
//
//    @Override
//    public void onEvent(int eventType, Bundle params) {
//        Log.w(TAG, "onEvent");
//    }
//}
