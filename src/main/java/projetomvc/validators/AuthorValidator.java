package projetomvc.validators;

import projetomvc.models.entities.Author;
public class AuthorValidator extends BaseValidator<Author> {
    
    @Override
    public ValidationResult validate(Author author) {
        if (author == null) {
            super.result.addError("Author cannot be null");
            return result;
        }
        
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            super.result.addError("Name is required");
        }
        
        if (author.getName() != null && author.getName().length() > 255) {
            super.result.addError("Name cannot exceed 255 characters");
        }

        if (author.getHometown() != null && author.getHometown().length() > 255) {
            super.result.addError("HogetHometown cannot exceed 255 characters");
        }
        
        if (author.getBirthDate() == null) {
            super.result.addError("Birth date is required");
        }
        
        return result;
    }
}
