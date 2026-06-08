package dev.slickcollections.kiwizin.database;

import dev.slickcollections.kiwizin.KCoreSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class PostgreSQLDatabase extends AbstractSqlDatabase {

  private final String jdbcUrl;
  private final String username;
  private final String password;
  private final String databaseName;

  private Connection connection;

  public PostgreSQLDatabase(String host, String port, String dbname, String username, String password, String sslMode) {
    super();
    this.databaseName = dbname;
    this.jdbcUrl = buildJdbcUrl(host, port, dbname, sslMode);
    this.username = username;
    this.password = password;
    this.openConnection();
    this.initializeSchema();
  }

  static String buildJdbcUrl(String host, String port, String dbname, String sslMode) {
    String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
    if (sslMode != null && !sslMode.trim().isEmpty()) {
      url += "?sslmode=" + sslMode.trim().toLowerCase();
    }
    return url;
  }

  @Override
  protected void openConnection() {
    try {
      boolean reconnected = this.connection != null;
      Class.forName("org.postgresql.Driver");
      this.connection = DriverManager.getConnection(jdbcUrl, username, password);
      this.prepareSchema();
      if (reconnected) {
        LOGGER.info("Reconectado ao PostgreSQL (" + jdbcUrl + ", schema=" + SqlHelper.schema() + ").");
        return;
      }

      LOGGER.info("Conectado ao PostgreSQL (" + jdbcUrl + ", schema=" + SqlHelper.schema() + ").");
    } catch (DatabaseConnectionException ex) {
      throw ex;
    } catch (Exception ex) {
      String message = "Nao foi possivel se conectar ao PostgreSQL em " + jdbcUrl
          + " com o usuario '" + username + "'. Verifique host, porta, banco, senha, SSL (sslmode="
          + KCoreSettings.Database.SSL_MODE + ") e firewall.";
      LOGGER.log(Level.SEVERE, message, ex);
      throw new DatabaseConnectionException(message, ex);
    }
  }

  private void prepareSchema() {
    String schema = SqlHelper.schema();
    try {
      if (!"public".equals(schema) && !schemaExists(schema)) {
        if (!KCoreSettings.Database.AUTO_CREATE_SCHEMA) {
          throw schemaSetupRequired(schema, null);
        }
        try (Statement statement = this.connection.createStatement()) {
          statement.execute("CREATE SCHEMA IF NOT EXISTS \"" + escapeIdent(schema) + "\"");
        } catch (SQLException ex) {
          throw schemaSetupRequired(schema, ex);
        }
      }

      try (Statement statement = this.connection.createStatement()) {
        statement.execute("SET search_path TO \"" + escapeIdent(schema) + "\"");
      }
    } catch (DatabaseConnectionException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
      throw ex;
    } catch (SQLException ex) {
      String message = "Nao foi possivel usar o schema PostgreSQL '" + schema + "'.";
      LOGGER.log(Level.SEVERE, message, ex);
      throw new DatabaseConnectionException(message, ex);
    }
  }

  private boolean schemaExists(String schema) throws SQLException {
    try (PreparedStatement ps = this.connection.prepareStatement(
        "SELECT 1 FROM information_schema.schemata WHERE schema_name = ?")) {
      ps.setString(1, schema);
      try (ResultSet rs = ps.executeQuery()) {
        return rs.next();
      }
    }
  }

  private DatabaseConnectionException schemaSetupRequired(String schema, SQLException cause) {
    String help = "Configure o PostgreSQL uma vez como superusuario (psql -U postgres):\n"
        + "  CREATE USER minecraft WITH PASSWORD 'sua_senha';\n"
        + "  CREATE DATABASE minecraft OWNER minecraft;\n"
        + "  \\c minecraft\n"
        + "  CREATE SCHEMA IF NOT EXISTS " + schema + " AUTHORIZATION minecraft;\n"
        + "  GRANT ALL ON SCHEMA " + schema + " TO minecraft;\n"
        + "Depois ajuste KCoreSettings.Database.NAME = \"minecraft\" e USER = \"minecraft\".\n"
        + "Nao use o banco 'postgres' com usuario limitado. Banco atual: '" + databaseName + "'.";
    return new DatabaseConnectionException(help, cause);
  }

  private static String escapeIdent(String ident) {
    return ident.replace("\"", "\"\"");
  }

  @Override
  protected void closeConnection() {
    if (isConnected()) {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.log(Level.WARNING, "Nao foi possivel fechar a conexao com o PostgreSQL: ", e);
      }
    }
  }

  @Override
  protected Connection getConnection() throws SQLException {
    if (!isConnected()) {
      try {
        this.openConnection();
      } catch (DatabaseConnectionException ex) {
        throw new SQLException(ex.getMessage(), ex);
      }
    }
    return this.connection;
  }

  @Override
  protected boolean isConnected() {
    try {
      return connection != null && !connection.isClosed() && connection.isValid(5);
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Nao foi possivel verificar a conexao com o PostgreSQL: ", ex);
      return false;
    }
  }
}
