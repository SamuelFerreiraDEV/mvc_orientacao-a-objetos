package projetomvc.controllers;

import projetomvc.controllers.interfaces.Controller;
import projetomvc.models.dao.interfaces.DAO;
import projetomvc.validators.interfaces.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseController<T> implements Controller<T> {
    protected final DAO<T> dao;
    protected final Validator<T> validator;

    public BaseController(DAO<T> dao, Validator<T> validator) {
        this.dao = dao;
        this.validator = validator;
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
