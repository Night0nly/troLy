package com.night.troly.activities;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.night.troly.R;
import com.night.troly.adapters.ViewPagerAdapter;
import com.night.troly.dialogs.InitDialog;
import com.night.troly.fragments.BaseFragment;
import com.night.troly.fragments.HomeFragment;
import com.night.troly.fragments.TrainingFragment;
import com.night.troly.ultis.Constants;
import com.night.troly.ultis.SettingManager;
import com.night.troly.ultis.SystemHelper;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private String mCurrentFragmentTag;
    private InitDialog mInitDialog;
    private boolean mIsActivityVisible;
    private boolean mIsDataOk;

    static {
        System.loadLibrary("jnilibsvm");
    }

    public native void jniSvmPredict(String options);
    public native void jniSvmScale(String options);

    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        if (SettingManager.isDataUpToDate()) {
            switchFragment(Constants.TRAINING_FRAGMENT, null);
        } else {
            initializeData();
        }
        ButterKnife.bind(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        // Example of a call to a native method
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter;
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "HOME");
        adapter.addFragment(new TrainingFragment(), "TRAINING");
        viewPager.setAdapter(adapter);
    }
    private void switchFragment(String fragmentTag, Object data) {
        BaseFragment fragment;
        switch (fragmentTag) {
            case Constants.HOME_FRAGMENT:
                fragment = HomeFragment.newInstance();
                break;
            case Constants.TRAINING_FRAGMENT:
                fragment = TrainingFragment.newInstance();
                break;
            default:
                return;
        }

        if (data != null) {
            fragment.setData(data);
        }

        fragmentTag = fragmentTag + mFragmentManager.getBackStackEntryCount();
        mFragmentManager.beginTransaction()
                .replace(R.id.viewpager, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
        mCurrentFragmentTag = fragmentTag;
    }

    private void initializeData() {
        mInitDialog = new InitDialog();
        mInitDialog.show(getSupportFragmentManager(), null);
        mIsDataOk = false;

        (new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mIsDataOk = true;
                if (mIsActivityVisible) {
                    dismissDialogAndStartHome();
                }
            }

            @Override
            protected Void doInBackground(Void... voids) {
                ArrayList<String> fileToExtract = new ArrayList<String>();
                fileToExtract.add("data.db");
                SystemHelper.getInstance(getApplicationContext())
                        .extractZippedDatabases(fileToExtract);

                FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
                SettingManager.markDataUpToDate();
                return null;
            }
        }).execute();
    }
    private void dismissDialogAndStartHome() {
        try {
            mInitDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }

        mInitDialog = null;
        //For development part, only go to training fragment
        switchFragment(Constants.TRAINING_FRAGMENT, null);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            popAndSaveFragmentTag();
        } else {
            finish();
        }
    }
    private void popAndSaveFragmentTag() {
        mFragmentManager.popBackStackImmediate();
        int lastIndex = mFragmentManager.getBackStackEntryCount() - 1;
        mCurrentFragmentTag = mFragmentManager.getBackStackEntryAt(lastIndex).getName();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityVisible = true;
        if (mIsDataOk && mInitDialog != null) {
            dismissDialogAndStartHome();
        }
    }
    @Override
    protected void onPause() {
        mIsActivityVisible = false;
        super.onPause();
    }
}
