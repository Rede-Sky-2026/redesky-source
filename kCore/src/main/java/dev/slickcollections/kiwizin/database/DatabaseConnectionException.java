package dev.slickcollections.kiwizin.database;

public class DatabaseConnectionException extends RuntimeException {

  public DatabaseConnectionException(String message, Throwable cause) {
    super(message, cause);
  }
}
