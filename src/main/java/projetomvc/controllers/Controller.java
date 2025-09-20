package projetomvc.controllers;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.validators.interfaces.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Controller<T> {
    protected final DAO<T> dao;
    protected final Validator<T> validator;

    public Controller(DAO<T> dao, Validator<T> validator) {
        this.dao = dao;
        this.validator = validator;
    }

    public abstract List<T> index(HashMap<String, String> params);
    public abstract T show(int id);
    public abstract T newForm();
    public abstract T edit(int id);
    public abstract boolean create(T entity);
    public abstract boolean update(int id, T entity);
    public abstract boolean delete(int id);

    protected HashMap<String, String> filterInvalidParams(HashMap<String, String> params) {
        HashMap<String, String> filteredParams = params.entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty() && !entry.getValue().equals("-1"))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                HashMap::new
            ));
        return filteredParams;
    }
}
