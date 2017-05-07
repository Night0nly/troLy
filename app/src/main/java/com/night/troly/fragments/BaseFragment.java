package com.night.troly.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by night on 06/05/2017.
 */

public class BaseFragment extends Fragment {
    Object mData;

    public Object getData(){
        return mData;
    }

    public void setData(Object data){
        mData = data;
    }
}
