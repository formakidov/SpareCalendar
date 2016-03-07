package com.formakidov.sparecalendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerTabs extends FragmentPagerAdapter {

    private final List<Fragment> listFragments = new ArrayList<>();
    private final List<String> listTitles = new ArrayList<>();

    public AdapterViewPagerTabs(FragmentManager manager) {
        super(manager);
    }

    public void addFragment(Fragment fragment, String title) {
        listFragments.add(fragment);
        listTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);
    }

}
