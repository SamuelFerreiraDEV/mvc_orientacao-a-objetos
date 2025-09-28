package projetomvc.controllers;

import projetomvc.models.dao.interfaces.IDAO;
import projetomvc.models.entities.Book;
import projetomvc.validators.interfaces.IValidator;

public class BookController extends BaseController<Book> {

    public BookController(IDAO<Book> dao, IValidator<Book> validator) {
        super(dao, validator);
    }
}
