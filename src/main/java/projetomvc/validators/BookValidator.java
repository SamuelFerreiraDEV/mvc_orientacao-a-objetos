package projetomvc.validators;

import projetomvc.models.entities.Book;
public class BookValidator extends BaseValidator<Book> {
    
    @Override
    public ValidationResult validate(Book book) {
        if (book == null) {
            super.result.addError("Book cannot be null");
            return result;
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            super.result.addError("Title is required");
        }
        
        if (book.getTitle() != null && book.getTitle().length() > 255) {
            super.result.addError("Title cannot exceed 255 characters");
        }
        
        if (book.getAuthorId() == null) {
            super.result.addError("Author ID is required");
        }

        if (book.getAuthorId() != null && book.getAuthorId() < 0) {
            super.result.addError("Author ID invalid.");
        }
        
        if (book.getPublishedYear() != null && book.getPublishedYear() < 0) {
            super.result.addError("Published year is invalid");
        }
        
        if (book.getPublishedYear() != null && book.getPublishedYear() < 0) {
            super.result.addError("Published year is invalid");
        }
        
        return result;
    }
}
