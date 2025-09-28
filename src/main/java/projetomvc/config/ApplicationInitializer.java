package projetomvc.config;

import java.sql.Connection;
import java.sql.SQLException;

import projetomvc.connections.SQLiteConnector;
import projetomvc.controllers.AuthorController;
import projetomvc.controllers.BookController;
import projetomvc.controllers.interfaces.Controller;
import projetomvc.models.dao.database.AuthorDAO;
import projetomvc.models.dao.database.BookDAO;
import projetomvc.models.dao.interfaces.DAO;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.services.BookService;
import projetomvc.services.interfaces.BookServiceInterface;
import projetomvc.validators.AuthorValidator;
import projetomvc.validators.BookValidator;
import projetomvc.validators.interfaces.Validator;
import projetomvc.views.AuthorsView;
import projetomvc.views.BooksView;
import projetomvc.views.MainView;
import projetomvc.views.interfaces.EntityView;
import projetomvc.views.interfaces.ViewNavigator;

public class ApplicationInitializer {
	public void initialize()  {
		try {
			SQLiteConnector sqlite = new SQLiteConnector("Library");
			Connection connection = sqlite.getConnection();

			DAO<Author> authorDB = new AuthorDAO(connection);
			DAO<Book> bookDB = new BookDAO(connection);

			Validator<Author> authorValidator = new AuthorValidator();
			Validator<Book> bookValidator = new BookValidator();

			BookServiceInterface<Book> bookService = new BookService(bookDB);

			Controller<Author> authorController = new AuthorController(authorDB, authorValidator, bookService);
			Controller<Book> bookController = new BookController(bookDB, bookValidator);

			ViewNavigator<Author, Book> mainView = new MainView<>();
			EntityView<Author> authorsView = new AuthorsView(mainView, authorController, bookController);
			EntityView<Book> booksView = new BooksView(mainView, authorController, bookController);

			mainView.setAuthorsView(authorsView);
			mainView.setBooksView(booksView);
			mainView.setVisible(true);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
