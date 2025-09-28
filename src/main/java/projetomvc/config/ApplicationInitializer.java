package projetomvc.config;

import java.sql.Connection;
import java.sql.SQLException;

import projetomvc.connections.SQLiteConnector;
import projetomvc.controllers.AuthorController;
import projetomvc.controllers.BookController;
import projetomvc.controllers.interfaces.IController;
import projetomvc.models.dao.database.AuthorDAO;
import projetomvc.models.dao.database.BookDAO;
import projetomvc.models.dao.interfaces.IDAO;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.services.BookService;
import projetomvc.services.interfaces.IBookService;
import projetomvc.validators.AuthorValidator;
import projetomvc.validators.BookValidator;
import projetomvc.validators.interfaces.IValidator;
import projetomvc.views.AuthorsView;
import projetomvc.views.BooksView;
import projetomvc.views.MainView;
import projetomvc.views.interfaces.IEntityView;
import projetomvc.views.interfaces.IViewNavigator;

public class ApplicationInitializer {
	public void initialize()  {
		try {
			SQLiteConnector sqlite = new SQLiteConnector("Library");
			Connection connection = sqlite.getConnection();

			IDAO<Author> authorDB = new AuthorDAO(connection);
			IDAO<Book> bookDB = new BookDAO(connection);

			IValidator<Author> authorValidator = new AuthorValidator();
			IValidator<Book> bookValidator = new BookValidator();

			IBookService<Book> bookService = new BookService(bookDB);

			IController<Author> authorController = new AuthorController(authorDB, authorValidator, bookService);
			IController<Book> bookController = new BookController(bookDB, bookValidator);

			IViewNavigator<Author, Book> mainView = new MainView<>();
			IEntityView<Author> authorsView = new AuthorsView(mainView, authorController, bookController);
			IEntityView<Book> booksView = new BooksView(mainView, authorController, bookController);

			mainView.setAuthorsView(authorsView);
			mainView.setBooksView(booksView);
			mainView.setVisible(true);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
