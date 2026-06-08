package dev.slickcollections.kiwizin.thebridge.lobby.leaderboards;

import dev.slickcollections.kiwizin.database.Database;
import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.KCoreSettings;
import dev.slickcollections.kiwizin.thebridge.lobby.Leaderboard;
import dev.slickcollections.kiwizin.utils.StringUtils;
import org.bukkit.Location;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinstreakLeaderboard extends Leaderboard {

  public WinstreakLeaderboard(Location location, String id) {
    super(location, id);
  }

  @Override
  public String getType() {
    return "winstreak";
  }

  @Override
  public List<String[]> getSplitted() {
    List<String[]> list = new ArrayList<>();
    try (CachedRowSet rs = Database.getInstance().query(
        "SELECT \"name\", \"winstreak\" FROM \"kCoreTheBridge\" WHERE DATE(TO_TIMESTAMP(\"laststreak\" / 1000.0)) = CURRENT_DATE ORDER BY \"winstreak\" DESC LIMIT 10")) {
      if (rs != null) {
        rs.beforeFirst();
        while (rs.next()) {
          list.add(new String[] {Role.getColored(rs.getString(1), true), StringUtils.formatNumber(rs.getLong(2))});
        }
      }
    } catch (SQLException ignore) {
    }

    while (list.size() < 10) {
      list.add(new String[] {KCoreSettings.TheBridge.lobby$leaderboard$empty, "0"});
    }
    return list;
  }

  @Override
  public List<String> getHologramLines() {
    return KCoreSettings.TheBridge.lobby$leaderboard$daily_winstreak$holograms;
  }
}
