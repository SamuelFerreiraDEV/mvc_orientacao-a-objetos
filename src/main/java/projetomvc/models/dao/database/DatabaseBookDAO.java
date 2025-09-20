package projetomvc.models.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projetomvc.models.entities.Book;

public class DatabaseBookDAO extends databaseDAO<Book> {

    public DatabaseBookDAO(Connection connection) {
      	super(connection);
    }

    @Override
	public boolean save(Book book) throws SQLException {
		String sql = "INSERT INTO books (title, author_id, published_year) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, book.getTitle());
			stmt.setInt(2, book.getAuthorId());
			stmt.setInt(3, book.getPublishedYear());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw e;
		}
    }

	@Override
	public boolean update(int bookId, Book book) throws SQLException {
		String sql = "UPDATE books SET title = ?, author_id = ?, published_year = ? WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, book.getTitle());
			stmt.setInt(2, book.getAuthorId());
			stmt.setInt(3, book.getPublishedYear());
			stmt.setInt(4, bookId);

			int rowsAffected =  stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	@Override
	public boolean delete(int bookId) throws SQLException {
		String sql = "DELETE FROM books WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
	
			int rowsAffected =  stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public Book find(int bookId) throws SQLException {
		String sql = "SELECT * FROM books WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new Book(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("author_id"),
					rs.getInt("published_year")
				);
			}
		} catch (SQLException e) {
			throw e;
		}
		return null;
	}

	@Override
	public List<Book> find(HashMap<String, String> params) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");

		for(String key : params.keySet()) {
			switch (key) {
				case "title":
					sql.append(" AND title LIKE ?");
					break;
				case "authorId":
					sql.append(" AND author_id = ?");
					break;
				case "publishedYear":
					sql.append(" AND published_year = ?");
				default:
					return null;
			}
		}

		try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
			for (int i = 1; i <= params.size(); i++) {
				stmt.setObject(i, params.values().toArray()[i-1]);
			}
			ResultSet rs = stmt.executeQuery();

			List<Book> books = new ArrayList<>();
			while(rs.next()) {
				books.add(new Book(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("author_id"),
					rs.getInt("published_year")
				));
			}

			return books;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public List<Book> findAll() throws SQLException {
		String sql = "SELECT * FROM books;";
		
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			List<Book> books = new ArrayList<>();
			while (rs.next()) {
				books.add( new Book(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("author_id"),
					rs.getInt("published_year")
				));
			}
			
			return books;
		} catch (SQLException e) {
			throw e;
		}
	}
}
