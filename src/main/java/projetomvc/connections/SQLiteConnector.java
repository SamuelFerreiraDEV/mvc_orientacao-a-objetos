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
    String autorTableSQL = "CREATE TABLE IF NOT EXISTS author (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "hometown TEXT NOT NULL," +
                            "birth_date DATETIME NOT NULL" +
                            ");";
    
    String livroTableSQL = "CREATE TABLE IF NOT EXISTS book (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "title TEXT NOT NULL," +
                            "author_id INTEGER NOT NULL REFERENCES autor(id)," +
                            "published_year INTEGER NOT NULL" +
                            ");";

    try(Statement stmt = connection.createStatement()) {
      stmt.execute(autorTableSQL);
      stmt.execute(livroTableSQL);
    } catch (SQLException e) {
      throw e;
    }
  }
}
