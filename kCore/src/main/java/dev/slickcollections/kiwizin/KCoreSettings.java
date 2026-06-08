package dev.slickcollections.kiwizin;

import dev.slickcollections.kiwizin.player.role.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Configurações centralizadas do kCore.
 * Edite os valores desta classe para alterar banco, cargos, permissões e utilitários.
 */
public final class KCoreSettings {

  private KCoreSettings() {
  }

  public static final class Database {
    public static final String HOST = "localhost";
    public static final String PORT = "5432";
    /** Banco dedicado da rede. Nao use "postgres" com usuario limitado. */
    public static final String NAME = "minecraft";
    public static final String USER = "minecraft";
    public static final String PASSWORD = "redeskydbpsql";
    /**
     * Modo SSL do driver PostgreSQL: disable, allow, prefer, require, verify-ca, verify-full.
     * Use "disable" em localhost e "require" para PostgreSQL remoto/Azure.
     */
    public static final String SSL_MODE = "disable";
    /**
     * Schema onde as tabelas do kCore serao criadas.
     * Use "kcore" para evitar restricoes do PostgreSQL 15+ no schema public.
     */
    public static final String SCHEMA = "kcore";
    /** Tenta CREATE SCHEMA automaticamente. Se false, o DBA precisa criar o schema antes. */
    public static final boolean AUTO_CREATE_SCHEMA = true;

    public static String buildJdbcUrl() {
      String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + NAME;
      if (SSL_MODE != null && !SSL_MODE.trim().isEmpty()) {
        url += "?sslmode=" + SSL_MODE.trim().toLowerCase();
      }
      return url;
    }
  }

  public static final class General {
    public static final boolean CASH = true;
    public static final boolean BUNGEECORD = true;
  }

  public static final class Fake {
    public static final List<String> ALLOWED_ROLES = Arrays.asList("Membro", "VIP", "MVP", "MVP+");
    public static final List<String> RANDOM_NICKS = Arrays.asList("pottershot", "ismykiss", "overdead", "traveler");
    public static final String KICK_APPLY = "&c&lREDE SLICK\n \n&aSeu nick foi modificado\n&aEntre novamente no servidor para que faça efeito.";
    public static final String KICK_REMOVE = "&c&lREDE SLICK\n \n&aSeu nick foi resetado\n&aEntre novamente no servidor para que faça efeito.";
  }

  public static final class Party {
    public static final int DEFAULT_SIZE = 3;
    public static final Map<String, Integer> SIZE_BY_PERMISSION = buildPartySizes();

    private static Map<String, Integer> buildPartySizes() {
      Map<String, Integer> sizes = new LinkedHashMap<>();
      sizes.put("role.master", 20);
      sizes.put("role.youtuber", 15);
      sizes.put("role.mvpplus", 10);
      sizes.put("role.mvp", 5);
      return Collections.unmodifiableMap(sizes);
    }
  }

  public static final class Roles {
    private static final Map<String, RoleDefinition> DEFINITIONS = new LinkedHashMap<>();

    static {
      register("master", "&6Master", "&6[Master] ", "role.master", true, true,
          "kcore.admin", "kcore.cmd.setrank", "kcore.cmd.permission");
      register("gerente", "&4Gerente", "&4[Gerente] ", "role.gerente", true, true,
          "kcore.cmd.setrank", "kcore.cmd.permission");
      register("admin", "&cAdmin", "&c[Admin] ", "role.admin", true, true,
          "kcore.cmd.setrank", "kcore.cmd.permission");
      register("moderador", "&2Moderador", "&2[Moderador] ", "role.moderador", true, true, "kcore.fly");
      register("ajudante", "&eAjudante", "&e[Ajudante] ", "role.ajudante", true, true, "kcore.fly");
      register("youtuber", "&cYouTuber", "&c[YouTuber] ", "role.youtuber", true, true, "kcore.fly");
      register("mvpplus", "&bMVP&6+", "&b[MVP&6+&b] ", "role.mvpplus", false, true, "kcore.queue");
      register("mvp", "&6MVP", "&6[MVP] ", "role.mvp", false, true, "kcore.queue");
      register("vip", "&eVIP", "&e[VIP] ", "role.vip", false, true, "kcore.queue");
      register("membro", "&7Membro", "&7", "", false, false);
    }

    private static void register(String key, String name, String prefix, String permission,
                                 boolean alwaysVisible, boolean broadcast, String... extraPermissions) {
      DEFINITIONS.put(key, new RoleDefinition(key, name, prefix, permission, alwaysVisible, broadcast, extraPermissions));
    }

    public static void registerRoles() {
      Role.listRoles().clear();
      for (RoleDefinition definition : DEFINITIONS.values()) {
        Role.listRoles().add(definition.toRole());
      }
      if (Role.listRoles().isEmpty()) {
        Role.listRoles().add(new Role("&7Membro", "&7", "", false, false));
      }
    }

    public static Map<String, RoleDefinition> definitions() {
      return DEFINITIONS;
    }

    public static RoleDefinition get(String key) {
      return DEFINITIONS.get(key.toLowerCase());
    }

    public static String getKey(Role role) {
      for (RoleDefinition definition : DEFINITIONS.values()) {
        if (!definition.permission.isEmpty() && definition.permission.equalsIgnoreCase(role.getPermission())) {
          return definition.key;
        }
        if (definition.name.replace("&", "").equalsIgnoreCase(role.getName().replace("§", "").replace("&", ""))) {
          return definition.key;
        }
      }
      return role.isDefault() ? "membro" : role.getPermission().toLowerCase();
    }

    public static Role resolve(String input) {
      if (input == null || input.trim().isEmpty()) {
        return null;
      }

      String normalized = input.trim();
      RoleDefinition byKey = DEFINITIONS.get(normalized.toLowerCase());
      if (byKey != null) {
        return byKey.toRole();
      }

      for (Role role : Role.listRoles()) {
        if (role.getName().replace("§", "").equalsIgnoreCase(normalized.replace("&", ""))) {
          return role;
        }
        if (!role.getPermission().isEmpty() && role.getPermission().equalsIgnoreCase(normalized)) {
          return role;
        }
      }
      return null;
    }

    public static Role getByKey(String key) {
      RoleDefinition definition = DEFINITIONS.get(key.toLowerCase());
      return definition == null ? Role.getLastRole() : definition.toRole();
    }
  }

  public static final class RoleDefinition {
    public final String key;
    public final String name;
    public final String prefix;
    public final String permission;
    public final boolean alwaysVisible;
    public final boolean broadcast;
    public final List<String> permissions;

    public RoleDefinition(String key, String name, String prefix, String permission,
                          boolean alwaysVisible, boolean broadcast, String... permissions) {
      this.key = key;
      this.name = name;
      this.prefix = prefix;
      this.permission = permission;
      this.alwaysVisible = alwaysVisible;
      this.broadcast = broadcast;
      this.permissions = Arrays.asList(permissions);
    }

    public Role toRole() {
      return new Role(name, prefix, permission, alwaysVisible, broadcast);
    }
  }
}
