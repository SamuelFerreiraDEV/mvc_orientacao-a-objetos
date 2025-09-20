package projetomvc.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.services.interfaces.BookServiceInterface;
import projetomvc.validators.ValidationResult;
import projetomvc.validators.interfaces.Validator;

public class AuthorController extends Controller<Author> {
    private final BookServiceInterface<Book> bookService;

    public AuthorController(DAO<Author> dao, Validator<Author> validator, BookServiceInterface<Book> bookService) {
        super(dao, validator);
        this.bookService = bookService;
    }

    @Override
    public List<Author> index(HashMap<String, String> params) {
        try {
            HashMap<String, String> filteredParams = params.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isEmpty() && !entry.getValue().equals("-1"))
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    HashMap::new
                ));
            return !filteredParams.isEmpty() ? dao.find(filteredParams) : dao.findAll();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Author show(int id) {
        try {
            return dao.find(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Author newForm() {
        return new Author();
    }

    @Override
    public Author edit(int id) {
        try {
            return dao.find(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Author author) {
        ValidationResult authorValidation = validator.validate(author);

        if (!authorValidation.isValid()) {
            System.err.println(authorValidation.getErrorsAsString());
            return false;
        }

        try {
            return dao.save(author);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(int id, Author author) {
        ValidationResult authorValidation = validator.validate(author);

        if (!authorValidation.isValid()) {
            System.err.println(authorValidation.getErrorsAsString());
            return false;
        }

        try {
            return dao.update(id, author);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        try {
            boolean hasBooks = this.bookService.authorHasBooks(id);
            if (hasBooks) {
                System.err.println("Não é possível deletar um Autor que tenha livros.");
                return false;
            }
            return dao.delete(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
