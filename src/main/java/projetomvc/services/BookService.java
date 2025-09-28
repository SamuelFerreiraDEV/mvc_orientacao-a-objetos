package projetomvc.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import projetomvc.models.dao.interfaces.IDAO;
import projetomvc.models.entities.Book;
import projetomvc.services.interfaces.IBookService;

public class BookService extends BaseService<Book> implements IBookService<Book> {

	public BookService(IDAO<Book> bookDao) {
		super(bookDao);
	}

	public boolean authorHasBooks(int authorId) throws SQLException {
		HashMap<String, String> params = new HashMap<>();
		params.put("authorId", String.valueOf(authorId));

		try {
			List<Book> books = super.dao.find(params);
			return books != null && !books.isEmpty();
		} catch (SQLException e) {
			throw e;
		}
	}
}
