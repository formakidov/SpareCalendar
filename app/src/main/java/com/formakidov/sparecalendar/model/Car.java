package com.formakidov.sparecalendar.model;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = Car.TABLE_NAME)
public class Car extends BaseModel {
    public static final String TABLE_NAME = "cars";

    public static final String FIELD_NAME = "name";
    public static final String FIELD_COMMENT = "comment";
    public static final String FIELD_MILEAGE = "mileage";
    public static final String FIELD_GEARBOX_TYPE = "gearbox_type";
    public static final String FIELD_LATEST_UPDATE = "latest_update";

    @DatabaseField(columnName = FIELD_NAME)
    private String name;
    @DatabaseField(columnName = FIELD_COMMENT)
    private String comment;
    @DatabaseField(columnName = FIELD_MILEAGE)
    private long mileage;
    @DatabaseField(columnName = FIELD_GEARBOX_TYPE)
    private int gearboxType;
    @DatabaseField(columnName = FIELD_LATEST_UPDATE)
    private long latestUpdate;
    @ForeignCollectionField
    private ForeignCollection<Consumable> consumables;

    Car() {
    }

    public Car(long id) {
        this.id = id;
        name = "";
        comment = "";
        mileage = 0;
        gearboxType = 0;
    }

    public Car(String name, String comment, long mileage, int gearboxType, long latestUpdate) {
        this.name = name;
        this.comment = comment;
        this.mileage = mileage;
        this.gearboxType = gearboxType;
        this.latestUpdate = latestUpdate;
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

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public List<Consumable> getConsumables() {
        CloseableIterator<Consumable> iterator = consumables.closeableIterator();
        List<Consumable> consumables = new ArrayList<>();
        try {
            while (iterator.hasNext()) {
                Consumable consumable = iterator.next();
                consumables.add(consumable);
            }
        } finally {
            try {
                iterator.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consumables;
    }

    public int getGearboxType() {
        return gearboxType;
    }

    public int getConsumablesCount() {
        return consumables.size();
    }

    public void setGearboxType(int gearboxType) {
        this.gearboxType = gearboxType;
    }
}