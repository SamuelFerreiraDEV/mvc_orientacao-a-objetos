package projetomvc.controllers;

import projetomvc.controllers.interfaces.Controller;
import projetomvc.models.dao.interfaces.DAO;
import projetomvc.validators.ValidationResult;
import projetomvc.validators.interfaces.Validator;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseController<T> implements Controller<T> {
    protected final DAO<T> dao;
    protected final Validator<T> validator;

    public BaseController(DAO<T> dao, Validator<T> validator) {
        this.dao = dao;
        this.validator = validator;
    }

    public List<T> index(HashMap<String, String> params) {
        try {
            HashMap<String, String> filteredParams = this.filterInvalidParams(params);
            return !filteredParams.isEmpty() ? this.dao.find(filteredParams) : this.dao.findAll();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public T show(int id) {
        try {
            return this.dao.find(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public T edit(int id) {
        try {
            return this.dao.find(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean create(T entity) {
        ValidationResult entityValidation = validator.validate(entity);

        if (!entityValidation.isValid()) {
            System.err.println(entityValidation.getErrorsAsString());
            return false;
        }

        try {
            return this.dao.save(entity);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean update(int id, T entity) {
        ValidationResult entityValidation = validator.validate(entity);

        if (!entityValidation.isValid()) {
            System.err.println(entityValidation.getErrorsAsString());
            return false;
        }

        try {
            return dao.update(id, entity);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            return this.dao.delete(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

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
