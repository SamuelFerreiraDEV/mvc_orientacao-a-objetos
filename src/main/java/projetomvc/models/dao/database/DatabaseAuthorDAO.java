package projetomvc.models.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projetomvc.models.entities.Author;

public class DatabaseAuthorDAO extends databaseDAO<Author>  {

    public DatabaseAuthorDAO(Connection connection) {
      	super(connection);
    }

    @Override
	public boolean save(Author author) throws SQLException {
		String sql = "INSERT INTO authors (name, hometown, birth_date) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getHometown());
			stmt.setInt(3, author.getBirthDate());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw e;
		}
    }

	@Override
	public boolean update(int authorId, Author author) throws SQLException {
		String sql = "UPDATE authors SET name = ?, hometown = ?, birth_date = ? WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getHometown());
			stmt.setInt(3, author.getBirthDate());
			stmt.setInt(4, authorId);

			int rowsAffected =  stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	@Override
	public boolean delete(int authorId) throws SQLException {
		String sql = "DELETE FROM authors WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, authorId);
	
			int rowsAffected =  stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public Author find(int authorId) throws SQLException {
		String sql = "SELECT * FROM authors WHERE id = ? LIMIT 1";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, authorId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new Author(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("hometown"),
					rs.getInt("birth_date")
				);
			}
		} catch (SQLException e) {
			throw e;
		}
		return null;
	}

	@Override
	public List<Author> find(HashMap<String, String> params) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM authors WHERE 1=1");
		for(String key : params.keySet()) {
			switch (key) {
				case "name":
					sql.append(" AND name LIKE ?");
					break;
				case "hometown":
					sql.append(" AND hometown = ?");
					break;
				case "birthDate":
					sql.append(" AND birth_date = ?");
				default:
					return null;
			}
		}
		try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
			for (int i = 1; i <= params.size(); i++) {
				stmt.setObject(i, params.values().toArray()[i-1]);
			}
			ResultSet rs = stmt.executeQuery();

			List<Author> authors = new ArrayList<>();
			while(rs.next()) {
				authors.add(new Author(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("hometown"),
					rs.getInt("birth_date")
				));
			}

			return authors;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public List<Author> findAll() throws SQLException {
		String sql = "SELECT * FROM authors;";
		
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			List<Author> authors = new ArrayList<>();
			while (rs.next()) {
				authors.add( new Author(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("hometown"),
					rs.getInt("birth_date")
				));
			}
			
			return authors;
		} catch (SQLException e) {
			throw e;
		}
	}
}
