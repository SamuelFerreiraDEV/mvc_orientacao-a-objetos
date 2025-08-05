package projetomvc.controllers;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.validators.interfaces.Validator;

import java.util.List;

public abstract class Controller<T> {
    protected final DAO<T> dao;
    protected final Validator<T> validator;

    public Controller(DAO<T> dao, Validator<T> validator) {
        this.dao = dao;
        this.validator = validator;
    }

    public abstract List<T> index();
    public abstract T show(int id);
    public abstract T newForm();
    public abstract T edit(int id);
    public abstract boolean create(T entity);
    public abstract boolean update(int id, T entity);
    public abstract boolean delete(int id);
}
