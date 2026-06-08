package dev.slickcollections.kiwizin.database;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.KCoreSettings;
import dev.slickcollections.kiwizin.Manager;
import dev.slickcollections.kiwizin.booster.NetworkBooster;
import dev.slickcollections.kiwizin.bungee.Bungee;
import dev.slickcollections.kiwizin.database.cache.RoleCache;
import dev.slickcollections.kiwizin.database.data.DataContainer;
import dev.slickcollections.kiwizin.database.exception.ProfileLoadException;

import javax.sql.rowset.CachedRowSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class Database {

  public static Logger LOGGER;
  private static Database instance;

  public static void setupDatabase() {
    LOGGER = Manager.BUNGEE ? Bungee.getInstance().getLogger() : Core.getInstance().getLogger();
    instance = new PostgreSQLDatabase(
        KCoreSettings.Database.HOST,
        KCoreSettings.Database.PORT,
        KCoreSettings.Database.NAME,
        KCoreSettings.Database.USER,
        KCoreSettings.Database.PASSWORD,
        KCoreSettings.Database.SSL_MODE
    );

    new Timer().scheduleAtFixedRate(RoleCache.clearCache(), TimeUnit.SECONDS.toMillis(60), TimeUnit.SECONDS.toMillis(60));
  }

  public static Database getInstance() {
    return instance;
  }

  public boolean isSql() {
    return this instanceof AbstractSqlDatabase;
  }

  public void update(String sql, Object... vars) {
    if (this instanceof AbstractSqlDatabase) {
      ((AbstractSqlDatabase) this).update(sql, vars);
    }
  }

  public void execute(String sql, Object... vars) {
    if (this instanceof AbstractSqlDatabase) {
      ((AbstractSqlDatabase) this).execute(sql, vars);
    }
  }

  public CachedRowSet query(String sql, Object... vars) {
    if (this instanceof AbstractSqlDatabase) {
      return ((AbstractSqlDatabase) this).query(sql, vars);
    }
    return null;
  }

  public int updateWithInsertId(String sql, Object... vars) {
    if (this instanceof AbstractSqlDatabase) {
      return ((AbstractSqlDatabase) this).updateWithInsertId(sql, vars);
    }
    return -1;
  }

  public void setupBoosters() {
  }

  public abstract void setBooster(String minigame, String booster, double multiplier, long expires);

  public abstract NetworkBooster getBooster(String minigame);

  public abstract String getRankAndName(String player);

  public abstract boolean getPreference(String player, String id, boolean def);

  public abstract List<String[]> getLeaderBoard(String table, String... columns);

  public abstract void close();

  public abstract Map<String, Map<String, DataContainer>> load(String name) throws ProfileLoadException;

  public abstract void save(String name, Map<String, Map<String, DataContainer>> tableMap);

  public abstract void saveSync(String name, Map<String, Map<String, DataContainer>> tableMap);

  public abstract String exists(String name);
}
