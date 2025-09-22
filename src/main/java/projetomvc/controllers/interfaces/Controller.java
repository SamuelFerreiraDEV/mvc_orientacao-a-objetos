package projetomvc.controllers.interfaces;

import java.util.HashMap;
import java.util.List;

public interface Controller<T> {
	abstract List<T> index(HashMap<String, String> params);
    abstract T show(int id);
    abstract T newForm();
    abstract T edit(int id);
    abstract boolean create(T entity);
    abstract boolean update(int id, T entity);
    abstract boolean delete(int id);
}
