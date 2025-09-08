package projetomvc;

import java.sql.Connection;
import java.sql.SQLException;

import projetomvc.connections.SQLiteConnector;
import projetomvc.controllers.AuthorController;
import projetomvc.controllers.BookController;
import projetomvc.controllers.Controller;
import projetomvc.models.dao.database.DatabaseAuthorDAO;
import projetomvc.models.dao.database.DatabaseBookDAO;
import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.validators.AuthorValidator;
import projetomvc.validators.BookValidator;
import projetomvc.validators.interfaces.Validator;
import projetomvc.views.AuthorsView;
import projetomvc.views.BooksView;
import projetomvc.views.MainView;

public class Library {
    public static void main(String[] args) {
        try {
            SQLiteConnector sqlite = new SQLiteConnector("Library");
            Connection connection = sqlite.getConnection();

            DAO<Author> authorDB = new DatabaseAuthorDAO(connection);
            DAO<Book> bookDB = new DatabaseBookDAO(connection);
            Validator<Author> authorValidator = new AuthorValidator();
            Validator<Book> bookValidator = new BookValidator();
            Controller<Author> authorController = new AuthorController(authorDB, authorValidator);
            Controller<Book> bookController = new BookController(bookDB, bookValidator);
    
            MainView mainView = new MainView();
            AuthorsView authorsView = new AuthorsView(mainView, authorController, bookController);
            BooksView booksView = new BooksView(mainView, authorController, bookController);
            mainView.setAuthorsView(authorsView);
            mainView.setBooksView(booksView);
            mainView.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Ainda não tá pronto. Calma lá, Zé Rui...");
        }
    }
}