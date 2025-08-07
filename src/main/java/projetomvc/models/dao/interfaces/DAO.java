package projetomvc.models.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    boolean save(T entity) throws SQLException;
    boolean update(int id, T entity) throws SQLException;
    boolean delete(int id) throws SQLException;
    T find(int id) throws SQLException;
    T find(String string) throws SQLException;
    List<T> findAll() throws SQLException;
}
