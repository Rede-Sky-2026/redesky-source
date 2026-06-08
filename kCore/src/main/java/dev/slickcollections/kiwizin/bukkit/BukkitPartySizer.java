package dev.slickcollections.kiwizin.bukkit;

import dev.slickcollections.kiwizin.KCoreSettings;
import org.bukkit.entity.Player;

import java.util.Map;

public class BukkitPartySizer {

  public static int getPartySize(Player player) {
    for (Map.Entry<String, Integer> entry : KCoreSettings.Party.SIZE_BY_PERMISSION.entrySet()) {
      if (player.hasPermission(entry.getKey())) {
        return entry.getValue();
      }
    }

    return KCoreSettings.Party.DEFAULT_SIZE;
  }
}
