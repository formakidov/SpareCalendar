package com.formakidov.sparecalendar.db.repository;

import android.content.Context;

import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.model.Consumable;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class ConsumablesRepository extends BaseRepository<Consumable> {
    public ConsumablesRepository(Context context) {
        super(context);
    }

    public List<Consumable> findStaleConsumables() throws SQLException {
        QueryBuilder<Consumable, Long> queryBuilder = getQueryBuilder();
        queryBuilder.orderBy(Consumable.FIELD_DISTANCE, true)
                .where().le(Consumable.FIELD_DISTANCE, Constants.CONSUMABLES_STALE_THRESHOLD);
        return queryBuilder.query();
    }

    public boolean hasStaleConsumables() throws SQLException {
        QueryBuilder<Consumable, Long> queryBuilder = getQueryBuilder();
        queryBuilder.orderBy(Consumable.FIELD_DISTANCE, true)
                .where().le(Consumable.FIELD_DISTANCE, Constants.CONSUMABLES_STALE_THRESHOLD);
        Consumable consumable = queryBuilder.queryForFirst();
        return consumable != null;
    }
}
