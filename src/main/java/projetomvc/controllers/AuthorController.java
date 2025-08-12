package projetomvc.controllers;

import java.sql.SQLException;
import java.util.List;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Author;
import projetomvc.validators.ValidationResult;
import projetomvc.validators.interfaces.Validator;

public class AuthorController extends Controller<Author> {

    public AuthorController(DAO<Author> dao, Validator<Author> validator) {
        super(dao, validator);
    }

    @Override
    public List<Author> index() {
        try {
            return dao.findAll();
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
    public Author show(String name) {
        try {
            return dao.find(name);
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

        if (authorValidation.isValid()) {
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
            return dao.delete(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
