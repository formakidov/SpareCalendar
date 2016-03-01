package com.formakidov.sparecalendar.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class BaseModel {
    @DatabaseField(generatedId = true)
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
