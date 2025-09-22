package projetomvc.controllers;

import java.sql.SQLException;

import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.services.interfaces.BookServiceInterface;
import projetomvc.validators.interfaces.Validator;

public class AuthorController extends BaseController<Author> {
    private final BookServiceInterface<Book> bookService;

    public AuthorController(DAO<Author> dao, Validator<Author> validator, BookServiceInterface<Book> bookService) {
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
