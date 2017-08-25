package com.codespurt.googleanalytics.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codespurt.googleanalytics.R;
import com.codespurt.googleanalytics.engine.MyApplication;

/**
 * Created by CodeSpurt on 25-08-2017.
 */

public class FooterFragment extends Fragment {

    public FooterFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_footer, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Footer Fragment");
    }
}
