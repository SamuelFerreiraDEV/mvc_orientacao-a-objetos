package projetomvc.validators;

import projetomvc.models.entities.Book;
import projetomvc.validators.interfaces.Validator;

public class BookValidator implements Validator<Book> {
    
    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book == null) {
            result.addError("Book cannot be null");
            return result;
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            result.addError("Title is required");
        }
        
        if (book.getTitle() != null && book.getTitle().length() > 255) {
            result.addError("Title cannot exceed 255 characters");
        }
        
        if (book.getPublishedYear() == null) {
            result.addError("Published year is required");
        }
        
        return result;
    }
}
