package projetomvc.models.dao.database;

import java.sql.Connection;

import projetomvc.models.dao.interfaces.DAO;

public abstract class BaseDAO<T> implements DAO<T> {
    protected Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }
}
