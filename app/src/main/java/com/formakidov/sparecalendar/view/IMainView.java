package com.formakidov.sparecalendar.view;

import com.formakidov.sparecalendar.fragment.BaseFragment;

public interface IMainView {
    void hideFab();
    void showFab();
    void setFragment(BaseFragment f);
    void finish();
}
