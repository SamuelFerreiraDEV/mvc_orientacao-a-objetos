package projetomvc.validators;

import projetomvc.validators.interfaces.Validator;

public abstract class BaseValidator<T> implements Validator<T> {
	protected final ValidationResult result = new ValidationResult();
}
