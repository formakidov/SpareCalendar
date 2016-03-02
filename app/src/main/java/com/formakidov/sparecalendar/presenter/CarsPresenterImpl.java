package com.formakidov.sparecalendar.presenter;

import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.tools.Tools;
import com.formakidov.sparecalendar.view.ICarView;

import java.sql.SQLException;
import java.util.List;

public class CarsPresenterImpl implements ICarsPresenter {
    private final ICarView view;

    public CarsPresenterImpl(ICarView view) {
        this.view = view;
    }

    @Override
    public void deleteCar(Car car) {
        CarsRepository carRepo = new CarsRepository(Application.getContext());
        try {
            carRepo.delete(car);
        } catch (SQLException e) {
            // TODO: 02.03.2016  
            e.printStackTrace();
        }
        updateCars();
    }

    @Override
    public void editCar(Car car) {
        try {
            CarsRepository repo = new CarsRepository(Application.getContext());
            repo.save(car);
        } catch (SQLException e) {
            // TODO: 02.03.2016
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        updateCars();
        Tools.cancelNotification();
    }

    private void updateCars() {
        try {
            CarsRepository repo = new CarsRepository(Application.getContext());
            List<Car> cars = repo.queryAll();
            view.setCars(cars);
        } catch (SQLException e) {
            // TODO: 27.02.2016
            e.printStackTrace();
        }
    }

}
