package projetomvc.validators.interfaces;

import projetomvc.validators.ValidationResult;

public interface IValidator<T> {
    ValidationResult validate(T entity);
}
