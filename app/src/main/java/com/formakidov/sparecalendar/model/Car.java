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
    public static final String FIELD_DISTANCE = "distance";
    public static final String FIELD_LAST_UPDATE = "last_update";

    @DatabaseField(columnName = FIELD_NAME, canBeNull = true)
    private String name;
    @DatabaseField(columnName = FIELD_COMMENT, canBeNull = true)
    private String comment;
    @DatabaseField(columnName = FIELD_DISTANCE, canBeNull = true)
    private long distance;
    @DatabaseField(columnName = FIELD_LAST_UPDATE, canBeNull = true)
    private long lastUpdate;
    @ForeignCollectionField
    private ForeignCollection<Consumable> consumables;

    Car() {
    }

    public Car(String name, String comment, long distance, long lastUpdate) {
        this.name = name;
        this.comment = comment;
        this.distance = distance;
        this.lastUpdate = lastUpdate;
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

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
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
}