package dev.slickcollections.kiwizin.player.role;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.slickcollections.kiwizin.utils.StringUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Set;

public final class RolePermissionSync {

  private RolePermissionSync() {
  }

  public static void sendToBackend(ProxiedPlayer player, Set<String> permissions, Role role) {
    if (player.getServer() == null) {
      return;
    }

    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("SYNC_PERMISSIONS");
    out.writeUTF(player.getName());
    out.writeUTF(StringUtils.stripColors(role.getName()));
    out.writeInt(permissions.size());
    for (String permission : permissions) {
      out.writeUTF(permission);
    }
    player.getServer().sendData("kCore", out.toByteArray());
  }
}
