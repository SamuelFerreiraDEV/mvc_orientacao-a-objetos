package projetomvc.validators;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
    }

    public void addError(String error) {
        this.valid = false;
        errors.add(error);
    }

    public boolean valid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorsAsString() {
        return String.join(", ", errors);
    }
}
