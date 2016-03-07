package com.formakidov.sparecalendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterViewPagerTabsByTag extends AdapterViewPagerTabs {

    private ArrayList<String> _tagsByPosition = new ArrayList<>();
    private boolean _isInstantiateItemInvoked = false;

    public AdapterViewPagerTabsByTag(FragmentManager manager) {
        super(manager);
    }

    //This method should be invoked if instantiateItem was invoked any time
    public ArrayList<String> getFragmentsTagsByPosition() throws RuntimeException {
        if (_isInstantiateItemInvoked) {
            return _tagsByPosition;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void addFragment(Fragment fragment, String title) {
        super.addFragment(fragment, title);

        //Reserve place for tag values for instantiateItem()
        _tagsByPosition.add(null);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        String tag = createdFragment.getTag();
        _tagsByPosition.set(position, tag);
        _isInstantiateItemInvoked = true;
        return createdFragment;
    }

}
