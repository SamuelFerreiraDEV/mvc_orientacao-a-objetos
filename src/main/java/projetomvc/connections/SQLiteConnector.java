package projetomvc.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnector {
  private Connection connection;

  public SQLiteConnector(String dbName) throws SQLException {
    String url = "jdbc:sqlite:" + dbName;
    this.connection = DriverManager.getConnection(url);

    createDefaultTables();
  }

  public Connection getConnection() {
    return this.connection;
  }

  private void createDefaultTables() throws SQLException {
    String authorsTableSQL = "CREATE TABLE IF NOT EXISTS authors (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "hometown TEXT NOT NULL," +
                            "birth_date DATETIME NOT NULL" +
                            ");";
    
    String booksTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "title TEXT NOT NULL," +
                            "author_id INTEGER NOT NULL REFERENCES authors(id)," +
                            "published_year INTEGER NOT NULL" +
                            ");";

    try(Statement stmt = connection.createStatement()) {
      stmt.execute(authorsTableSQL);
      stmt.execute(booksTableSQL);
    } catch (SQLException e) {
      throw e;
    }
  }
}
