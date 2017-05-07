package com.night.troly.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.night.troly.R;
//import com.night.troly.services.RecognitionService;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by night on 06/05/2017.
 */

public class HomeFragment extends BaseFragment {

    private View mMainView;
    private static HomeFragment mInstance;

    public static HomeFragment newInstance() {
        if (mInstance == null) {
            mInstance = new HomeFragment();
        }
        return mInstance;
    }

//    @Bind(R.id.microphone_image)
    ImageView mMicrophoneButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.bind(this, mMainView);

//            startRecognitionService();
//            openAssistantChatHead();
        }

//        mMicrophoneButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (RecognitionService.getInstance() != null) {
//                    Messenger serverMessenger = RecognitionService.getInstance().mServerMessenger;
//                    Message message = Message.obtain(null, RecognitionService.MSG_RECOGNIZER_CANCEL_LISTENING);
//                    try {
//                        serverMessenger.send(message);
//                        message = Message.obtain(null, RecognitionService.MSG_RECOGNIZER_START_LISTENING);
//                        serverMessenger.send(message);
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
        return mMainView;
    }

//    private void startRecognitionService() {
//        Intent recognitionService = new Intent(getActivity(), RecognitionService.class);
//        getActivity().startService(recognitionService);
//    }
//
//    private void openAssistantChatHead() {
//        Intent intent = new Intent(getActivity(), AssistantHeadService.class);
//        getActivity().startService(intent);
//    }
}
