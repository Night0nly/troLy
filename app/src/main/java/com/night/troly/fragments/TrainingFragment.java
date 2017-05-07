package com.night.troly.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.night.troly.R;

import butterknife.ButterKnife;

/**
 * Created by night on 06/05/2017.
 */
public class TrainingFragment extends BaseFragment {

    private View mMainView;
    private static TrainingFragment mInstance;

    public static TrainingFragment newInstance() {
        if (mInstance == null) {
            mInstance = new TrainingFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater.inflate(R.layout.fragment_training, container, false);
            ButterKnife.bind(this, mMainView);
        }

        return mMainView;
    }
}
