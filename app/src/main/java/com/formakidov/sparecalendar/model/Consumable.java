package com.formakidov.sparecalendar.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Consumable.TABLE_NAME)
public class Consumable extends BaseModel {
    public static final String TABLE_NAME = "consumables";
    public static final String FIELD_CAR_ID = "car_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_COMMENT = "comment";
    public static final String FIELD_DISTANCE = "distance";

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = FIELD_CAR_ID)
    private Car car;
    @DatabaseField(columnName = FIELD_NAME, canBeNull = true)
    private String name;
    @DatabaseField(columnName = FIELD_COMMENT, canBeNull = true)
    private String comment;
    @DatabaseField(columnName = FIELD_DISTANCE, canBeNull = false, defaultValue = "0")
    private long distance;
    private long kmLeft;

    Consumable() {
    }

    public Consumable(Car car, String name, String comment, long distance) {
        this.car = car;
        this.name = name;
        this.comment = comment;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setKmLeft(long kmLeft) {
        this.kmLeft = kmLeft;
    }

    public long getKmLeft() {
        return kmLeft;
    }

    @Override
    public String toString() {
        return "Car [" + car.getName() + "], " +
                "name [" + name + "], " +
                "comment [" + comment + "], " +
                "distance [" + distance + "], " +
                "kmLeft [" + kmLeft + "], ";
    }
}