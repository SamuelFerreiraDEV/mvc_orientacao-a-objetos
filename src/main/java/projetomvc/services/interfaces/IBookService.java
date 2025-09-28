package projetomvc.services.interfaces;

import java.sql.SQLException;

public interface IBookService<T> {
	boolean authorHasBooks(int authorId) throws SQLException;
}
