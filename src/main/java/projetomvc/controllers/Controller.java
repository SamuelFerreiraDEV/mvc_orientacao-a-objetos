package projetomvc.controllers;

import projetomvc.models.dao.interfaces.DAO;

import java.util.List;

public abstract class Controller<T> {
    protected final DAO<T> dao;

    public Controller(DAO<T> dao) {
        this.dao = dao;
    }

    public abstract List<T> index();
    public abstract T show(int id);
    public abstract T newForm();
    public abstract T edit();
    public abstract boolean create(T entity);
    public abstract boolean update(int id, T entity);
    public abstract boolean delete(int id);
}
