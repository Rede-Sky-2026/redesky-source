package dev.slickcollections.kiwizin.database;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.Manager;
import dev.slickcollections.kiwizin.booster.NetworkBooster;
import dev.slickcollections.kiwizin.database.cache.RoleCache;
import dev.slickcollections.kiwizin.database.data.DataContainer;
import dev.slickcollections.kiwizin.database.data.DataTable;
import dev.slickcollections.kiwizin.database.exception.ProfileLoadException;
import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class AbstractSqlDatabase extends Database {

  protected final ExecutorService executor = Executors.newCachedThreadPool();

  protected AbstractSqlDatabase() {
  }

  protected void initializeSchema() {
    this.updateRequired(
        "CREATE TABLE IF NOT EXISTS `kCoreNetworkBooster` (`id` VARCHAR(32), `booster` TEXT, `multiplier` DOUBLE, `expires` BIGINT, PRIMARY KEY(`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;");

    DataTable.listTables().forEach(table -> {
      this.updateRequired(table.getInfo().create());
      SqlHelper.createIndexIfMissing(this, table.getInfo().name(), table.getInfo().name().toLowerCase() + "_name_idx", "name");
      table.init(this);
    });
  }

  protected abstract void openConnection();

  protected abstract void closeConnection();

  protected abstract Connection getConnection() throws SQLException;

  protected abstract boolean isConnected();

  protected String sql(String query) {
    return SqlHelper.translate(query);
  }

  @Override
  public void setupBoosters() {
    if (!Manager.BUNGEE) {
      for (String mg : Core.minigames) {
        if (query("SELECT * FROM `kCoreNetworkBooster` WHERE `id` = ?", mg) == null) {
          execute("INSERT INTO `kCoreNetworkBooster` VALUES (?, ?, ?, ?)", mg, "Kiwizin", 1.0, 0L);
        }
      }
    }
  }

  @Override
  public void setBooster(String minigame, String booster, double multiplier, long expires) {
    execute("UPDATE `kCoreNetworkBooster` SET `booster` = ?, `multiplier` = ?, `expires` = ? WHERE `id` = ?", booster, multiplier, expires, minigame);
  }

  @Override
  public NetworkBooster getBooster(String minigame) {
    try (CachedRowSet rs = query("SELECT * FROM `kCoreNetworkBooster` WHERE `id` = ?", minigame)) {
      if (rs != null) {
        String booster = rs.getString("booster");
        double multiplier = rs.getDouble("multiplier");
        long expires = rs.getLong("expires");
        if (expires > System.currentTimeMillis()) {
          return new NetworkBooster(booster, multiplier, expires);
        }
      }
    } catch (SQLException ignored) {
    }

    return null;
  }

  @Override
  public String getRankAndName(String player) {
    try (CachedRowSet rs = query("SELECT `name`, `role` FROM `kCoreProfile` WHERE LOWER(`name`) = ?", player.toLowerCase())) {
      if (rs != null) {
        String result = rs.getString("role") + " : " + rs.getString("name");
        RoleCache.setCache(player, rs.getString("role"), rs.getString("name"));
        return result;
      }
    } catch (SQLException ignored) {
    }
    return null;
  }

  @Override
  public boolean getPreference(String player, String id, boolean def) {
    try (CachedRowSet rs = query("SELECT `preferences` FROM `kCoreProfile` WHERE LOWER(`name`) = ?", player.toLowerCase())) {
      if (rs != null) {
        Object value = ((JSONObject) new JSONParser().parse(rs.getString("preferences"))).get(id);
        return value != null && value.equals(0L);
      }
    } catch (Exception ex) {
      LOGGER.log(Level.WARNING, "Nao foi possivel carregar preferencia de " + player, ex);
    }

    return def;
  }

  @Override
  public List<String[]> getLeaderBoard(String table, String... columns) {
    List<String[]> result = new ArrayList<>();
    StringBuilder orderBy = new StringBuilder();
    StringBuilder select = new StringBuilder();

    for (String column : columns) {
      orderBy.append("COALESCE(\"").append(column).append("\", 0) + ");
      select.append("\"").append(column).append("\", ");
    }

    try (CachedRowSet rs = query("SELECT " + select + "\"name\" FROM \"" + table + "\" ORDER BY " + orderBy + " 0 DESC LIMIT 10")) {
      if (rs != null) {
        rs.beforeFirst();
        while (rs.next()) {
          long count = 0;
          for (String column : columns) {
            count += rs.getLong(column);
          }
          result.add(new String[]{Role.getColored(rs.getString("name"), true), StringUtils.formatNumber(count)});
        }
      }
    } catch (SQLException ignore) {
    }

    return result;
  }

  @Override
  public void close() {
    this.executor.shutdownNow().forEach(Runnable::run);
    this.closeConnection();
  }

  @Override
  public Map<String, Map<String, DataContainer>> load(String name) throws ProfileLoadException {
    Map<String, Map<String, DataContainer>> tableMap = new HashMap<>();
    for (DataTable table : DataTable.listTables()) {
      Map<String, DataContainer> containerMap = new LinkedHashMap<>();
      tableMap.put(table.getInfo().name(), containerMap);

      try (CachedRowSet rs = this.query(table.getInfo().select(), name.toLowerCase())) {
        if (rs != null) {
          for (int column = 2; column <= rs.getMetaData().getColumnCount(); column++) {
            containerMap.put(rs.getMetaData().getColumnName(column), new DataContainer(rs.getObject(column)));
          }
          continue;
        }
      } catch (SQLException ex) {
        throw new ProfileLoadException(ex.getMessage());
      }

      containerMap = table.getDefaultValues();
      tableMap.put(table.getInfo().name(), containerMap);
      List<Object> values = new ArrayList<>();
      values.add(name);
      values.addAll(containerMap.values().stream().map(DataContainer::get).collect(Collectors.toList()));
      this.execute(table.getInfo().insert(), values.toArray());
    }

    return tableMap;
  }

  @Override
  public void save(String name, Map<String, Map<String, DataContainer>> tableMap) {
    this.save0(name, tableMap, true);
  }

  @Override
  public void saveSync(String name, Map<String, Map<String, DataContainer>> tableMap) {
    this.save0(name, tableMap, false);
  }

  private void save0(String name, Map<String, Map<String, DataContainer>> tableMap, boolean async) {
    for (DataTable table : DataTable.listTables()) {
      Map<String, DataContainer> rows = tableMap.get(table.getInfo().name());
      if (rows == null || rows.values().stream().noneMatch(DataContainer::isUpdated)) {
        continue;
      }

      List<Object> values = rows.values().stream().filter(DataContainer::isUpdated).map(DataContainer::get).collect(Collectors.toList());
      StringBuilder query = new StringBuilder("UPDATE \"" + table.getInfo().name() + "\" SET ");
      for (Map.Entry<String, DataContainer> column : rows.entrySet()) {
        if (column.getValue().isUpdated()) {
          column.getValue().setUpdated(false);
          query.append("\"").append(column.getKey()).append("\" = ?, ");
        }
      }
      query.setLength(query.length() - 2);
      query.append(" WHERE LOWER(\"name\") = ?");
      values.add(name.toLowerCase());
      if (async) {
        this.execute(query.toString(), values.toArray());
      } else {
        this.update(query.toString(), values.toArray());
      }
    }
  }

  @Override
  public String exists(String name) {
    try (CachedRowSet rs = this.query("SELECT \"name\" FROM \"kCoreProfile\" WHERE LOWER(\"name\") = ?", name.toLowerCase())) {
      if (rs != null) {
        return rs.getString("name");
      }
    } catch (SQLException ignored) {
    }
    return null;
  }

  public void update(String query, Object... vars) {
    try (PreparedStatement ps = prepareStatement(query, vars)) {
      if (ps != null) {
        ps.executeUpdate();
      }
    } catch (SQLException ex) {
      LOGGER.log(Level.WARNING, "Nao foi possivel executar um SQL: ", ex);
    }
  }

  protected void updateRequired(String query, Object... vars) {
    try (PreparedStatement ps = prepareStatement(query, vars)) {
      if (ps != null) {
        ps.executeUpdate();
      }
    } catch (SQLException ex) {
      String detail = ex.getMessage() == null ? ex.toString() : ex.getMessage();
      String message = "Falha ao inicializar o banco de dados: " + detail;
      if (detail.contains("permission denied for schema")) {
        message += ". No PostgreSQL 15+, usuarios comuns nao podem criar tabelas em public. "
            + "Use KCoreSettings.Database.SCHEMA = \"kcore\" ou conceda GRANT CREATE ON SCHEMA public.";
      }
      LOGGER.log(Level.SEVERE, message, ex);
      throw new DatabaseConnectionException(message, ex);
    }
  }

  public void execute(String query, Object... vars) {
    executor.execute(() -> update(query, vars));
  }

  public int updateWithInsertId(String query, Object... vars) {
    int id = -1;
    try (PreparedStatement ps = getConnection().prepareStatement(sql(query), Statement.RETURN_GENERATED_KEYS)) {
      for (int i = 0; i < vars.length; i++) {
        ps.setObject(i + 1, vars[i]);
      }
      ps.execute();
      try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
          id = rs.getInt(1);
        }
      }
    } catch (SQLException ex) {
      LOGGER.log(Level.WARNING, "Nao foi possivel executar um SQL: ", ex);
    }
    return id;
  }

  public PreparedStatement prepareStatement(String query, Object... vars) throws SQLException {
    PreparedStatement ps = getConnection().prepareStatement(sql(query));
    for (int i = 0; i < vars.length; i++) {
      ps.setObject(i + 1, vars[i]);
    }
    return ps;
  }

  public CachedRowSet query(String query, Object... vars) {
    try (PreparedStatement ps = prepareStatement(query, vars);
         ResultSet rs = ps.executeQuery()) {
      CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
      rowSet.populate(rs);
      if (rowSet.next()) {
        rowSet.beforeFirst();
        return rowSet;
      }
    } catch (SQLException ex) {
      LOGGER.log(Level.WARNING, "Nao foi possivel executar uma requisicao SQL: ", ex);
    }
    return null;
  }
}
