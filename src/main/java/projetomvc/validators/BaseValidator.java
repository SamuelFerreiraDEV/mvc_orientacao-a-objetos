package projetomvc.validators;

import projetomvc.validators.interfaces.IValidator;

public abstract class BaseValidator<T> implements IValidator<T> {
	protected final ValidationResult result = new ValidationResult();
}
