package dev.slickcollections.kiwizin.bungee.party;

import dev.slickcollections.kiwizin.KCoreSettings;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Map;

public class BungeePartySizer {

  public static int getPartySize(ProxiedPlayer player) {
    for (Map.Entry<String, Integer> entry : KCoreSettings.Party.SIZE_BY_PERMISSION.entrySet()) {
      if (player.hasPermission(entry.getKey())) {
        return entry.getValue();
      }
    }

    return KCoreSettings.Party.DEFAULT_SIZE;
  }
}
