package com.formakidov.sparecalendar.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.adapter.AdapterViewPagerTabsByTag;
import com.formakidov.sparecalendar.fragment.AddConsumableFragment;
import com.formakidov.sparecalendar.fragment.ReferenceBookFragment;
import com.formakidov.sparecalendar.tools.Tools;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddConsumableActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumable);// TODO: 07.03.2016
        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    protected void setupViews() {
        long consumableId = getIntent().getLongExtra(Constants.EXTRA_CONSUMABLE_ID, 0);
        int titleId;
        if (consumableId == 0) {
            titleId = R.string.toolbar_title_consumable_add;
        } else {
            titleId = R.string.toolbar_title_consumable_edit;
        }

        initToolbar(titleId);
        initFixedTabLayout(getViewPager());

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                switchTabPosition();
                Tools.hideKeyboard(AddConsumableActivity.this);
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        switchTabPosition();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_ok:
                // TODO: 07.03.2016  
//                AddConsumableFragment fragAddCar = null;
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                if (tagsByPosition == null) {
//                    tagsByPosition = adapter.getFragmentsTagsByPosition();
//                }
//                for (String tag : tagsByPosition) {
//                    BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(tag);
//                    if (fragment instanceof AddCarFragment) {
//                        fragAddCar = (AddCarFragment) fragment;
//                        break;
//                    }
//                }
//                if (fragAddCar != null) {
//                    if (fragAddCar.saveCar()) {
//                        finish();
//                    }
//                }
//
//                Tools.hideKeyboard(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ViewPager getViewPager() {
        // TODO: 07.03.2016 remove adapter
        AdapterViewPagerTabsByTag adapter = new AdapterViewPagerTabsByTag(getSupportFragmentManager());

        adapter.addFragment(
                AddConsumableFragment.newInstance(),
                getString(R.string.car_add_tab_car));

        adapter.addFragment(
                ReferenceBookFragment.newInstance(),
                getString(R.string.car_add_tab_reference_book));

        viewPager.setAdapter(adapter);

        return viewPager;
    }

    private void switchTabPosition() {
        int tabPosition = tabLayout.getSelectedTabPosition();
        switch (tabPosition) {
            case 0:
                if (menu != null) {
                    menu.findItem(R.id.toolbar_action_ok).setVisible(true);
                }
                break;
            case 1:
                if (menu != null) {
                    menu.findItem(R.id.toolbar_action_ok).setVisible(false);
                }
                break;
        }
        viewPager.setCurrentItem(tabPosition);
    }

}
