package projetomvc.validators.interfaces;

import projetomvc.validators.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T entity);
}
