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
    String autorTableSQL = "CREATE TABLE IF NOT EXISTS autor (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome TEXT NOT NULL," +
                            "cidade_natal TEXT NOT NULL," +
                            "data_nascimento DATETIME NOT NULL" +
                            ");";
    
    String livroTableSQL = "CREATE TABLE IF NOT EXISTS livro (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "titulo TEXT NOT NULL," +
                            "autor_id INTEGER NOT NULL REFERENCES autor(id)," +
                            "ano INTEGER NOT NULL" +
                            ");";

    try(Statement stmt = connection.createStatement()) {
      stmt.execute(autorTableSQL);
      stmt.execute(livroTableSQL);
    } catch (SQLException e) {
      throw e;
    }
  }
}
