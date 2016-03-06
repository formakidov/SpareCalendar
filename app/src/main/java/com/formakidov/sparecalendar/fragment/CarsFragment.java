package com.formakidov.sparecalendar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.activity.ConsumablesActivity;
import com.formakidov.sparecalendar.adapter.CarsAdapter;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.dialog.CarDialog;
import com.formakidov.sparecalendar.interfaces.IHasFabFragment;
import com.formakidov.sparecalendar.listener.ItemClickSupport;
import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.tools.Tools;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CarsFragment extends BaseFragment implements IHasFabFragment {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private CarsAdapter adapter;

    public CarsFragment() {
    }

    public static CarsFragment newInstance() {
        return new CarsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_cars, container, false);
        ButterKnife.bind(this, v);
        setupViews(v);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCars();
    }

	@Override
	protected void setupViews(View v) {
		adapter = new CarsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemClickSupport support = ItemClickSupport.addTo(recyclerView);
        support.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Car car = adapter.getItem(position);
                Intent i = new Intent(getActivity(), ConsumablesActivity.class);
                i.putExtra(Constants.EXTRA_CAR_ID, car.getId());
                startActivity(i);
                Tools.nextActivityAnimation(getActivity());
            }
        });
        // TODO: 27.02.2016 btns edit & delete
        support.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, final int position, View v) {
                showMenuDialog();
                return false;
            }
        });
    }

    private void showAddDialog() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        CarDialog dialog = new CarDialog();
        dialog.show(ft, getString(R.string.add_car));
    }

    private void showEditDialog() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        // TODO: 27.02.2016 put parcelable car in args
        CarDialog dialog = new CarDialog();
        dialog.setArguments(args);
        dialog.show(ft, getString(R.string.edit_car));
    }

    private void showDeleteDialog() {
        // TODO: 27.02.2016 are you sure?
//        deleteCar();
    }

    private void showMenuDialog() {
        String[] values = new String[]{getString(R.string.edit), getString(R.string.delete)};
        ArrayAdapter<String> dialogAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values);
        new MaterialDialog.Builder(getActivity())
                .adapter(dialogAdapter, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int pos, CharSequence charSequence) {
                        switch (pos) {
                            case 0:
                                showEditDialog();
                                break;
                            case 1:
                                showDeleteDialog();
                                break;
                        }
                        materialDialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    @Override
    public void onFabClicked() {
        showAddDialog();
    }

    private void deleteCar(Car car) {
        CarsRepository carRepo = new CarsRepository(Application.getContext());
        try {
            carRepo.delete(car);
        } catch (SQLException e) {
            // TODO: 02.03.2016
            e.printStackTrace();
        }
        updateCars();
    }

    private void editCar(Car car) {
        try {
            CarsRepository repo = new CarsRepository(Application.getContext());
            repo.save(car);
        } catch (SQLException e) {
            // TODO: 02.03.2016
            e.printStackTrace();
        }
    }

    private void updateCars() {
        try {
            CarsRepository repo = new CarsRepository(Application.getContext());
            List<Car> cars = repo.queryAll();
            adapter.addAll(cars);
        } catch (SQLException e) {
            // TODO: 27.02.2016
            e.printStackTrace();
        }
    }

}
