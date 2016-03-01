package com.formakidov.sparecalendar.presenter;

import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.model.Consumable;
import com.formakidov.sparecalendar.view.IConsumablesView;

import java.sql.SQLException;
import java.util.List;

public class ConsumablesPresenterImpl implements IConsumablesPresenter {
    private final IConsumablesView view;

    public ConsumablesPresenterImpl(IConsumablesView view) {
        this.view = view;
    }

    @Override
    public void updateConsumables(long carId) {
        try {
            CarsRepository repo = new CarsRepository(Application.getContext());
            Car car = repo.findById(carId);
            List<Consumable> consumables = car.getConsumables();
            view.setConsumables(consumables);
        } catch (SQLException e) {
            // TODO: 02.03.2016
            e.printStackTrace();
        }
    }
}
