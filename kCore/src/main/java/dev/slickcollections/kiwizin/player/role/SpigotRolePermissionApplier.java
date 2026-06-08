package dev.slickcollections.kiwizin.player.role;

import dev.slickcollections.kiwizin.Core;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class SpigotRolePermissionApplier {

  private static final Map<String, PermissionAttachment> ATTACHMENTS = new ConcurrentHashMap<>();

  private SpigotRolePermissionApplier() {
  }

  public static void apply(Player player, Set<String> permissions) {
    clear(player);
    PermissionAttachment attachment = player.addAttachment(Core.getInstance());
    for (String permission : permissions) {
      attachment.setPermission(permission.toLowerCase(Locale.ROOT), true);
    }
    ATTACHMENTS.put(player.getName().toLowerCase(Locale.ROOT), attachment);
  }

  public static void clear(Player player) {
    PermissionAttachment attachment = ATTACHMENTS.remove(player.getName().toLowerCase(Locale.ROOT));
    if (attachment != null) {
      player.removeAttachment(attachment);
    }
  }

  public static boolean hasManagedPermission(Player player, String permission) {
    PermissionAttachment attachment = ATTACHMENTS.get(player.getName().toLowerCase(Locale.ROOT));
    if (attachment == null) {
      return false;
    }
    return attachment.getPermissions().getOrDefault(permission.toLowerCase(Locale.ROOT), false);
  }
}
