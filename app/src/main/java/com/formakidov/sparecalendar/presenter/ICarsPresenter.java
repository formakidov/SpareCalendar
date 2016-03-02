package com.formakidov.sparecalendar.presenter;

import com.formakidov.sparecalendar.model.Car;

public interface ICarsPresenter {
    void deleteCar(Car car);
    void editCar(Car car);
    void onResume();
}
