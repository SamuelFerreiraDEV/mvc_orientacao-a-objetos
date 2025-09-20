package projetomvc.services.interfaces;

import java.sql.SQLException;

public interface BookServiceInterface<T> {
	boolean authorHasBooks(int authorId) throws SQLException;
}
