package com.formakidov.sparecalendar.view;

import com.formakidov.sparecalendar.fragment.BaseFragment;

public interface IMainView {
    void hideFab();
    void showFab();
    void changeFragment(BaseFragment f);
    void finish();
}
