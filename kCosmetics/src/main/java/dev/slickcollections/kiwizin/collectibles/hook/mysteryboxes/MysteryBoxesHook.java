package dev.slickcollections.kiwizin.collectibles.hook.mysteryboxes;

import dev.slickcollections.kiwizin.collectibles.Main;
import dev.slickcollections.kiwizin.collectibles.cosmetics.Cosmetic;
import dev.slickcollections.kiwizin.database.Database;
import dev.slickcollections.kiwizin.mysterybox.api.MysteryBoxAPI;
import org.bukkit.command.CommandSender;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysteryBoxesHook {

  private static Boolean unsynced;

  public static void sync(CommandSender sender) {
    if (!Database.getInstance().isSql()) {
      sender.sendMessage("§cEste recurso requer PostgreSQL.");
      return;
    }

    Database.getInstance().update("DELETE FROM `kMysteryBoxContent` WHERE `dependency` = ?", "kCosmetics");

    StringBuilder query = new StringBuilder("INSERT INTO `kMysteryBoxContent` VALUES ");
    Cosmetic.listCosmetics().forEach(cosmetic -> query.append("('").append(cosmetic.getLootChestsID()).append("', '").append("&f").append(cosmetic.getName()).append(" &7(Lobby - ")
        .append(cosmetic.getType().getName()).append(")', '").append(cosmetic.getRarity().name()).append("', 'kcs mb give {player} ").append(cosmetic.getLootChestsID())
        .append(" ; kcs mb give {player} ").append(cosmetic.getLootChestsID()).append("', 'kCosmetics'),"));

    if (query.charAt(query.length() - 1) == ',') {
      query.deleteCharAt(query.length() - 1);
    }
    Database.getInstance().update(query.toString());
    MysteryBoxAPI.reloadContents();
    sender.sendMessage("§aA sincronização terminou!");
    unsynced = false;
  }

  public static boolean isUnsynced() {
    if (unsynced == null) {
      if (Main.kMysteryBox && Database.getInstance().isSql()) {
        List<String> verified = new ArrayList<>();
        try (CachedRowSet rs = Database.getInstance().query("SELECT * FROM `kMysteryBoxContent` WHERE `dependency` = ?", "kCosmetics")) {
          if (rs != null) {
            rs.beforeFirst();
            while (rs.next()) {
              if (Cosmetic.findById(rs.getString("id")) == null) {
                unsynced = true;
                return unsynced;
              }
              verified.add(rs.getString("id"));
            }
          }
        } catch (SQLException ignore) {
        }
        unsynced = Cosmetic.listCosmetics().stream().anyMatch(c -> !verified.contains(c.getLootChestsID()));
        verified.clear();
        return unsynced;
      }

      unsynced = false;
    }

    return unsynced;
  }
}
