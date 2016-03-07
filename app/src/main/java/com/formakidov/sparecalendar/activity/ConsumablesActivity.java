package com.formakidov.sparecalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.adapter.ConsumablesAdapter;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.listener.ItemClickSupport;
import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.model.Consumable;
import com.formakidov.sparecalendar.tools.Tools;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConsumablesActivity extends BaseActivity {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private ConsumablesAdapter adapter;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumables);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        carId = intent.getLongExtra(Constants.EXTRA_CAR_ID, 0);

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateConsumables();
    }

    private void updateConsumables() {
        try {
            CarsRepository repo = new CarsRepository(Application.getContext());
            Car car = repo.findById(carId);
            List<Consumable> consumables = car.getConsumables();
            adapter.addAll(consumables);
        } catch (SQLException e) {
            // TODO: 02.03.2016
            e.printStackTrace();
        }
    }

    @Override
    protected void setupViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new ConsumablesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Consumable consumable = adapter.getItem(position);
                Intent i = new Intent(ConsumablesActivity.this, ConsumableDetailsActivity.class);
                i.putExtra(Constants.EXTRA_CONSUMABLE_ID, consumable.getId());
                startActivity(i);
                Tools.nextActivityAnimation(ConsumablesActivity.this);
            }
        });
    }

}
