package com.formakidov.sparecalendar.db.repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T, Id> {
    void save(T entity) throws SQLException;

    void saveBatch(List<T> entities) throws Exception;

    List<T> queryAll() throws SQLException;

    T findById(Id id) throws SQLException;

    void delete(T entity) throws SQLException;
}
