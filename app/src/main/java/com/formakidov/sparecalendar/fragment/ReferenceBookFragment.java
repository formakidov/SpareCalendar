package com.formakidov.sparecalendar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.formakidov.sparecalendar.R;

public class ReferenceBookFragment extends BaseFragment {

    public ReferenceBookFragment() {
    }

    public static ReferenceBookFragment newInstance() {
        return new ReferenceBookFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_reference_book, container, false);

        setupViews(v);

        return v;
    }

	@Override
	protected void setupViews(View v) {

    }
}
