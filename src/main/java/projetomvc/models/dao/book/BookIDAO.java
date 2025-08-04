package projetomvc.models.dao.book;

import java.sql.SQLException;
import java.util.List;

import projetomvc.models.entities.Book;

public interface BookIDAO {
    boolean save(Book book) throws SQLException;
    boolean update(int bookId, Book book) throws SQLException;
    boolean delete(int bookId) throws SQLException;
    Book find(int bookId) throws SQLException;
    List<Book> findAll() throws SQLException;
}
