package projetomvc.controllers;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Book;
import projetomvc.validators.interfaces.Validator;

public class BookController extends BaseController<Book> {

    public BookController(DAO<Book> dao, Validator<Book> validator) {
        super(dao, validator);
    }
}
