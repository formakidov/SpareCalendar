package com.formakidov.sparecalendar.presenter;

import com.formakidov.sparecalendar.fragment.BaseFragment;

public interface IMainPresenter {
    void onBackPressed(BaseFragment curFrag);

    void onNavigationItemSelected(int itemId);
}
