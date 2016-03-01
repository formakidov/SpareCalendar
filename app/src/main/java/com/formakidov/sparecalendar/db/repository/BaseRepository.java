package com.formakidov.sparecalendar.db.repository;

import android.content.Context;

import com.formakidov.sparecalendar.db.helper.DBHelper;

public abstract class BaseRepository<T> extends BaseOrmLiteRepository<T, Long> {
    public BaseRepository(Context context) {
        super(context, DBHelper.class);
    }
}
