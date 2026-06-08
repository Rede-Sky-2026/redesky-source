package dev.slickcollections.kiwizin.player.role;

import dev.slickcollections.kiwizin.KCoreSettings;
import dev.slickcollections.kiwizin.Manager;
import dev.slickcollections.kiwizin.database.Database;
import dev.slickcollections.kiwizin.database.cache.RoleCache;
import dev.slickcollections.kiwizin.reflection.Accessors;
import dev.slickcollections.kiwizin.reflection.acessors.MethodAccessor;
import dev.slickcollections.kiwizin.utils.StringUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class RolePermissionManager {

  private static final Map<String, Set<String>> ROLE_PERMISSIONS = new ConcurrentHashMap<>();
  private static final Map<String, String> PLAYER_ROLE_KEYS = new ConcurrentHashMap<>();
  private static final Map<String, Set<String>> PLAYER_PERMISSIONS = new ConcurrentHashMap<>();
  private static final Map<String, Set<String>> APPLIED_PERMISSIONS = new ConcurrentHashMap<>();

  private static MethodAccessor SET_PERMISSION;

  private RolePermissionManager() {
  }

  public static void setup() {
    if (!Manager.BUNGEE) {
      return;
    }

    try {
      SET_PERMISSION = Accessors.getMethod(Class.forName("net.md_5.bungee.api.connection.ProxiedPlayer"), "setPermission", String.class, boolean.class);
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }

    reload();
  }

  public static void reload() {
    ROLE_PERMISSIONS.clear();
    for (Map.Entry<String, KCoreSettings.RoleDefinition> entry : KCoreSettings.Roles.definitions().entrySet()) {
      ROLE_PERMISSIONS.put(entry.getKey(), new LinkedHashSet<>(entry.getValue().permissions));
    }
  }

  public static Role resolveRole(String roleInput) {
    return KCoreSettings.Roles.resolve(roleInput);
  }

  public static String getRoleKey(Role role) {
    return KCoreSettings.Roles.getKey(role);
  }

  public static Role getAssignedRole(String playerName) {
    String roleKey = PLAYER_ROLE_KEYS.get(playerName.toLowerCase(Locale.ROOT));
    if (roleKey != null) {
      return KCoreSettings.Roles.getByKey(roleKey);
    }

    String cached = RoleCache.isPresent(playerName) ? RoleCache.get(playerName) : Database.getInstance().getRankAndName(playerName);
    if (cached != null) {
      return Role.getRoleByName(cached.split(" : ")[0]);
    }

    return Role.getLastRole();
  }

  public static Set<String> getEffectivePermissions(String playerName) {
    Set<String> permissions = new LinkedHashSet<>();
    Role role = getAssignedRole(playerName);
    if (!role.isDefault()) {
      permissions.add(role.getPermission());
    }
    permissions.addAll(ROLE_PERMISSIONS.getOrDefault(getRoleKey(role), Collections.emptySet()));
    permissions.addAll(PLAYER_PERMISSIONS.getOrDefault(playerName.toLowerCase(Locale.ROOT), Collections.emptySet()));
    return permissions;
  }

  public static boolean hasPermission(ProxiedPlayer player, String permission) {
    if (player.hasPermission(permission)) {
      return true;
    }
    return getEffectivePermissions(player.getName()).contains(permission);
  }

  public static void applyPermissions(ProxiedPlayer player) {
    clearManagedPermissions(player);
    Set<String> permissions = getEffectivePermissions(player.getName());
    for (String permission : permissions) {
      setPermission(player, permission, true);
    }
    APPLIED_PERMISSIONS.put(player.getName().toLowerCase(Locale.ROOT), new HashSet<>(permissions));
    RolePermissionSync.sendToBackend(player, permissions, getAssignedRole(player.getName()));
  }

  public static void clearManagedPermissions(ProxiedPlayer player) {
    Set<String> applied = APPLIED_PERMISSIONS.remove(player.getName().toLowerCase(Locale.ROOT));
    if (applied == null) {
      return;
    }
    for (String permission : applied) {
      setPermission(player, permission, false);
    }
  }

  public static boolean setPlayerRole(String targetName, Role role) {
    String roleKey = getRoleKey(role);
    String strippedRoleName = StringUtils.stripColors(role.getName());
    PLAYER_ROLE_KEYS.put(targetName.toLowerCase(Locale.ROOT), roleKey);
    RoleCache.removeCache(targetName);
    Database.getInstance().execute("UPDATE `kCoreProfile` SET `role` = ? WHERE LOWER(`name`) = ?", strippedRoleName, targetName.toLowerCase(Locale.ROOT));

    ProxiedPlayer online = ProxyServer.getInstance().getPlayer(targetName);
    if (online != null) {
      for (Role currentRole : Role.listRoles()) {
        if (!currentRole.isDefault()) {
          setPermission(online, currentRole.getPermission(), false);
        }
      }
      applyPermissions(online);
    }
    return true;
  }

  public static boolean addRolePermission(Role role, String permission) {
    Set<String> permissions = ROLE_PERMISSIONS.computeIfAbsent(getRoleKey(role), key -> new LinkedHashSet<>());
    boolean added = permissions.add(permission.toLowerCase(Locale.ROOT));
    if (added) {
      refreshOnlinePlayersWithRole(role);
    }
    return added;
  }

  public static boolean removeRolePermission(Role role, String permission) {
    Set<String> permissions = ROLE_PERMISSIONS.get(getRoleKey(role));
    if (permissions == null) {
      return false;
    }
    boolean removed = permissions.remove(permission.toLowerCase(Locale.ROOT));
    if (removed) {
      refreshOnlinePlayersWithRole(role);
    }
    return removed;
  }

  public static boolean addPlayerPermission(String playerName, String permission) {
    Set<String> permissions = PLAYER_PERMISSIONS.computeIfAbsent(playerName.toLowerCase(Locale.ROOT), key -> new LinkedHashSet<>());
    boolean added = permissions.add(permission.toLowerCase(Locale.ROOT));
    if (added) {
      ProxiedPlayer online = ProxyServer.getInstance().getPlayer(playerName);
      if (online != null) {
        applyPermissions(online);
      }
    }
    return added;
  }

  public static boolean removePlayerPermission(String playerName, String permission) {
    Set<String> permissions = PLAYER_PERMISSIONS.get(playerName.toLowerCase(Locale.ROOT));
    if (permissions == null) {
      return false;
    }
    boolean removed = permissions.remove(permission.toLowerCase(Locale.ROOT));
    if (removed) {
      ProxiedPlayer online = ProxyServer.getInstance().getPlayer(playerName);
      if (online != null) {
        applyPermissions(online);
      }
    }
    return removed;
  }

  public static List<String> listRolePermissions(Role role) {
    return new ArrayList<>(ROLE_PERMISSIONS.getOrDefault(getRoleKey(role), Collections.emptySet()));
  }

  public static List<String> listPlayerPermissions(String playerName) {
    return new ArrayList<>(PLAYER_PERMISSIONS.getOrDefault(playerName.toLowerCase(Locale.ROOT), Collections.emptySet()));
  }

  public static boolean hasStoredRole(String playerName) {
    return PLAYER_ROLE_KEYS.containsKey(playerName.toLowerCase(Locale.ROOT));
  }

  private static void refreshOnlinePlayersWithRole(Role role) {
    for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
      if (getAssignedRole(player.getName()).getPermission().equals(role.getPermission())) {
        applyPermissions(player);
      }
    }
  }

  private static void setPermission(ProxiedPlayer player, String permission, boolean value) {
    if (SET_PERMISSION != null) {
      SET_PERMISSION.invoke(player, permission, value);
    }
  }
}
