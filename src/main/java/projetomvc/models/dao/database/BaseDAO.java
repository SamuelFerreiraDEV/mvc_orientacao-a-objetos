package projetomvc.models.dao.database;

import java.sql.Connection;

import projetomvc.models.dao.interfaces.IDAO;

public abstract class BaseDAO<T> implements IDAO<T> {
    protected Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }
}
