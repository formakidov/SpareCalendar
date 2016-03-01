package com.formakidov.sparecalendar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.formakidov.sparecalendar.R;

public class SettingsFragment extends BaseFragment {

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_settings, container, false);

        setupViews(v);

        return v;
    }

    private void setupViews(View v) {

    }
}
