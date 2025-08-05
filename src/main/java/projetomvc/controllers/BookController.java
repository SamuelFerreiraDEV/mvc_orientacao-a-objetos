package projetomvc.controllers;

import java.sql.SQLException;
import java.util.List;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Book;
import projetomvc.validators.ValidationResult;
import projetomvc.validators.interfaces.Validator;

public class BookController extends Controller<Book> {

    public BookController(DAO<Book> dao, Validator<Book> validator) {
        super(dao, validator);
    }

    @Override
    public List<Book> index() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Book show(int id) {
        try {
            return dao.find(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Book newForm() {
        return new Book();
    }

    @Override
    public Book edit(int id) {
        try {
            return dao.find(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Book book) {
        ValidationResult bookValidation = validator.validate(book);

        if (bookValidation.isValid()) {
            System.err.println(bookValidation.getErrorsAsString());
            return false;
        }

        try {
            return dao.save(book);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(int id, Book book) {
        ValidationResult bookValidation = validator.validate(book);

        if (bookValidation.isValid()) {
            System.err.println(bookValidation.getErrorsAsString());
            return false;
        }

        try {
            return dao.update(id, book);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        try {
            return dao.delete(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
