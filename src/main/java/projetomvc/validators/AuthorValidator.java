package projetomvc.validators;

import projetomvc.models.entities.Author;
import projetomvc.validators.interfaces.Validator;

public class AuthorValidator implements Validator<Author> {
    
    @Override
    public ValidationResult validate(Author author) {
        ValidationResult result = new ValidationResult();

        if (author == null) {
            result.addError("Author cannot be null");
            return result;
        }
        
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            result.addError("Name is required");
        }
        
        if (author.getName() != null && author.getName().length() > 255) {
            result.addError("Name cannot exceed 255 characters");
        }

        if (author.getHometown() != null && author.getHometown().length() > 255) {
            result.addError("HogetHometown cannot exceed 255 characters");
        }
        
        if (author.getBirthDate() == null) {
            result.addError("Birth date is required");
        }
        
        return result;
    }
}
