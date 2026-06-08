package dev.slickcollections.kiwizin.database;

import dev.slickcollections.kiwizin.KCoreSettings;

public final class SqlHelper {

  private SqlHelper() {
  }

  public static String translate(String sql) {
    return sql
        .replace('`', '"')
        .replace(" LONG", " BIGINT")
        .replace(" DOUBLE", " DOUBLE PRECISION")
        .replace("ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin", "")
        .replace("ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_bin", "")
        .trim();
  }

  public static String schema() {
    String schema = KCoreSettings.Database.SCHEMA;
    if (schema == null || schema.trim().isEmpty()) {
      return "public";
    }
    return schema.trim().toLowerCase();
  }

  public static boolean tableExists(AbstractSqlDatabase database, String table) {
    return database.query(
        "SELECT table_name FROM information_schema.tables WHERE table_schema = ? AND LOWER(table_name) = LOWER(?)",
        schema(),
        table
    ) != null;
  }

  public static boolean columnExists(AbstractSqlDatabase database, String table, String column) {
    return database.query(
        "SELECT column_name FROM information_schema.columns WHERE table_schema = ? AND LOWER(table_name) = LOWER(?) AND LOWER(column_name) = LOWER(?)",
        schema(),
        table,
        column
    ) != null;
  }

  public static void addColumnIfMissing(AbstractSqlDatabase database, String table, String column, String definition) {
    if (!columnExists(database, table, column)) {
      database.update("ALTER TABLE \"" + table + "\" ADD COLUMN \"" + column + "\" " + definition);
    }
  }

  public static void createIndexIfMissing(AbstractSqlDatabase database, String table, String indexName, String column) {
    if (!tableExists(database, table)) {
      return;
    }
    database.update("CREATE INDEX IF NOT EXISTS " + indexName + " ON \"" + table + "\" (\"" + column + "\" DESC)");
  }
}
