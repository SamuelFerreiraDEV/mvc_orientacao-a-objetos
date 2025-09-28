package projetomvc.controllers;

import java.sql.SQLException;

import projetomvc.models.dao.interfaces.IDAO;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.services.interfaces.IBookService;
import projetomvc.validators.interfaces.IValidator;

public class AuthorController extends BaseController<Author> {
    private final IBookService<Book> bookService;

    public AuthorController(IDAO<Author> dao, IValidator<Author> validator, IBookService<Book> bookService) {
        super(dao, validator);
        this.bookService = bookService;
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
