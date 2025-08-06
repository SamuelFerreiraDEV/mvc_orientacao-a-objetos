package projetomvc.models.dao.database;

import java.sql.Connection;

import projetomvc.models.dao.interfaces.DAO;

public abstract class databaseDAO<T> implements DAO<T> {
    protected Connection connection;

    public databaseDAO(Connection connection) {
        this.connection = connection;
    }
}
