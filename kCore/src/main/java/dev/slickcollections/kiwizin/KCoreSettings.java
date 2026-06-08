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

  public static final class Infos {
    public static final String SERVER_NAME = Network.NAME;
    public static final String SERVER_IP = Network.DOMAIN;
    public static final String SERVER_STORE = Network.STORE;
  }

  /** Informações globais da rede (tab, scoreboard, links, loja). */
  public static final class Network {
    public static final String NAME = "Rede Sky";
    public static final String DOMAIN = "redesky.com";
    public static final String STORE = "loja.redesky.com";
    public static final String FORUM = "redesky.com/forum";
    public static final String DISCORD = "redesky.com/discord";
    public static final String TWITTER = "@RedeSky";

    public static String coloredName() {
      return "§6§l" + NAME.toUpperCase();
    }

    public static String websiteLine() {
      return "§7www." + DOMAIN;
    }

    public static String websiteLineAlt() {
      return " &8- §7www." + DOMAIN;
    }

    public static String storeUrlLine() {
      return "§7www." + STORE;
    }

    public static String storeMenuLine() {
      return "§fAdquira Cash acessando:\n§a" + STORE;
    }
  }

  public static final class Tab {
    public static final boolean ENABLED = true;

    public static String header() {
      return " \n" + Network.coloredName() + "\n  §f" + Network.DOMAIN + "\n ";
    }

    public static String footer() {
      return " \n \n§aForúm: §f" + Network.FORUM
          + "\n§aTwitter: §f" + Network.TWITTER
          + "\n§aDiscord: §f" + Network.DISCORD
          + "\n \n                                          §bAdquira VIP acessando: §f" + Network.STORE + "                                          \n ";
    }
  }

  public static final class Chat {
    public static final String DELAY = "§cAguarde mais {time}s para falar novamente.";
    public static final String COLOR_DEFAULT = "§7";
    public static final String COLOR_CUSTOM = "§f";
    public static final String FORMAT_LOBBY = "{player}{color}: {message}";
    public static final String FORMAT_SPECTATOR = "§8[Espectador] {player}{color}: {message}";
    public static final String FORMAT_INGAME_TEAM = "§7[TIME] {player}{color}: {message}";
    public static final String FORMAT_INGAME_GLOBAL = "§6[GLOBAL] {team} {player}{color}: {message}";
  }

  public static final class Scoreboard {
    public static final long SCROLLER_EVERY_TICK = 1L;
    public static final String TIME_WAITING = "Aguardando...";
    public static final String TIME_STARTING = "Iniciando em §a{time}s";
  }

  public static final class Lobby {
    public static final String BROADCAST = "{player} §6entrou no lobby!";
    public static final String ACHIEVEMENT = " \n§aVocê completou o desafio §f{name}\n ";
    public static final long LEADERBOARD_MINUTES = 30L;
    public static final String LEADERBOARD_EMPTY = "§7Ninguém";
    public static final String NPC_PLAY_CONNECT = "§aConectando...";
    public static final String NPC_PLAY_INFO_ITEM = "PAPER : 1 : nome>§aInformações : desc>{desc}";
    public static final String NPC_MAP_SELECT_VIP_DESC =
        "§fLimite Diário: §7{limit}\n \n§7Jogadores que possuem o grupo §aVIP §7ou\n§7superior, podem escolher o mapa sem\n§7limite algum.\n \n" + Network.storeUrlLine();
    public static final String NPC_MAP_SELECT_NO_LIMIT = "§7Você não possui limite diário de seleções.";
    public static final String NPC_DELIVERIES_LABEL = "§c{deliveries} Entrega{s}";
    public static final List<String> NPC_DELIVERIES_HOLOGRAM = Arrays.asList("{deliveries}", "§bEntregador", "§e§lCLIQUE DIREITO");
    public static final String NPC_DELIVERIES_SKIN_VALUE =
        "eyJ0aW1lc3RhbXAiOjE1ODM0NTc4OTkzMTksInByb2ZpbGVJZCI6IjIxMWNhN2E4ZWFkYzQ5ZTVhYjBhZjMzMTBlODY0M2NjIiwicHJvZmlsZU5hbWUiOiJNYXh0ZWVyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MWU0NTc3OTgzZjEzZGI2YTRiMWMwNzQ1MGUyNzQ2MTVkMDMyOGUyNmI0MGQ3ZDMyMjA3MjYwOWJmZGQ0YTA4IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=";
    public static final String NPC_DELIVERIES_SKIN_SIGNATURE =
        "SXnMF3f9x90fa+FdP2rLk/V6/zvMNuZ0sC4RQpPHF9JxdVWYRZm/+DhxkfjCHWKXV/4FSTN8LPPsxXd0XlYSElpi5OaT9/LGhITSK6BbeBfaYhLZnoD0cf9jG9nl9av38KipnkNXI+cO3wttB27J7KHznAmfrJd5bxdO/M0aGQYtwpckchYUBG6pDzaxN7tr4bFxDdxGit8Tx+aow/YtYSQn4VilBIy2y/c2a4PzWEpWyZQ94ypF5ZojvhaSPVl88Fbh+StdgfJUWNN3hNWt31P68KT4Jhx+SkT2LTuDj0jcYsiuxHP6AzZXtOtPPARqM0/xd53CUHCK+TEF5mkbJsG/PZYz/JRR1B1STk4D2cgbhunF87V4NLmCBtF5WDQYid11eO0OnROSUbFduCLj0uJ6QhNRRdhSh54oES7vTi0ja3DftTjdFhPovDAXQxCn+ROhTeSxjW5ZvP6MpmJERCSSihv/11VGIrVRfj2lo9MaxRogQE3tnyMNKWm71IRZQf806hwSgHp+5m2mhfnjYeGRZr44j21zqnSKudDHErPyEavLF83ojuMhNqTTO43ri3MVbMGix4TbIOgB2WDwqlcYLezENBIIkRsYO/Y1r5BWCA7DJ5IlpxIr9TCu39ppVmOGReDWA/Znyox5GP6JIM53kQoTOFBM3QWIQcmXll4=";

    public static List<String> leaderboardHologram(String rankingTitle) {
      return Arrays.asList(
          "{monthly_color}Mensal {total_color}Total",
          "§6§lClique para alternar!",
          "§a10. {name_10} §7- §a{stats_10}",
          "§a9. {name_9} §7- §a{stats_9}",
          "§a8. {name_8} §7- §a{stats_8}",
          "§a7. {name_7} §7- §a{stats_7}",
          "§a6. {name_6} §7- §a{stats_6}",
          "§a5. {name_5} §7- §a{stats_5}",
          "§a4. {name_4} §7- §a{stats_4}",
          "§a3. {name_3} §7- §a{stats_3}",
          "§a2. {name_2} §7- §a{stats_2}",
          "§a1. {name_1} §7- §a{stats_1}",
          "",
          "§7" + rankingTitle,
          "§f§lTodos os Modos"
      );
    }
  }

  public static final class Cosmetics {
    public static final String COLOR_LOCKED = "§a";
    public static final String COLOR_CANBUY = "§e";
    public static final String COLOR_UNLOCKED = "§a";
    public static final String COLOR_SELECTED = "§6";
    public static final String ICON_PERM_COMMON = "§cVocê não possui permissão.";
    public static final String ICON_PERM_ROLE = "§7Exclusivo para {role} §7ou superior.";
    public static final String ICON_BUY_NOT_ENOUGH = "§cVocê não possui saldo suficiente.";
    public static final String ICON_BUY_CLICK = "§eClique para comprar!";
    public static final String ICON_HAS_SELECT = "§eClique para selecionar!";
    public static final String ICON_HAS_DESELECT = "§eClique para deselecionar!";
  }

  public static final class Ingame {
    public static final String BROADCAST_JOIN = "{player} §eentrou na partida! §a({players}/{max_players})";
    public static final String BROADCAST_LEAVE = "{player} §csaiu da partida! §a({players}/{max_players})";
    public static final String BROADCAST_STARTING = "§aO jogo começa em §f{time} §asegundo{s}.";
    public static final String TITLE_DEATH_HEADER = "§c§lVOCE MORREU";
    public static final String TITLE_DEATH_FOOTER = "§7Você agora é um espectador";
    public static final String TITLE_WIN_HEADER = "§a§lVITÓRIA";
    public static final String TITLE_LOSE_HEADER = "§c§lFIM DE JOGO";
    public static final String TITLE_LOSE_FOOTER = "§7Você não foi vitorioso dessa vez";
    public static final String MESSAGE_BOW_HIT = "{name} §aestá com §c{hp} §ade HP.";
  }

  public static final class Shop {
    public static final List<String> ITEM_HOLOGRAM = Arrays.asList("§e§lLOJA DE", "§e§lITENS", "§b§lCLIQUE DIREITO");
    public static final List<String> UPGRADE_HOLOGRAM = Arrays.asList("§e§lLOJA DE", "§e§lMELHORIAS", "§b§lCLIQUE DIREITO");
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
    public static final String KICK_APPLY = "&c&l" + Network.NAME.toUpperCase() + "\n \n&aSeu nick foi modificado\n&aEntre novamente no servidor para que faça efeito.";
    public static final String KICK_REMOVE = "&c&l" + Network.NAME.toUpperCase() + "\n \n&aSeu nick foi resetado\n&aEntre novamente no servidor para que faça efeito.";
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
  public static final class BedWars {
    
      public static int options$coins$wins = 50;
      public static int options$coins$beds = 25;
      public static int options$coins$kills = 5;
      public static int options$start$waiting = 45;
      public static int options$start$full = 10;
    
      public static List<String> options$events$all$timings = Arrays
          .asList("DIAMOND:280", "EMERALD:480", "DIAMOND:680", "EMERALD:880", "BEDDESTROY:1240", "FIM:16840");
      public static int options$regen$block_regen$per_tick = 20000;
      public static boolean options$regen$world_reload = true;
    
      public static String options$events$diamond = "Diamante {tier}";
      public static String options$events$emerald = "Esmeralda {tier}";
      public static String options$events$beddestroy = "Destruição de Camas";
      public static String options$events$end = "Fim de Jogo";
    
      public static int options$generator$diamond$countdown_tier_1 = 30;
      public static int options$generator$diamond$countdown_tier_2 = 23;
      public static int options$generator$diamond$countdown_tier_3 = 15;
      public static int options$generator$emerald$countdown_tier_1 = 65;
      public static int options$generator$emerald$countdown_tier_2 = 50;
      public static int options$generator$emerald$countdown_tier_3 = 35;
      public static double options$team_generator$emerald$countdown = 7.0;
      public static double options$team_generator$gold$countdown = 3.0;
      public static double options$team_generator$iron$countdown = 1.0;
    
      public static long scoreboards$scroller$every_tick = KCoreSettings.Scoreboard.SCROLLER_EVERY_TICK;
      public static List<String> scoreboards$scroller$titles = Arrays
          .asList("§a§lBED WARS", "§6§lB§a§lED WARS", "§f§lB§6§lE§a§lD WARS", "§f§lBE§6§lD §a§lWARS",
              "§f§lBED §6§lW§a§lARS", "§f§lBED W§6§lA§a§lRS", "§f§lBED WA§6§lR§a§lS",
              "§f§lBED WAR§6§lS", "§f§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§f§lBED WARS", "§f§lBED WARS",
              "§f§lBED WARS", "§f§lBED WARS", "§f§lBED WARS", "§f§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§f§lBED WARS", "§f§lBED WARS", "§f§lBED WARS", "§f§lBED WARS", "§f§lBED WARS",
              "§f§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS",
              "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS", "§a§lBED WARS");
      public static String scoreboards$time$waiting = KCoreSettings.Scoreboard.TIME_WAITING;
      public static String scoreboards$time$starting = KCoreSettings.Scoreboard.TIME_STARTING;
      public static List<String> scoreboards$lobby = Arrays
          .asList("", " §eGeral", "  Vitorias: §a%kCore_BedWars_wins%", "  Abates Finais: §a%kCore_BedWars_finalkills%",
              "  Abates Gerais: §a%kCore_BedWars_kills%", "", "  Camas Destr.: §a%kCore_BedWars_bedsdestroyeds%", "  Partidas: §a%kCore_BedWars_games%", "",
              " Coins: §6%kCore_BedWars_coins%", " Cash: §b%kCore_cash%", "", " " + KCoreSettings.Network.websiteLine(), " ");
      public static List<String> scoreboards$waiting =
          Arrays.asList("", "  Mapa: §a{map}", "  Jogadores: §a{players}/{max_players}",
              "", "  {time}", "", "  " + KCoreSettings.Network.websiteLine(), "");
      public static List<String> scoreboards$ingame = Arrays
          .asList("", " Próximo Evento:", " §a{next_event}", "", " {red}", " {pink}", " {aqua}",
              " {blue}", " {white}", " {orange}", " {purple}", " {green}", "", " " + KCoreSettings.Network.websiteLine(), " ");
    
      public static String chat$delay = KCoreSettings.Chat.DELAY;
      public static String chat$color$default = KCoreSettings.Chat.COLOR_DEFAULT;
      public static String chat$color$custom = KCoreSettings.Chat.COLOR_CUSTOM;
      public static String chat$format$ingame$team = KCoreSettings.Chat.FORMAT_INGAME_TEAM;
      public static String chat$format$ingame$global = KCoreSettings.Chat.FORMAT_INGAME_GLOBAL;
      public static String chat$format$lobby = KCoreSettings.Chat.FORMAT_LOBBY;
      public static String chat$format$spectator = KCoreSettings.Chat.FORMAT_SPECTATOR;
    
      public static String lobby$achievement = KCoreSettings.Lobby.ACHIEVEMENT;
      public static String lobby$broadcast = KCoreSettings.Lobby.BROADCAST;
      public static boolean lobby$tab$enabled = KCoreSettings.Tab.ENABLED;
      public static String lobby$tab$header = KCoreSettings.Tab.header();
      public static String lobby$tab$footer = KCoreSettings.Tab.footer();
    
      public static long lobby$leaderboard$minutes = KCoreSettings.Lobby.LEADERBOARD_MINUTES;
      public static String lobby$leaderboard$empty = KCoreSettings.Lobby.LEADERBOARD_EMPTY;
    
      public static List<String> lobby$leaderboard$wins$hologram =
          KCoreSettings.Lobby.leaderboardHologram("Ranking de Vitórias");
      public static List<String> lobby$leaderboard$kills$hologram =
          KCoreSettings.Lobby.leaderboardHologram("Ranking de Abates Finais");
      public static List<String> lobby$leaderboard$beds$hologram =
          KCoreSettings.Lobby.leaderboardHologram("Ranking de Camas Destruídas");
    
      public static String lobby$npc$play$connect = KCoreSettings.Lobby.NPC_PLAY_CONNECT;
      public static String lobby$npc$play$menu$info$item = KCoreSettings.Lobby.NPC_PLAY_INFO_ITEM;
      public static String lobby$npc$play$menu$info$desc_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_VIP_DESC;
      public static String lobby$npc$play$menu$info$desc_not_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_NO_LIMIT;
    
      public static String lobby$npc$deliveries$deliveries = KCoreSettings.Lobby.NPC_DELIVERIES_LABEL;
      public static List<String> lobby$npc$deliveries$hologram = KCoreSettings.Lobby.NPC_DELIVERIES_HOLOGRAM;
      public static List<String> lobby$npc$stats$hologram = Arrays
          .asList("§6Estatísticas", "Total de Eliminações: §7%kCore_BedWars_kills%",
              "Total de Vitórias: §7%kCore_BedWars_wins%", "§e§lCLIQUE DIREITO");
      public static List<String> lobby$npc$play$dupla$hologram = Arrays
          .asList("§bDuplas", "§a{players} Jogadores");
      public static List<String> lobby$npc$play$solo$hologram = Arrays
          .asList("§bSolo", "§a{players} Jogadores");
      public static List<String> lobby$npc$play$quarteto$hologram = Arrays
          .asList("§bQuartetos", "§a{players} Jogadores");
      public static String lobby$npc$deliveries$skin$value = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_VALUE;
      public static String lobby$npc$deliveries$skin$signature = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_SIGNATURE;
      public static String lobby$npc$play$dupla$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1MjM1Njk3MjI0OTgsInByb2ZpbGVJZCI6IjdiM2QxNGQ2YzExZDRjODA5NTc1ZjI5ODczNGE0ZDFiIiwicHJvZmlsZU5hbWUiOiJUYWxvbkRldiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQwMTdhYmQ5ZjExZTlkZTM4ODBkNGM0OTAxODUzNTdiOGY4ZmY1NGM3MzA2Mzg2ZTgyYWQ1NjdhNTMwMzMifX19";
      public static String lobby$npc$play$dupla$skin$signature =
          "i7k5tYkZ0CJ1hnGrGELLVXjIi0hfVVtg+c4a/iXP4wOwvAPj6tQtExFWgGaZYnYhN6ldcjJKUw13a/TRwHi4er4OceOlxBgqSvc0zzT7U4iZsEUuCwv7r9t6a+3MELqSQe3/bbX6WP6pDA9TRSVWaCTGpBtZfAYyrszk+VTowMjKrDB7r/kzrhE+h2rSozVcv4fUMGOd4m8xbTPlcvBatZ9OcHfZEpuoTpECUq3tWH3GIJi+Uxz3rTVl5rKJdKLOeUVXLpiLSgQ0jybMy705WlB0NWFbWFkY0mEQU7yca6keopEsGaQ+36yEtcE4hKYhibqW2sFhne/wIZh5arwyXVv/04twL/dpdiBwg4nqGEO60i+tQoF9RVWeCmIwJizEn3+WO6H2QogfCy+W1vNO65/HoHlhVbC6Y6nkUUQ8r0jtqz/sBQVAEBhFDjOQcdFucyjnO4LXrZPajdzJtBhkottBZDQZQlbFoZxC47WpQ+sktc51SWT2f3BzMowRKg08R8xpZxMTf+bB5OldilMuDPggXF/wVQU4+N9OFo1qYNxRPtM/7DCP8dtS7pwfhJkRhnQOfBVu7/mkNX1EM3mlMRzhEiUmqXfhL3SSyzTzqdTB76JgrRF92zuW+ouUlnXHe4hWiaWvRQ1XHB4fc+HOQ6/1RMYb4NItJFte1tjcQQs=";
      public static String lobby$npc$play$solo$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1MjM1Njk3MjI0OTgsInByb2ZpbGVJZCI6IjdiM2QxNGQ2YzExZDRjODA5NTc1ZjI5ODczNGE0ZDFiIiwicHJvZmlsZU5hbWUiOiJUYWxvbkRldiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQwMTdhYmQ5ZjExZTlkZTM4ODBkNGM0OTAxODUzNTdiOGY4ZmY1NGM3MzA2Mzg2ZTgyYWQ1NjdhNTMwMzMifX19";
      public static String lobby$npc$play$solo$skin$signature =
          "i7k5tYkZ0CJ1hnGrGELLVXjIi0hfVVtg+c4a/iXP4wOwvAPj6tQtExFWgGaZYnYhN6ldcjJKUw13a/TRwHi4er4OceOlxBgqSvc0zzT7U4iZsEUuCwv7r9t6a+3MELqSQe3/bbX6WP6pDA9TRSVWaCTGpBtZfAYyrszk+VTowMjKrDB7r/kzrhE+h2rSozVcv4fUMGOd4m8xbTPlcvBatZ9OcHfZEpuoTpECUq3tWH3GIJi+Uxz3rTVl5rKJdKLOeUVXLpiLSgQ0jybMy705WlB0NWFbWFkY0mEQU7yca6keopEsGaQ+36yEtcE4hKYhibqW2sFhne/wIZh5arwyXVv/04twL/dpdiBwg4nqGEO60i+tQoF9RVWeCmIwJizEn3+WO6H2QogfCy+W1vNO65/HoHlhVbC6Y6nkUUQ8r0jtqz/sBQVAEBhFDjOQcdFucyjnO4LXrZPajdzJtBhkottBZDQZQlbFoZxC47WpQ+sktc51SWT2f3BzMowRKg08R8xpZxMTf+bB5OldilMuDPggXF/wVQU4+N9OFo1qYNxRPtM/7DCP8dtS7pwfhJkRhnQOfBVu7/mkNX1EM3mlMRzhEiUmqXfhL3SSyzTzqdTB76JgrRF92zuW+ouUlnXHe4hWiaWvRQ1XHB4fc+HOQ6/1RMYb4NItJFte1tjcQQs=";
      public static String lobby$npc$play$quarteto$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1MjM1Njk3MjI0OTgsInByb2ZpbGVJZCI6IjdiM2QxNGQ2YzExZDRjODA5NTc1ZjI5ODczNGE0ZDFiIiwicHJvZmlsZU5hbWUiOiJUYWxvbkRldiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQwMTdhYmQ5ZjExZTlkZTM4ODBkNGM0OTAxODUzNTdiOGY4ZmY1NGM3MzA2Mzg2ZTgyYWQ1NjdhNTMwMzMifX19";
      public static String lobby$npc$play$quarteto$skin$signature =
          "i7k5tYkZ0CJ1hnGrGELLVXjIi0hfVVtg+c4a/iXP4wOwvAPj6tQtExFWgGaZYnYhN6ldcjJKUw13a/TRwHi4er4OceOlxBgqSvc0zzT7U4iZsEUuCwv7r9t6a+3MELqSQe3/bbX6WP6pDA9TRSVWaCTGpBtZfAYyrszk+VTowMjKrDB7r/kzrhE+h2rSozVcv4fUMGOd4m8xbTPlcvBatZ9OcHfZEpuoTpECUq3tWH3GIJi+Uxz3rTVl5rKJdKLOeUVXLpiLSgQ0jybMy705WlB0NWFbWFkY0mEQU7yca6keopEsGaQ+36yEtcE4hKYhibqW2sFhne/wIZh5arwyXVv/04twL/dpdiBwg4nqGEO60i+tQoF9RVWeCmIwJizEn3+WO6H2QogfCy+W1vNO65/HoHlhVbC6Y6nkUUQ8r0jtqz/sBQVAEBhFDjOQcdFucyjnO4LXrZPajdzJtBhkottBZDQZQlbFoZxC47WpQ+sktc51SWT2f3BzMowRKg08R8xpZxMTf+bB5OldilMuDPggXF/wVQU4+N9OFo1qYNxRPtM/7DCP8dtS7pwfhJkRhnQOfBVu7/mkNX1EM3mlMRzhEiUmqXfhL3SSyzTzqdTB76JgrRF92zuW+ouUlnXHe4hWiaWvRQ1XHB4fc+HOQ6/1RMYb4NItJFte1tjcQQs=";
    
      public static String cosmetics$color$locked = KCoreSettings.Cosmetics.COLOR_LOCKED;
      public static String cosmetics$color$canbuy = KCoreSettings.Cosmetics.COLOR_CANBUY;
      public static String cosmetics$color$unlocked = KCoreSettings.Cosmetics.COLOR_UNLOCKED;
      public static String cosmetics$color$selected = KCoreSettings.Cosmetics.COLOR_SELECTED;
    
      public static String cosmetics$icon$perm_desc$common = KCoreSettings.Cosmetics.ICON_PERM_COMMON;
      public static String cosmetics$icon$perm_desc$role = KCoreSettings.Cosmetics.ICON_PERM_ROLE;
      public static String cosmetics$icon$buy_desc$enough = KCoreSettings.Cosmetics.ICON_BUY_NOT_ENOUGH;
      public static String cosmetics$icon$buy_desc$click_to_buy = KCoreSettings.Cosmetics.ICON_BUY_CLICK;
      public static String cosmetics$icon$has_desc$select = KCoreSettings.Cosmetics.ICON_HAS_SELECT;
      public static String cosmetics$icon$has_desc$selected = KCoreSettings.Cosmetics.ICON_HAS_DESELECT;
    
      public static String cosmetics$kill_effect$icon$perm_desc$start = "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$kill_effect$icon$buy_desc$start =
          "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$kill_effect$icon$has_desc$start = "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$shopkeeperskin$icon$perm_desc$start =
          "§7Altera a skin da Loja para {name}\n&7durante a partida.\n  \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$shopkeeperskin$icon$buy_desc$start =
          "§7Altera a skin da Loja para {name}\n&7durante a partida.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$shopkeeperskin$icon$has_desc$start =
          "§7Altera a skin da Loja para {name}\n&7durante a partida.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$break_effect$icon$perm_desc$start =
          "§7Quando você quebrar uma cama\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$break_effect$icon$buy_desc$start =
          "§7Quando você quebrar uma cama\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$break_effect$icon$has_desc$start =
          "§7Quando você quebrar uma cama\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$deathcry$icon$perm_desc$start =
          "§7Quando você morrer tocará\n§7o grito de morte {name}.\n \n§6Clique direito para escutar!\n  \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathcry$icon$buy_desc$start =
          "§7Quando você morrer tocará\n§7o grito de morte {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n§6Clique direito para escutar!\n \n{buy_desc_status}";
      public static String cosmetics$deathcry$icon$has_desc$start =
          "§7Quando você morrer tocará\n§7o grito de morte {name}.\n \n§6Clique direito para escutar!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";

      public static String cosmetics$fall_effect$icon$perm_desc$start =
          "§7Quando você levar dano de queda\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$fall_effect$icon$buy_desc$start =
          "§7Quando você levar dano de queda\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$fall_effect$icon$has_desc$start =
          "§7Quando você levar dano de queda\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";

      public static String cosmetics$wood_types$icon$perm_desc$start =
          "§7Costumizar a madeira do \n§7Vendedor para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$wood_types$icon$buy_desc$start =
          "§7Costumizar a madeira do \n§7Vendedor para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$wood_types$icon$has_desc$start =
          "§7Costumizar a madeira do \n§7Vendedor para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$death_message$icon$perm_desc$start =
          "\n§6Clique direito para ver!\n \n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$death_message$icon$buy_desc$start =
          "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$death_message$icon$has_desc$start =
          "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$cage$icon$perm_desc$start =
          "§7Altere sua jaula para {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$cage$icon$buy_desc$start =
          "§7Altere sua jaula para {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins\n \n{buy_desc_status}";
      public static String cosmetics$cage$icon$has_desc$start =
          "§7Altere sua jaula para {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$balloon$icon$perm_desc$start = "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$balloon$icon$buy_desc$start =
          "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$balloon$icon$has_desc$start = "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$win_animation$icon$perm_desc$start = "\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$win_animation$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$win_animation$icon$has_desc$start = "\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static List<String> ingame$generators$hologram = Arrays
          .asList("§eNível §c{tier}", "{type}", "§eGera em §c{time} §esegundo{s}");
      public static List<String> ingame$npc$shop$item$hologram = KCoreSettings.Shop.ITEM_HOLOGRAM;
      public static List<String> ingame$npc$shop$upgrade$hologram = KCoreSettings.Shop.UPGRADE_HOLOGRAM;
    
      public static String ingame$broadcast$join = KCoreSettings.Ingame.BROADCAST_JOIN;
      public static String ingame$broadcast$leave = KCoreSettings.Ingame.BROADCAST_LEAVE;
      public static String ingame$broadcast$starting = KCoreSettings.Ingame.BROADCAST_STARTING;
      public static String ingame$broadcast$suicide = "{name} §amorreu sozinho.";
      public static String ingame$broadcast$killed = "{name} §afoi abatido por {killer}";
      public static String ingame$broadcast$generator_upgrade$diamond = "§eOs geradores de §b§lDiamante §eforam aprimorados para o nivel §b§l{tier}§e!";
      public static String ingame$broadcast$generator_upgrade$emerald = "§eOs geradores de §2§lEsmeralda §eforam aprimorados para o nivel §b§l{tier}§e!";
      public static String ingame$broadcast$team_eliminated = "\n §f§lTIME ELIMINADO > §cO time {team} §cfoi eliminado!\n ";
      public static String ingame$broadcast$bed_destroyself = "\n §f§lCAMA DESTRUIDA > §7A sua cama foi destruída por {name}\n ";
      public static String ingame$broadcast$bed_destroy = "\n §f§lCAMA DESTRUIDA > §7A cama do time {team}§7 foi destruída por {name}\n ";
      public static String ingame$broadcast$double_kill = "§a. §e§lDOUBLE KILL";
      public static String ingame$broadcast$triple_kill = "§a. §b§lTRIPLE KILL";
      public static String ingame$broadcast$quadra_kill = "§a. §6§lQUADRA KILL";
      public static String ingame$broadcast$monster_kill = "§a. §c§lMONSTER KILL";
      public static String ingame$broadcast$end = " \n§aO tempo acabou, não houve ganhadores.\n ";
      public static String ingame$broadcast$win$solo = " \n{name} §avenceu a partida!\n ";
      public static String ingame$broadcast$win$dupla = " \n{name} §avenceram a partida!\n ";
      public static String ingame$titles$death$header = KCoreSettings.Ingame.TITLE_DEATH_HEADER;
      public static String ingame$titles$death$footer = KCoreSettings.Ingame.TITLE_DEATH_FOOTER;
      public static String ingame$titles$win$header = KCoreSettings.Ingame.TITLE_WIN_HEADER;
      public static String ingame$titles$win$footer = "§7Seu time venceu!";
      public static String ingame$titles$beddestroy_self$header = "§c§lCAMA DESTRUIDA";
      public static String ingame$titles$beddestroy_self$footer = "§7Você não renascerá mais.";
      public static String ingame$titles$lose$header = KCoreSettings.Ingame.TITLE_LOSE_HEADER;
      public static String ingame$titles$lose$footer = KCoreSettings.Ingame.TITLE_LOSE_FOOTER;
      public static String ingame$messages$bow$hit = KCoreSettings.Ingame.MESSAGE_BOW_HIT;
      public static String ingame$messages$coins$base = " \n  §a{coins} coins ganhos nesta partida:\n {coins_win}{coins_beds}{coins_kills}\n ";
      public static String ingame$messages$coins$win = "\n       §a+{coins} §fpor vencer o jogo";
      public static String ingame$messages$coins$beds = "\n       §a+{coins} §fpor destruir §c{beds} §fcama{s}";
      public static String ingame$messages$coins$kills = "\n       §a+{coins} §fpor realizar §c{kills} §fabate{s}";
  }


  public static final class SkyWars {
    
      public static long scoreboards$scroller$every_tick = KCoreSettings.Scoreboard.SCROLLER_EVERY_TICK;
      public static List<String> scoreboards$scroller$titles = Arrays
          .asList("§a§lSKY WARS", "§6§lS§a§lKY WARS", "§f§lS§6§lK§a§lY WARS", "§f§lSK§6§lY §a§lWARS",
              "§f§lSKY §6§lW§a§lARS", "§f§lSKY W§6§lA§a§lRS", "§f§lSKY WA§6§lR§a§lS",
              "§f§lSKY WAR§6§lS", "§f§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§f§lSKY WARS", "§f§lSKY WARS",
              "§f§lSKY WARS", "§f§lSKY WARS", "§f§lSKY WARS", "§f§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§f§lSKY WARS", "§f§lSKY WARS", "§f§lSKY WARS", "§f§lSKY WARS", "§f§lSKY WARS",
              "§f§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS",
              "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS", "§a§lSKY WARS");
      public static String scoreboards$time$waiting = KCoreSettings.Scoreboard.TIME_WAITING;
      public static String scoreboards$time$starting = KCoreSettings.Scoreboard.TIME_STARTING;
      public static String scoreboards$ranking$empty = "§7Ninguém §f[0]";
      public static String scoreboards$ranking$format = "{name} §f[{kills}]";
      public static List<String> scoreboards$lobby = Arrays
          .asList("", " §8>§f Eliminações: §a%kCore_SkyWars_kills%",
              " §8>§f Vitórias: §a%kCore_SkyWars_wins%", " §8>§f Partidas: §a%kCore_SkyWars_games%", "",
              " §8>§f Cash: §b%kCore_cash%", " §8>§f Coins: §6%kCore_SkyWars_coins%", "", KCoreSettings.Network.websiteLineAlt(), "");
      public static List<String> scoreboards$waiting =
          Arrays
              .asList("", " §8>§f Mapa: §a{map}", " §8>§f Jogadores: §a{players}/{max_players}",
                  "", "  {time}", "", KCoreSettings.Network.websiteLineAlt(), "");
      public static List<String> scoreboards$ingame$solo = Arrays
          .asList("", " §8>§f Próximo Evento:", " §8>§f §a{next_event}", "", " §8>§f Restantes: §a{players}", "",
              " §8>§f Abates: §a{kills}", "", " §8>§f Mapa: §a{map}", " §8>§f Modo: §a{mode}", "", KCoreSettings.Network.websiteLineAlt(), "");
      public static List<String> scoreboards$ingame$dupla = Arrays
          .asList("", " §8>§f Próximo Evento:", " §8>§f §a{next_event}", "", " §8>§f Restantes: §a{players}", " §8>§f Times Restantes: §a{teams}", "",
              " §8>§f Abates: §a{kills}", "", " §8>§f Mapa: §a{map}", " §8>§f Modo: §a{mode}", "", KCoreSettings.Network.websiteLineAlt(), "");
      public static String cosmetics$color$locked = KCoreSettings.Cosmetics.COLOR_LOCKED;
      public static String cosmetics$color$canbuy = KCoreSettings.Cosmetics.COLOR_CANBUY;
      public static String cosmetics$color$unlocked = KCoreSettings.Cosmetics.COLOR_UNLOCKED;
      public static String cosmetics$color$selected = KCoreSettings.Cosmetics.COLOR_SELECTED;
      public static String cosmetics$icon$perm_desc$common = KCoreSettings.Cosmetics.ICON_PERM_COMMON;
      public static String cosmetics$icon$perm_desc$role = KCoreSettings.Cosmetics.ICON_PERM_ROLE;
      public static String cosmetics$icon$buy_desc$enough = KCoreSettings.Cosmetics.ICON_BUY_NOT_ENOUGH;
      public static String cosmetics$icon$buy_desc$click_to_buy = KCoreSettings.Cosmetics.ICON_BUY_CLICK;
      public static String cosmetics$icon$has_desc$select = KCoreSettings.Cosmetics.ICON_HAS_SELECT;
      public static String cosmetics$icon$has_desc$selected = KCoreSettings.Cosmetics.ICON_HAS_DESELECT;
      public static String cosmetics$kit$icon$perm_desc$start = "\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$kit$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$kit$icon$has_desc$start = "\n \n§fRaridade: {rarity}\n \n§eAdquirido!\n§eClique para customizar ou evoluir!";
      public static String cosmetics$perk$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$kill_effect$icon$perm_desc$start = "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$kill_effect$icon$buy_desc$start =
          "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$kill_effect$icon$has_desc$start = "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$projectile_effect$icon$perm_desc$start =
          "§7Quando você jogar um projétil\n§7sairá partículas de {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$projectile_effect$icon$buy_desc$start =
          "§7Quando você jogar um projétil\n§7sairá partículas de {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$projectile_effect$icon$has_desc$start =
          "§7Quando você jogar um projétil\n§7sairá partículas de {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$fall_effect$icon$perm_desc$start =
          "§7Quando você levar dano de queda\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$fall_effect$icon$buy_desc$start =
          "§7Quando você levar dano de queda\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$fall_effect$icon$has_desc$start =
          "§7Quando você levar dano de queda\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$teleport_effect$icon$perm_desc$start =
          "§7Quando você se teleportar\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$teleport_effect$icon$buy_desc$start =
          "§7Quando você se teleportar\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$teleport_effect$icon$has_desc$start =
          "§7Quando você se teleportar\n§7sairá partículas de {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$deathcry$icon$perm_desc$start =
          "§7Quando você morrer tocará\n§7o grito de morte {name}.\n \n§6Clique direito para escutar!\n  \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathcry$icon$buy_desc$start =
          "§7Quando você morrer tocará\n§7o grito de morte {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n§6Clique direito para escutar!\n \n{buy_desc_status}";
      public static String cosmetics$deathcry$icon$has_desc$start =
          "§7Quando você morrer tocará\n§7o grito de morte {name}.\n \n§6Clique direito para escutar!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$deathmessage$icon$perm_desc$start =
          "\n§6Clique direito para ver!\n \n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathmessage$icon$buy_desc$start =
          "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$deathmessage$icon$has_desc$start =
          "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$deathhologram$icon$perm_desc$start =
          "§7Quando você morrer spawnará\n§7um holograma de morte {name}.\n \n§6Clique direito para ver!\n \n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathhologram$icon$buy_desc$start =
          "§7Quando você morrer spawnará\n§7um holograma de morte {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$deathhologram$icon$has_desc$start =
          "§7Quando você morrer spawnará\n§7um holograma de morte {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$cage$icon$perm_desc$start =
          "§7Utilize a jaula {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$cage$icon$buy_desc$start =
          "§7Utilize a jaula {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins\n \n{buy_desc_status}";
      public static String cosmetics$cage$icon$has_desc$start =
          "§7Utilize a jaula {name}.\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$balloon$icon$perm_desc$start = "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$balloon$icon$buy_desc$start =
          "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$balloon$icon$has_desc$start = "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$win_animation$icon$perm_desc$start = "\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$win_animation$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$win_animation$icon$has_desc$start = "\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String chat$delay = KCoreSettings.Chat.DELAY;
      public static String chat$color$default = KCoreSettings.Chat.COLOR_DEFAULT;
      public static String chat$color$custom = KCoreSettings.Chat.COLOR_CUSTOM;
      public static String chat$format$lobby = "{league} {player}{color}: {message}";
      public static String chat$format$spectator = KCoreSettings.Chat.FORMAT_SPECTATOR;
      public static int options$coins$wins = 50;
      public static int options$coins$kills = 5;
      public static int options$points$wins = 60;
      public static int options$points$kills = 20;
      public static int options$start$waiting = 45;
      public static int options$start$full = 10;
      public static boolean options$regen$world_reload = true;
      public static int options$regen$block_regen$per_tick = 20000;
      public static String options$events$refill = "Reabastecimento";
      public static String options$events$end = "Fim de Jogo";
      public static List<String> options$events$solo$timings = Arrays
          .asList("REFILL:300", "REFILL:480", "ANUNCIO:540", "FIM:840");
      public static List<String> options$events$ranked$timings = Arrays
          .asList("REFILL:300", "REFILL:480", "ANUNCIO:540", "FIM:840");
      public static List<String> options$events$dupla$timings = Arrays
          .asList("REFILL:300", "REFILL:480", "ANUNCIO:540", "FIM:840");
      public static String lobby$achievement = KCoreSettings.Lobby.ACHIEVEMENT;
      public static String lobby$broadcast = KCoreSettings.Lobby.BROADCAST;
      public static boolean lobby$tab$enabled = KCoreSettings.Tab.ENABLED;
      public static String lobby$tab$header = KCoreSettings.Tab.header();
      public static String lobby$tab$footer = KCoreSettings.Tab.footer();
      public static long lobby$leaderboard$minutes = KCoreSettings.Lobby.LEADERBOARD_MINUTES;
      public static String lobby$leaderboard$empty = KCoreSettings.Lobby.LEADERBOARD_EMPTY;
      public static List<String> lobby$leaderboard$wins$hologram =
          KCoreSettings.Lobby.leaderboardHologram("§7Ranking de Vitórias");
      public static List<String> lobby$leaderboard$kills$hologram =
          KCoreSettings.Lobby.leaderboardHologram("§7Ranking de Abates");
      public static List<String> lobby$leaderboard$points$hologram = Arrays
          .asList("§a10. {name_10} §7- §a{stats_10}", "§a9. {name_9} §7- §a{stats_9}",
              "§a8. {name_8} §7- §a{stats_8}", "§a7. {name_7} §7- §a{stats_7}",
              "§a6. {name_6} §7- §a{stats_6}",
              "§a5. {name_5} §7- §a{stats_5}", "§a4. {name_4} §7- §a{stats_4}",
              "§a3. {name_3} §7- §a{stats_3}", "§a2. {name_2} §7- §a{stats_2}",
              "§a1. {name_1} §7- §a{stats_1}", "",
              "§7Ranking de Pontos", "§f§lRanqueado");
      public static String lobby$npc$play$connect = KCoreSettings.Lobby.NPC_PLAY_CONNECT;
      public static String lobby$npc$play$menu$info$item = KCoreSettings.Lobby.NPC_PLAY_INFO_ITEM;
      public static String lobby$npc$play$menu$info$desc_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_VIP_DESC;
      public static String lobby$npc$play$menu$info$desc_not_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_NO_LIMIT;
      public static String lobby$npc$deliveries$deliveries = KCoreSettings.Lobby.NPC_DELIVERIES_LABEL;
      public static List<String> lobby$npc$deliveries$hologram = KCoreSettings.Lobby.NPC_DELIVERIES_HOLOGRAM;
      public static List<String> lobby$npc$promotion$hologram = Arrays
          .asList("§c{promotions} Itens em Promoção", "§bPromoções", "§e§lCLIQUE DIREITO");
      public static List<String> lobby$npc$stats$hologram = Arrays
          .asList("§6Estatísticas", "Total de Eliminações: §7%kCore_SkyWars_kills%", "Total de Vitórias: §7%kCore_SkyWars_wins%", "§e§lCLIQUE DIREITO");
      public static List<String> lobby$npc$play$solo$hologram = Arrays
          .asList("§bSolo", "§a{players} Jogadores");
      public static List<String> lobby$npc$play$dupla$hologram = Arrays
          .asList("§bDuplas", "§a{players} Jogadores");
      public static List<String> lobby$npc$play$ranked$hologram = Arrays
          .asList("§bRanked", "§a{players} Jogadores");
      public static String lobby$npc$promotion$skin$value =
          "ewogICJ0aW1lc3RhbXAiIDogMTYyMzI1Mzg3NzQwNSwKICAicHJvZmlsZUlkIiA6ICJhMDVkZWVjMDdhMGU0MDc2ODdjYmRjNWRjYWNhODU4NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJWaWxsYWdlciIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83YWY3YzA3ZDFkZWQ2MWIxZDMzMTI2ODViMzJlNDU2OGZmZGRhNzYyZWM4ZDgwODg5NWNjMzI5YTkzZDYwNmUwIgogICAgfQogIH0KfQ==";
      public static String lobby$npc$promotion$skin$signature =
          "yeeouiiK7vV/9o4BpBUz/UMoRWqkLE9GeLU/fbcXWFBycWw/z2CxX8UXdXrmg8IDKOA9ELTeeQQcuejo7dC0tsOGhgyeWPJR5jn8H3w24ZmeG154cQMvtTQW/fKuCcwFxj807VSBtGoEYqYjgaxYmou32dBCnPrB+L7RUf/kNO7wPhTj1zykwuhdXBNHS8G05ghFXmr9SN1CoctUhR5aK++CESn5qLVMhZAA81eCd29v0Tx65Kq6gSQpBU/dElFaVhCaQph5j/0XgDfYy5TOwK4tvhA18vpbHPGRXFg0Da873d0U5g9JqFcMMzD3DWq+J2XdhASyDjX4qlrn+0ShKtS2FIeN+soksLH+62pSuF+cNmvltPLktSlyp5tf6gy6Eox/MH6GANJ5O+NCDxxvqNli8z14zfM62nHJMIeDXGYyeZLyf35n9bHbIZnVmPjgp+/g0xyDq02jGOJvWmzlZe+WdhnDc6HeIIJFSkoY8GBUkPqhMTmCge6FhcahWQFxO0AXZysQevl+DlvwRkmcBTH3PgW6ryebcDWxZ7LdH6tg9RCO/aKwoi1fw06NczAOtLrz4zN1jvJD8tzaNtID78nYVPSCJWysMY5NnEVQdE6DglHs13Xe1kJtMK1Xs75j9WbTfrulLPVlbGAE4//odVKB7jhi2xtA7VUr45Y7I3Y=";
      public static String lobby$npc$deliveries$skin$value = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_VALUE;
      public static String lobby$npc$deliveries$skin$signature = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_SIGNATURE;
      public static String lobby$npc$play$solo$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1MjM1Njk3MjI0OTgsInByb2ZpbGVJZCI6IjdiM2QxNGQ2YzExZDRjODA5NTc1ZjI5ODczNGE0ZDFiIiwicHJvZmlsZU5hbWUiOiJUYWxvbkRldiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQwMTdhYmQ5ZjExZTlkZTM4ODBkNGM0OTAxODUzNTdiOGY4ZmY1NGM3MzA2Mzg2ZTgyYWQ1NjdhNTMwMzMifX19";
      public static String lobby$npc$play$solo$skin$signature =
          "i7k5tYkZ0CJ1hnGrGELLVXjIi0hfVVtg+c4a/iXP4wOwvAPj6tQtExFWgGaZYnYhN6ldcjJKUw13a/TRwHi4er4OceOlxBgqSvc0zzT7U4iZsEUuCwv7r9t6a+3MELqSQe3/bbX6WP6pDA9TRSVWaCTGpBtZfAYyrszk+VTowMjKrDB7r/kzrhE+h2rSozVcv4fUMGOd4m8xbTPlcvBatZ9OcHfZEpuoTpECUq3tWH3GIJi+Uxz3rTVl5rKJdKLOeUVXLpiLSgQ0jybMy705WlB0NWFbWFkY0mEQU7yca6keopEsGaQ+36yEtcE4hKYhibqW2sFhne/wIZh5arwyXVv/04twL/dpdiBwg4nqGEO60i+tQoF9RVWeCmIwJizEn3+WO6H2QogfCy+W1vNO65/HoHlhVbC6Y6nkUUQ8r0jtqz/sBQVAEBhFDjOQcdFucyjnO4LXrZPajdzJtBhkottBZDQZQlbFoZxC47WpQ+sktc51SWT2f3BzMowRKg08R8xpZxMTf+bB5OldilMuDPggXF/wVQU4+N9OFo1qYNxRPtM/7DCP8dtS7pwfhJkRhnQOfBVu7/mkNX1EM3mlMRzhEiUmqXfhL3SSyzTzqdTB76JgrRF92zuW+ouUlnXHe4hWiaWvRQ1XHB4fc+HOQ6/1RMYb4NItJFte1tjcQQs=";
      public static String lobby$npc$play$dupla$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1MjM1Njk3MjI0OTgsInByb2ZpbGVJZCI6IjdiM2QxNGQ2YzExZDRjODA5NTc1ZjI5ODczNGE0ZDFiIiwicHJvZmlsZU5hbWUiOiJUYWxvbkRldiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQwMTdhYmQ5ZjExZTlkZTM4ODBkNGM0OTAxODUzNTdiOGY4ZmY1NGM3MzA2Mzg2ZTgyYWQ1NjdhNTMwMzMifX19";
      public static String lobby$npc$play$dupla$skin$signature =
          "i7k5tYkZ0CJ1hnGrGELLVXjIi0hfVVtg+c4a/iXP4wOwvAPj6tQtExFWgGaZYnYhN6ldcjJKUw13a/TRwHi4er4OceOlxBgqSvc0zzT7U4iZsEUuCwv7r9t6a+3MELqSQe3/bbX6WP6pDA9TRSVWaCTGpBtZfAYyrszk+VTowMjKrDB7r/kzrhE+h2rSozVcv4fUMGOd4m8xbTPlcvBatZ9OcHfZEpuoTpECUq3tWH3GIJi+Uxz3rTVl5rKJdKLOeUVXLpiLSgQ0jybMy705WlB0NWFbWFkY0mEQU7yca6keopEsGaQ+36yEtcE4hKYhibqW2sFhne/wIZh5arwyXVv/04twL/dpdiBwg4nqGEO60i+tQoF9RVWeCmIwJizEn3+WO6H2QogfCy+W1vNO65/HoHlhVbC6Y6nkUUQ8r0jtqz/sBQVAEBhFDjOQcdFucyjnO4LXrZPajdzJtBhkottBZDQZQlbFoZxC47WpQ+sktc51SWT2f3BzMowRKg08R8xpZxMTf+bB5OldilMuDPggXF/wVQU4+N9OFo1qYNxRPtM/7DCP8dtS7pwfhJkRhnQOfBVu7/mkNX1EM3mlMRzhEiUmqXfhL3SSyzTzqdTB76JgrRF92zuW+ouUlnXHe4hWiaWvRQ1XHB4fc+HOQ6/1RMYb4NItJFte1tjcQQs=";
      public static String lobby$npc$play$ranked$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1MjM1Njk3MjI0OTgsInByb2ZpbGVJZCI6IjdiM2QxNGQ2YzExZDRjODA5NTc1ZjI5ODczNGE0ZDFiIiwicHJvZmlsZU5hbWUiOiJUYWxvbkRldiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQwMTdhYmQ5ZjExZTlkZTM4ODBkNGM0OTAxODUzNTdiOGY4ZmY1NGM3MzA2Mzg2ZTgyYWQ1NjdhNTMwMzMifX19";
      public static String lobby$npc$play$ranked$skin$signature =
          "i7k5tYkZ0CJ1hnGrGELLVXjIi0hfVVtg+c4a/iXP4wOwvAPj6tQtExFWgGaZYnYhN6ldcjJKUw13a/TRwHi4er4OceOlxBgqSvc0zzT7U4iZsEUuCwv7r9t6a+3MELqSQe3/bbX6WP6pDA9TRSVWaCTGpBtZfAYyrszk+VTowMjKrDB7r/kzrhE+h2rSozVcv4fUMGOd4m8xbTPlcvBatZ9OcHfZEpuoTpECUq3tWH3GIJi+Uxz3rTVl5rKJdKLOeUVXLpiLSgQ0jybMy705WlB0NWFbWFkY0mEQU7yca6keopEsGaQ+36yEtcE4hKYhibqW2sFhne/wIZh5arwyXVv/04twL/dpdiBwg4nqGEO60i+tQoF9RVWeCmIwJizEn3+WO6H2QogfCy+W1vNO65/HoHlhVbC6Y6nkUUQ8r0jtqz/sBQVAEBhFDjOQcdFucyjnO4LXrZPajdzJtBhkottBZDQZQlbFoZxC47WpQ+sktc51SWT2f3BzMowRKg08R8xpZxMTf+bB5OldilMuDPggXF/wVQU4+N9OFo1qYNxRPtM/7DCP8dtS7pwfhJkRhnQOfBVu7/mkNX1EM3mlMRzhEiUmqXfhL3SSyzTzqdTB76JgrRF92zuW+ouUlnXHe4hWiaWvRQ1XHB4fc+HOQ6/1RMYb4NItJFte1tjcQQs=";
      public static String ingame$broadcast$join = KCoreSettings.Ingame.BROADCAST_JOIN;
      public static String ingame$broadcast$leave = KCoreSettings.Ingame.BROADCAST_LEAVE;
      public static String ingame$broadcast$starting = KCoreSettings.Ingame.BROADCAST_STARTING;
      public static String ingame$broadcast$suicide = "{name} §emorreu sozinho.";
      public static String ingame$broadcast$default_killed_message = "{name} §efoi abatido por {killer}";
      public static String ingame$broadcast$double_kill = "§e. §e§lDOUBLE KILL";
      public static String ingame$broadcast$triple_kill = "§e. §b§lTRIPLE KILL";
      public static String ingame$broadcast$quadra_kill = "§e. §6§lQUADRA KILL";
      public static String ingame$broadcast$monster_kill = "§e. §c§lMOOONSTER KILL";
      public static String ingame$broadcast$end = " \n§aO tempo acabou, não houve ganhadores.\n ";
      public static String ingame$broadcast$win$solo = " \n{name} §avenceu a partida!\n ";
      public static String ingame$broadcast$win$dupla = " \n{name} §avenceram a partida!\n ";
      public static String ingame$actionbar$killed = "§aRestam §c{alive} §ajogadores!";
      public static String ingame$actionbar$kitselected = "§eKit selecionado: §a{kit}";
      public static String ingame$titles$end$header = "";
      public static String ingame$titles$end$footer = "§aRestam {time} minuto{s}";
      public static String ingame$titles$refill$header = "";
      public static String ingame$titles$refill$footer = "§aOs baús foram reabastecidos";
      public static String ingame$titles$border$header = "§c§lAVISO";
      public static String ingame$titles$border$footer = "§aVocê está saindo da borda!";
      public static String ingame$titles$death$header = KCoreSettings.Ingame.TITLE_DEATH_HEADER;
      public static String ingame$titles$death$footer = KCoreSettings.Ingame.TITLE_DEATH_FOOTER;
      public static String ingame$titles$win$header = KCoreSettings.Ingame.TITLE_WIN_HEADER;
      public static String ingame$titles$win$footer = "§7Você é o último de pé";
      public static String ingame$titles$lose$header = KCoreSettings.Ingame.TITLE_LOSE_HEADER;
      public static String ingame$titles$lose$footer = KCoreSettings.Ingame.TITLE_LOSE_FOOTER;
      public static String ingame$messages$bow$hit = KCoreSettings.Ingame.MESSAGE_BOW_HIT;
      public static String ingame$messages$coins$base = " \n  §a{coins} coins ganhos nesta partida:\n {coins_win}{coins_kills}\n ";
      public static String ingame$messages$coins$win = "\n       §a+{coins} §fpor vencer o jogo";
      public static String ingame$messages$coins$kills = "\n       §a+{coins} §fpor realizar §c{kills} §fabate{s}";
      public static String ingame$messages$points$base = "\n  \n §a{points} pontos ganhos nesta partida:\n {points_win}{points_kills}\n ";
      public static String ingame$messages$points$win = "\n       §a+{points} §fpor vencer o jogo";
      public static String ingame$messages$points$kills = "\n       §a+{points} §fpor realizar §c{kills} §fabate{s}";
  }

  public static final class Murder {

      public static int options$coins$wins = 50;
      public static int options$start$waiting = 45;
      public static int options$start$full = 10;
      public static int options$ingame$time = 270;
    
      public static long scoreboards$scroller$every_tick = KCoreSettings.Scoreboard.SCROLLER_EVERY_TICK;
      public static List<String> scoreboards$scroller$titles = Arrays
          .asList("§a§lMURDER", "§f§l§6§lM§a§lURDER", "§f§lM§6§lU§a§lRDER", "§f§lMU§6§lR§a§lDER",
              "§f§lMUR§6§lD§a§lER", "§f§lMURD§6§lE§a§lR", "§f§lMURDE§6§lR", "§f§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER",
              "§f§lMURDER", "§f§lMURDER", "§f§lMURDER", "§f§lMURDER", "§f§lMURDER", "§f§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER",
              "§f§lMURDER", "§f§lMURDER", "§f§lMURDER", "§f§lMURDER", "§f§lMURDER", "§f§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER",
              "§a§lMURDER", "§a§lMURDER", "§a§lMURDER", "§a§lMURDER");
      public static String scoreboards$time$waiting = KCoreSettings.Scoreboard.TIME_WAITING;
      public static String scoreboards$time$starting = KCoreSettings.Scoreboard.TIME_STARTING;
      public static List<String> scoreboards$lobby = Arrays
          .asList("", " Abates: §a%kCore_Murder_classic_kills%",
              " Vitórias: §a%kCore_Murder_classic_wins%", "", " §eVitórias sendo:",
              "  Detetive: §a%kCore_Murder_classic_detectivewins%",
              "  Assassino: §a%kCore_Murder_classic_killerwins%", "", " Cash: §b%kCore_cash%",
              " Coins: §6%kCore_Murder_coins%", "", " " + KCoreSettings.Network.websiteLine(), "");
      public static List<String> scoreboards$waiting =
              Arrays.asList("", "  Mapa: §a{map}", "  Jogadores: §a{players}/{max_players}",
                              "", "  {time}", "", "  " + KCoreSettings.Network.websiteLine(), "");
      public static List<String> scoreboards$classic = Arrays
          .asList("", " Função: {role}", "", " Inocentes vivos: §a{innocents}",
              " Tempo restante: §a{timeLeft}", "", " Detetive: {detective}", "", " Mapa: §a{map}", "",
              " " + KCoreSettings.Network.websiteLine(), "");
      public static List<String> scoreboards$assassins = Arrays
          .asList("", " Alvo: {bounty}", "", " Vivos: §a{players}", " Tempo restante: §a{timeLeft}", "",
              " Abates: §c{kills}", "", " Mapa: §a{map}", "", " " + KCoreSettings.Network.websiteLine(), "");
    
      public static String cosmetics$color$locked = KCoreSettings.Cosmetics.COLOR_LOCKED;
      public static String cosmetics$color$canbuy = KCoreSettings.Cosmetics.COLOR_CANBUY;
      public static String cosmetics$color$unlocked = KCoreSettings.Cosmetics.COLOR_UNLOCKED;
      public static String cosmetics$color$selected = KCoreSettings.Cosmetics.COLOR_SELECTED;
    
      public static String cosmetics$icon$perm_desc$common = KCoreSettings.Cosmetics.ICON_PERM_COMMON;
      public static String cosmetics$icon$perm_desc$role = KCoreSettings.Cosmetics.ICON_PERM_ROLE;
      public static String cosmetics$icon$buy_desc$enough = KCoreSettings.Cosmetics.ICON_BUY_NOT_ENOUGH;
      public static String cosmetics$icon$buy_desc$click_to_buy = KCoreSettings.Cosmetics.ICON_BUY_CLICK;
      public static String cosmetics$icon$has_desc$select = KCoreSettings.Cosmetics.ICON_HAS_SELECT;
      public static String cosmetics$icon$has_desc$selected = KCoreSettings.Cosmetics.ICON_HAS_DESELECT;
    
      public static String cosmetics$deathmessage$icon$perm_desc$start =
              "\n§6Clique direito para ver!\n \n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathmessage$icon$buy_desc$start =
              "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$deathmessage$icon$has_desc$start =
              "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$knife$icon$perm_desc$start =
          "§7Troca a aparência da Faca\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$knife$icon$buy_desc$start =
          "§7Troca a aparência da Faca\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$knife$icon$has_desc$start =
          "§7Troca a aparência da Faca\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$hat$icon$perm_desc$start =
              "§7Troca a aparência do Chapéu\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$hat$icon$buy_desc$start =
              "§7Troca a aparência do Chapéu\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$hat$icon$has_desc$start =
              "§7Troca a aparência do Chapéu\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$win_animation$icon$perm_desc$start = "\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$win_animation$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$win_animation$icon$has_desc$start = "\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String cosmetics$deathcry$icon$perm_desc$start =
          "§7Toca o grito de morte {name}\n§7quando você morre.\n \n§6Clique direito para escutar!\n  \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathcry$icon$buy_desc$start =
          "§7Toca o grito de morte {name}\n§7quando você morre.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n§6Clique direito para escutar!\n \n{buy_desc_status}";
      public static String cosmetics$deathcry$icon$has_desc$start =
          "§7Toca o grito de morte {name}\n§7quando você morre.\n \n§6Clique direito para escutar!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
    
      public static String chat$delay = KCoreSettings.Chat.DELAY;
      public static String chat$color$default = KCoreSettings.Chat.COLOR_DEFAULT;
      public static String chat$color$custom = KCoreSettings.Chat.COLOR_CUSTOM;
      public static String chat$format$lobby = KCoreSettings.Chat.FORMAT_LOBBY;
      public static String chat$format$spectator = KCoreSettings.Chat.FORMAT_SPECTATOR;
    
      public static String lobby$achievement = KCoreSettings.Lobby.ACHIEVEMENT;
      public static String lobby$broadcast = KCoreSettings.Lobby.BROADCAST;
    
      public static boolean lobby$tab$enabled = KCoreSettings.Tab.ENABLED;
      public static String lobby$tab$header = KCoreSettings.Tab.header();
      public static String lobby$tab$footer = KCoreSettings.Tab.footer();
    
      public static long lobby$leaderboard$minutes = KCoreSettings.Lobby.LEADERBOARD_MINUTES;
      public static String lobby$leaderboard$empty = KCoreSettings.Lobby.LEADERBOARD_EMPTY;
      public static List<String> lobby$leaderboard$wins_as_detective$hologram = Arrays
          .asList("§a10. {name_10} §7- §a{stats_10}", "§a9. {name_9} §7- §a{stats_9}",
              "§a8. {name_8} §7- §a{stats_8}", "§a7. {name_7} §7- §a{stats_7}",
              "§a6. {name_6} §7- §a{stats_6}",
              "§a5. {name_5} §7- §a{stats_5}", "§a4. {name_4} §7- §a{stats_4}",
              "§a3. {name_3} §7- §a{stats_3}", "§a2. {name_2} §7- §a{stats_2}",
              "§a1. {name_1} §7- §a{stats_1}", "",
              "§7Ranking de Vitórias", "§f§lMelhores Detetives");
      public static List<String> lobby$leaderboard$wins_as_murder$hologram = Arrays
          .asList("§a10. {name_10} §7- §a{stats_10}", "§a9. {name_9} §7- §a{stats_9}",
              "§a8. {name_8} §7- §a{stats_8}", "§a7. {name_7} §7- §a{stats_7}",
              "§a6. {name_6} §7- §a{stats_6}",
              "§a5. {name_5} §7- §a{stats_5}", "§a4. {name_4} §7- §a{stats_4}",
              "§a3. {name_3} §7- §a{stats_3}", "§a2. {name_2} §7- §a{stats_2}",
              "§a1. {name_1} §7- §a{stats_1}", "",
              "§7Ranking de Vitórias", "§f§lMelhores Assassinos");
    
      public static String lobby$npc$play$connect = KCoreSettings.Lobby.NPC_PLAY_CONNECT;
    
      public static String lobby$npc$play$menu$info$item = KCoreSettings.Lobby.NPC_PLAY_INFO_ITEM;
      public static String lobby$npc$play$menu$info$desc_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_VIP_DESC;
      public static String lobby$npc$play$menu$info$desc_not_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_NO_LIMIT;
    
      public static String lobby$npc$deliveries$deliveries = KCoreSettings.Lobby.NPC_DELIVERIES_LABEL;
      public static List<String> lobby$npc$deliveries$hologram = KCoreSettings.Lobby.NPC_DELIVERIES_HOLOGRAM;
      public static List<String> lobby$npc$stats$hologram = Arrays
              .asList("§6Estatísticas", "Total de Eliminações: §7%kCore_Murder_classic_kills%",
                      "Total de Vitórias: §7%kCore_Murder_classic_wins%", "§e§lCLIQUE DIREITO");
      public static List<String> lobby$npc$play$classic$hologram = Arrays
          .asList("§bClássico", "§a{players} Jogadores");
      public static List<String> lobby$npc$play$assassins$hologram = Arrays
          .asList("§bAssassinos", "§a{players} Jogadores");
    
      public static String lobby$npc$deliveries$skin$value = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_VALUE;
      public static String lobby$npc$deliveries$skin$signature = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_SIGNATURE;
      public static String lobby$npc$play$classic$skin$value =
          "ewogICJ0aW1lc3RhbXAiIDogMTU5MzcxOTkwNjkxMSwKICAicHJvZmlsZUlkIiA6ICJmZDYwZjM2ZjU4NjE0ZjEyYjNjZDQ3YzJkODU1Mjk5YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWFkIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzUyMjg3ZmQ5MDhkMjlhYjMwMDQyNjRjODk4ZGIwMDY1MjkyZDdiOGU5NmJlMzY2YmMwZWIwYzM5ZDMxNjY1NWEiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
      public static String lobby$npc$play$classic$skin$signature =
          "OKYGdZOZwR+4Q1bfzb1enXXUIT0Ru4WxadpXZoR2R/o8OJobBJAO205MuwHj4dNQNRE0L/ggPoE/iWvQXn3elcRdnJE4I4oVwb6DVZX1KLKtP1v2RM3QLcvZ6I2ogNa/cIln2BuxfV1u+wR39RahzrsbwWFz/U6MTaXw2dYIZmTkllHOkrLrRdeqAjJh9EsBizKcoWDCzdqXgOxOl0j+0JzG6InA7wgvf98IHZcZga0ihWebGDV3/TD4gocML1oTvrLW0ecPjOO1hCoKlk5niFobT6fJWR7XM0feKJsNdPlTOqAY0jlzm/w/hBW1gbTVDtK/lO2K6IfIijq2MIUnGF4uIkhQ3U9quVWTbK9/KqoJYKdx9Lkta+NOUkx5zT72Pske5O/taQsVYHST4ALicfGmvV7P1ohczHsibvguqzs0+sVgr0pb2jXiYJTRSgBvr2/X6esoBIy5DgvAbH9XM1adVqIj1zIhq1Q+YQ2iCV++eoUZ56uyy2sHlnzMA/Jj6+vODDKvpnYTXIyJ/qnWmxfYmFbf0Zd95A85rGhTfjvwP3DOcN3GZdwIe09ELSumDjHTlqv7MV+yLP+DKIcOqhQm4SQE7SlACW4Yw3r5UyiLfovpmTg8SBr/WwGJs8AhIY4LuF7PtPVNhs/Xe9cfH8KVUbd4PxOeLyNPq7+ibt8=";
      public static String lobby$npc$play$assassins$skin$value =
          "ewogICJ0aW1lc3RhbXAiIDogMTYwMDU1MjU2NTk4NywKICAicHJvZmlsZUlkIiA6ICI1NjY3NWIyMjMyZjA0ZWUwODkxNzllOWM5MjA2Y2ZlOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVJbmRyYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81YmNmNmRjYTAwZDgzZjQxZGU1ZWU0ZmUxYTg3ZDU5NTQwNzI5OTgxZTRmOWMyNDA4YzA1Nzc2ODdlMjVmODE5IgogICAgfQogIH0KfQ==";
      public static String lobby$npc$play$assassins$skin$signature =
          "aJ7/qBVQm0UKiFDENFBNiWnsim+bS6SXQACBBQ11j5cm/gYe1qU/SeuAFe9G5JPl2eKKrb9SspL5nHlvV2TFNU0+NmZLi+6feBFAdiifhtGphTUCYKQIvqLOJWvEe4xSu1qBnec9fHM7nkxB1P+KppoWHyEHZT5q4s1czpI5ATO9+M4qBzWghS8232FAaklvNs9qNLZfAoL9MEa3QO4LuX5fXIt5GnWHr7FDyNUPcUgNTNVNvKphsCfMwLLNOPItEbB9c9PzUMtXigBffK6eF9aPc6NqAwU80pBmqClI5XKyob4MPyIXT3i/iTMdhbmaKU3vId7vxdImL9jwsWLN+EtNEQZb7F6UFZYDNQbCCenb+nESvDi883iLNCjhHpXxw/OvkAFlPufamzSHzDT3+AJMA3m1gBpDnL/EzOCgk9LlweLjrovd5Nh4okTem09ZX2VBtQyc4i/SLmW20+JD7gb16rLRQAixGQAh7r1co0rWlNBHkUyh9LtcCHHKLRaSDsGhKGkTGAneTioN778QK5Q9e5u3hSPGprNK7yrvfHxx9eNMXWfKfJfsPBOwCy8riJlPRQY7CPZIVfuK7BhLMt/QWtWCN09QVoE9qWsETrpRDP5HZFDWDARwKPO0zg9DwK1BB1ZO8TJUDZUzslpQImH9V5gihB4xHQlVOqW0bHw=";
    
      public static String ingame$contract_updated = "§aSeu contrato de assassinato foi atualizado!";
      public static String ingame$broadcast$join = KCoreSettings.Ingame.BROADCAST_JOIN;
      public static String ingame$broadcast$leave = KCoreSettings.Ingame.BROADCAST_LEAVE;
      public static String ingame$broadcast$starting = KCoreSettings.Ingame.BROADCAST_STARTING;
      public static String ingame$broadcast$default_killed_message = "{name} §efoi abatido por {killer}";
      public static String ingame$broadcast$knife = "§aO §cAssassino §areceberá a faca em {time} segundo{s}.";
      public static String ingame$broadcast$contract = "§aOs §cContratos de Assassinato §aserão liberados em {time} segundo{s}.";
      public static String ingame$broadcast$knife_received = "§aO §cAssassino §arecebeu a faca.";
      public static String ingame$broadcast$detective_died = "§6O Detetive morreu! §aRecupere o arco para ter uma chance de acabar com o assassino.";
  }

  public static final class TheBridge {
    
      public static int options$coins$wins = 50;
      public static int options$coins$kills = 5;
      public static int options$coins$score = 10;
      public static long scoreboards$scroller$every_tick = KCoreSettings.Scoreboard.SCROLLER_EVERY_TICK;
      public static List<String> scoreboards$scroller$titles = Arrays
          .asList("§a§lTHE BRIDGE", "§f§l§6§lT§a§lHE BRIDGE", "§f§lT§6§lH§a§lE BRIDGE",
              "§f§lTH§6§lE §a§lBRIDGE", "§f§lTHE §6§lB§a§lRIDGE", "§f§lTHE B§6§lR§a§lIDGE",
              "§f§lTHE BR§6§lI§a§lDGE", "§f§lTHE BRI§6§lD§a§lGE", "§f§lTHE BRID§6§lG§a§lE",
              "§f§lTHE BRIDG§6§lE", "§f§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§f§lTHE BRIDGE", "§f§lTHE BRIDGE",
              "§f§lTHE BRIDGE", "§f§lTHE BRIDGE", "§f§lTHE BRIDGE", "§f§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§f§lTHE BRIDGE", "§f§lTHE BRIDGE", "§f§lTHE BRIDGE",
              "§f§lTHE BRIDGE", "§f§lTHE BRIDGE", "§f§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE",
              "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE", "§a§lTHE BRIDGE");
      public static String scoreboards$time$waiting = KCoreSettings.Scoreboard.TIME_WAITING;
      public static String scoreboards$time$starting = KCoreSettings.Scoreboard.TIME_STARTING;
      public static List<String> scoreboards$lobby = Arrays
          .asList("", "  Vitórias: §a%kCore_TheBridge_wins%", "  Abates: §a%kCore_TheBridge_kills%",
              "  Pontos: §a%kCore_TheBridge_points%", "  Winstreak: §a%kCore_TheBridge_winstreak%", "",
              "  Cash: §b%kCore_cash%", "  Coins: §6%kCore_TheBridge_coins%", "", "  " + KCoreSettings.Network.websiteLine(), "");
      public static List<String> scoreboards$waiting =
          Arrays
              .asList("", "  Mapa: §a{map}", "  Modo: §a{mode}", "", "  Jogadores: §a{players}/{max_players}",
                  "", "  {time}", "", "  " + KCoreSettings.Network.websiteLine(), "");
      public static List<String> scoreboards$ingame = Arrays
          .asList("", "  §c§lV §fVermelho §7(§a{red}/5§7)", "  §9§lA §fAzul §7(§a{blue}/5§7)", "",
              "  Abates: §a{kills}", "  Pontos: §a{points}", "", "  Modo: §a{mode}", "  Mapa: §a{map}", "",
              "  " + KCoreSettings.Network.websiteLine(), "");
      public static String cosmetics$color$locked = KCoreSettings.Cosmetics.COLOR_LOCKED;
      public static String cosmetics$color$canbuy = KCoreSettings.Cosmetics.COLOR_CANBUY;
      public static String cosmetics$color$unlocked = KCoreSettings.Cosmetics.COLOR_UNLOCKED;
      public static String cosmetics$color$selected = KCoreSettings.Cosmetics.COLOR_SELECTED;
      public static String cosmetics$icon$perm_desc$common = KCoreSettings.Cosmetics.ICON_PERM_COMMON;
      public static String cosmetics$icon$perm_desc$role = KCoreSettings.Cosmetics.ICON_PERM_ROLE;
      public static String cosmetics$icon$buy_desc$enough = KCoreSettings.Cosmetics.ICON_BUY_NOT_ENOUGH;
      public static String cosmetics$icon$buy_desc$click_to_buy = KCoreSettings.Cosmetics.ICON_BUY_CLICK;
      public static String cosmetics$icon$has_desc$select = KCoreSettings.Cosmetics.ICON_HAS_SELECT;
      public static String cosmetics$icon$has_desc$selected = KCoreSettings.Cosmetics.ICON_HAS_DESELECT;
      public static String cosmetics$perk$icon$perm_desc$start = "\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$perk$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$perk$icon$has_desc$start = "\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$kill_effect$icon$perm_desc$start = "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$kill_effect$icon$buy_desc$start =
          "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$kill_effect$icon$has_desc$start = "\n \n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$deathcry$icon$perm_desc$start =
          "§7Toca o grito de morte {name}\n§7quando você morre.\n \n§6Clique direito para escutar!\n  \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathcry$icon$buy_desc$start =
          "§7Toca o grito de morte {name}\n§7quando você morre.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n§6Clique direito para escutar!\n \n{buy_desc_status}";
      public static String cosmetics$deathcry$icon$has_desc$start =
          "§7Toca o grito de morte {name}\n§7quando você morre.\n \n§6Clique direito para escutar!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$block$icon$perm_desc$start = "§7Altera seu bloco de construção\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$block$icon$buy_desc$start =
          "§7Altera seu bloco de construção\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$block$icon$has_desc$start = "§7Altera seu bloco de construção\n§7para {name}.\n \n§fRaridade: {rarity}\n  \n{has_desc_status}";
      public static String cosmetics$deathmessage$icon$perm_desc$start =
          "\n§6Clique direito para ver!\n \n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$deathmessage$icon$buy_desc$start =
          "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$deathmessage$icon$has_desc$start =
          "\n§6Clique direito para ver!\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$balloon$icon$perm_desc$start = "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$balloon$icon$buy_desc$start =
          "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$balloon$icon$has_desc$start = "§7Altera o balão decorativo da ilha\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String cosmetics$win_animation$icon$perm_desc$start = "\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$win_animation$icon$buy_desc$start = "\n \n§fRaridade: {rarity}\n§fCusto: §6{coins} Coins §7ou §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$win_animation$icon$has_desc$start = "\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
      public static String chat$delay = KCoreSettings.Chat.DELAY;
      public static String chat$color$default = KCoreSettings.Chat.COLOR_DEFAULT;
      public static String chat$color$custom = KCoreSettings.Chat.COLOR_CUSTOM;
      public static String chat$format$lobby = KCoreSettings.Chat.FORMAT_LOBBY;
      public static String chat$format$spectator = KCoreSettings.Chat.FORMAT_SPECTATOR;
      public static String lobby$achievement = KCoreSettings.Lobby.ACHIEVEMENT;
      public static String lobby$broadcast = KCoreSettings.Lobby.BROADCAST;
      public static boolean lobby$tab$enabled = KCoreSettings.Tab.ENABLED;
      public static String lobby$tab$header = KCoreSettings.Tab.header();
      public static String lobby$tab$footer = KCoreSettings.Tab.footer();
      public static long lobby$leaderboard$minutes = KCoreSettings.Lobby.LEADERBOARD_MINUTES;
      public static String lobby$leaderboard$empty = KCoreSettings.Lobby.LEADERBOARD_EMPTY;
      public static List<String> lobby$leaderboard$wins$holograms =
          KCoreSettings.Lobby.leaderboardHologram("§7Ranking de Vitórias");
      public static List<String> lobby$leaderboard$kills$holograms =
          KCoreSettings.Lobby.leaderboardHologram("§7Ranking de Abates");
      public static List<String> lobby$leaderboard$points$holograms =
          KCoreSettings.Lobby.leaderboardHologram("§7Ranking de Pontuação");
      public static List<String> lobby$leaderboard$daily_winstreak$holograms = Arrays
          .asList("§a10. {name_10} §7- §a{stats_10}", "§a9. {name_9} §7- §a{stats_9}",
              "§a8. {name_8} §7- §a{stats_8}", "§a7. {name_7} §7- §a{stats_7}",
              "§a6. {name_6} §7- §a{stats_6}",
              "§a5. {name_5} §7- §a{stats_5}", "§a4. {name_4} §7- §a{stats_4}",
              "§a3. {name_3} §7- §a{stats_3}", "§a2. {name_2} §7- §a{stats_2}",
              "§a1. {name_1} §7- §a{stats_1}", "",
              "§7Winstreak Diário", "§f§lTodos os Modos");
      public static String lobby$npc$play$connect = KCoreSettings.Lobby.NPC_PLAY_CONNECT;
      public static String lobby$npc$play$menu$info$item = KCoreSettings.Lobby.NPC_PLAY_INFO_ITEM;
      public static String lobby$npc$play$menu$info$desc_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_VIP_DESC;
      public static String lobby$npc$play$menu$info$desc_not_limit = KCoreSettings.Lobby.NPC_MAP_SELECT_NO_LIMIT;
      public static String lobby$npc$deliveries$deliveries = KCoreSettings.Lobby.NPC_DELIVERIES_LABEL;
      public static List<String> lobby$npc$deliveries$hologram = KCoreSettings.Lobby.NPC_DELIVERIES_HOLOGRAM;
      public static List<String> lobby$npc$stats$hologram = Arrays
          .asList("§6Estatísticas", "Total de Eliminações: §7%kCore_TheBridge_kills%", "Total de Vitórias: §7%kCore_TheBridge_wins%", "§e§lCLIQUE DIREITO");
      public static List<String> lobby$npc$play$solo$hologram = Arrays
          .asList("§b1v1", "§a{players} Jogadores");
      public static List<String> lobby$npc$play$dupla$hologram = Arrays
          .asList("§b2v2", "§a{players} Jogadores");
      public static String lobby$npc$deliveries$skin$value = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_VALUE;
      public static String lobby$npc$deliveries$skin$signature = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_SIGNATURE;
      public static String lobby$npc$play$solo$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1ODQyNDcwOTU5MDksInByb2ZpbGVJZCI6Ijc0NDE2MjQxNjAzZTRkYzRiYTVmZjU2NGE5YjIwNjZiIiwicHJvZmlsZU5hbWUiOiJUYWtpUGFuMzU0MiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRiZTQ3NDhlZTY4N2MxMDRkYTBlMzUxMzkxNDFlMWRlNTU4ZGQ1MDBmYTNhYWMyZjZhNWFkNDFjNTI0MmFjZSJ9fX0=";
      public static String lobby$npc$play$solo$skin$signature =
          "h7oIZHFsV4xTpzegVKb0am7DSps6oDu/HkUaP87ptuABdAjECT//eKIXJt8RRYNWUJhnpGOAycxv6KETZuezfBA7yGKnP+IEyPJXMqM0KMRlMFYIoLyDnjqoeScjgVzNV0/amXF+IZrzIB/c0htsM3CdBGDsJqZBn4M7FRijlDxMrdrOJOVnx1ZA5PB07sxcg+svCqrEHMYXrIzFPRZaZqOiszV/L6g4nLijSYl8gyM3AgV7NDTV4vQddGgQMy8PRxoMzF7Wr9QMgJuCGzHh+9mJKXyCWrVk3exf6mK8miDdQim+Y+iAz+7dEbj2LHR8ij9RmAMQgtKfn6zQMQowo0rdoWLkBYj6Owr8R8+f7OsWMBzop2qXzfkaO2FBgBIHaBVlGt/h5qCYPeLsyF6PgjDLxsmmZo85necDKpJWtQf196psLD3hI6eJU6ycFjJ9HEJKXiC7gkAnvOajOjG4ozYKQxGBU8oW+2MCgE9zmNGh4Om6FQzwc4x6hsu9uNKy0HDbpen29VAm2gQAtAovbjlNrz3djTaVXHIO44I7GDLr3uphvYr/Lf1/pj3zU0OqQLPdnDhQJF8+5kvACyolzPAT1xwt1Kl4M+6BMvxKCs3ZZqBH9+cH4UQza43nJVdSTJUX6BhW34zmiEbIBVzZmIBDZXRHzKpx4EsqEgMPGPA=";
      public static String lobby$npc$play$dupla$skin$value =
          "eyJ0aW1lc3RhbXAiOjE1ODQyNDcyNDQwMTksInByb2ZpbGVJZCI6IjUyZTZlMWI1YTczYzQ1OWRiZmIwOGE0MjEyNTVjZWEwIiwicHJvZmlsZU5hbWUiOiJCdWlsZF9WaWxsYWluIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNDMyYTEzMWQxNWI1M2UzYTUyMDU5YjM1ZmIzNDMyMjUxZWViYWM4ZjZmZGE5ZDIyYWEwM2IxYTliNGE4MTBhIn19fQ==";
      public static String lobby$npc$play$dupla$skin$signature =
          "omvzBV7bhA9KuwbGC9qH5pCu1xmN4Pw5bJpmF11WT40R3y4285Jf2PI4IjlUmZy4J92+Ot9+VrVdMXoaR4AD8i9EL07OwneSqBzAB/t/qeLI3/mtXeUp7bzQs6F7xgoGByMvcq1chQlwPvLAVmRuwCGIuBoSyf6XOfAme2RiS3YMi0hmUrYPRWL7SvcExB/U8tACJ8xO+Ts7xvag7VtY+RWj3xVC+imZ1rSeXJ26QBk/pDxC2ni5CinC6M/iNHxTQ12TOP/TXMcLK1ms0pttOQbIJ5RDDes65jckLBCkrf8zjabjN7NzdIGgaRUj2dDbDT3DfMiJafWb9shsh7iSCRUni0DO2oHzCXZjCATnBXvoGN5+ym9aryywErgL3GfcEdT50ankUhxXLEfxSEHsqu3d9/HLF5V5m9eSgQi0Cpnb5q9eZ90nSNt2TEYRrjV7o/eaoaJyfCtzIqkKnfBZ0TqqAF4yBer/M7cdo0JrOXzhMlRkAdK2nWNgaBsOAnpx+XvfWW5Hk+zq+Gjsg6IKXPJBoulWuEsDlcO7ZwObleXl9eU/Z8mKZMdn9qVx37zz1aE/ygwP+ost7Rzn+MA3nMiBzmy5SSUU0PDgUix3OUozosyO8ANq+qbq/eQ28ToPKtb0Z8IThHycdwoTeCwPCzZydmVBg0nHgGIQSxsWMF4=";
      public static String ingame$broadcast$join = KCoreSettings.Ingame.BROADCAST_JOIN;
      public static String ingame$broadcast$leave = KCoreSettings.Ingame.BROADCAST_LEAVE;
      public static String ingame$broadcast$starting = KCoreSettings.Ingame.BROADCAST_STARTING;
      public static String ingame$broadcast$suicide = "{name} §amorreu sozinho.";
      public static String ingame$broadcast$killed = "{name} §afoi abatido por {killer}";
      public static String ingame$broadcast$point = "{name} §7marcou um ponto!";
      public static String ingame$broadcast$win$solo = " \n{name} §avenceu a partida!\n ";
      public static String ingame$broadcast$win$dupla = " \n{name} §avenceram a partida!\n ";
      public static String ingame$titles$cage$header = "{time}";
      public static String ingame$titles$cage$footer = "§7Liberando jogadores...";
      public static String ingame$titles$win$header = KCoreSettings.Ingame.TITLE_WIN_HEADER;
      public static String ingame$titles$win$footer = "§fVocê venceu";
      public static String ingame$titles$lose$header = KCoreSettings.Ingame.TITLE_LOSE_HEADER;
      public static String ingame$titles$lose$footer = KCoreSettings.Ingame.TITLE_LOSE_FOOTER;
      public static String ingame$messages$bow$hit = KCoreSettings.Ingame.MESSAGE_BOW_HIT;
      public static String ingame$messages$coins$base = " \n  §a{coins} coins ganhos nesta partida:\n {coins_win}{coins_points}{coins_kills}\n ";
      public static String ingame$messages$coins$win = "\n       §a+{coins} §fpor vencer o jogo";
      public static String ingame$messages$coins$points = "\n       §a+{coins} §fpor pontuar §a{points} §fvezes";
      public static String ingame$messages$coins$kills = "\n       §a+{coins} §fpor realizar §c{kills} §fabates";
  }

  public static final class LobbyPlugin {
    
      public static long scoreboards$scroller$every_tick = KCoreSettings.Scoreboard.SCROLLER_EVERY_TICK;
      public static List<String> scoreboards$scroller$titles = Arrays.asList(
          KCoreSettings.Network.coloredName(), "§f§l§6§lR§a§lEDE SKY", "§f§lR§6§lE§a§lDE SKY",
          "§f§lRE§6§lD§a§lE SKY", "§f§lRED§6§lE §a§lSKY", "§f§lREDE §6§lS§a§lSKY",
          "§f§lREDE S§6§lL§a§lKY", "§f§lREDE SK§6§lY", KCoreSettings.Network.coloredName(),
          KCoreSettings.Network.coloredName(), KCoreSettings.Network.coloredName());
      public static List<String> scoreboards$lobby = Arrays.asList(
          "", "  Grupo: §a%kCore_role%", "  Cash: §b%kCore_cash%", "", "  Jogadores: §a%kCore_online%",
          "", "  " + KCoreSettings.Network.websiteLine(), "");
      public static String chat$delay = KCoreSettings.Chat.DELAY;
      public static String chat$color$default = KCoreSettings.Chat.COLOR_DEFAULT;
      public static String chat$color$custom = KCoreSettings.Chat.COLOR_CUSTOM;
      public static String chat$format$lobby = KCoreSettings.Chat.FORMAT_LOBBY;
      public static String lobby$broadcast = KCoreSettings.Lobby.BROADCAST;
      public static boolean lobby$tab$enabled = KCoreSettings.Tab.ENABLED;
      public static String lobby$tab$header = KCoreSettings.Tab.header();
      public static String lobby$tab$footer = KCoreSettings.Tab.footer();
      public static String lobby$npc$deliveries$deliveries = KCoreSettings.Lobby.NPC_DELIVERIES_LABEL;
      public static List<String> lobby$npc$deliveries$hologram = KCoreSettings.Lobby.NPC_DELIVERIES_HOLOGRAM;
      public static String lobby$npc$deliveries$skin$value = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_VALUE;
      public static String lobby$npc$deliveries$skin$signature = KCoreSettings.Lobby.NPC_DELIVERIES_SKIN_SIGNATURE;
  }

  public static final class Collectibles {
    
      public static List<String> settings$mundos = Collections.singletonList("world");
      public static boolean settings$item$usar = true;
      public static boolean settings$command$cosmetics = true;
      public static int settings$coins$duplicated = 500;
      public static int settings$item$slot = 4;
      public static int settings$gadget$slot = 5;
      public static String settings$item$stack = "RAW_FISH:3 : 1 : nome>&eCosméticos";
  }

  public static final class MysteryBox {
    
      public static List<String> lobby$mysterybox$holograms = Arrays.asList(
          "{boxes}", "§bCápsula Mágica", "§eClique para abrir!");
      public static String lobby$mysterybox$mysteryboxes = "§e§l{boxes} CAIXA{s}";
      public static String lobby$mysterybox$mysteryboxes_replace = "S";
      public static String menus$boxes$info =
          "BOOK : 1 : nome>&aInformações : desc>&fFragmentos Misteriosos: &7{frags}\n&fCápsulas Mágicas: &7{boxes}\n \n"
              + KCoreSettings.Network.storeMenuLine().replace("§", "&")
              + "\n \n&aSeus últimos 5 itens encontrados: {last_rewards}";
      public static String menus$boxes$no_have_boxes =
          "STAINED_GLASS_PANE:14 : 1 : nome>&cOps, você não possui Cápsulas Mágicas. : desc>&bCápsulas Mágicas &7podem ser adquiridas utilizando Cash.\n \n&fAdquira Cash acessando\n&e"
              + KCoreSettings.Network.STORE;
      public static String menus$boxes$open_multiples_boxes =
          "NETHER_STAR : 1 : nome>&aAbrir Múltiplas Cápsulas Mágicas : desc>&7Clique aqui para abrir várias\n&bCápsulas Mágicas&7 ao mesmo tempo\n \n{open_desc}";
      public static String menus$boxes$fabricate_box = "ANVIL : 1 : nome>&aFabricar Cápsulas Mágicas : desc>&7Ao receber itens repitidos em uma cápsula, você\n&7recebe &bFragmentos Misteriosos &7que podem ser\n&7usados para fabricar cápsulas.\n \n&eClique para fabricar!";
    
      public static String cosmetics$color$locked = KCoreSettings.Cosmetics.COLOR_LOCKED;
      public static String cosmetics$color$canbuy = KCoreSettings.Cosmetics.COLOR_CANBUY;
      public static String cosmetics$color$unlocked = KCoreSettings.Cosmetics.COLOR_UNLOCKED;
      public static String cosmetics$color$selected = KCoreSettings.Cosmetics.COLOR_SELECTED;
    
      public static String cosmetics$icon$perm_desc$common = KCoreSettings.Cosmetics.ICON_PERM_COMMON;
      public static String cosmetics$icon$perm_desc$role = KCoreSettings.Cosmetics.ICON_PERM_ROLE;
      public static String cosmetics$icon$buy_desc$enough = KCoreSettings.Cosmetics.ICON_BUY_NOT_ENOUGH;
      public static String cosmetics$icon$buy_desc$click_to_buy = KCoreSettings.Cosmetics.ICON_BUY_CLICK;
      public static String cosmetics$icon$has_desc$select = KCoreSettings.Cosmetics.ICON_HAS_SELECT;
      public static String cosmetics$icon$has_desc$selected = KCoreSettings.Cosmetics.ICON_HAS_DESELECT;
    
      public static String cosmetics$opener$icon$perm_desc$start =
          "§7Troca a animação da Cápsula\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{perm_desc_status}";
      public static String cosmetics$opener$icon$buy_desc$start =
          "§7Troca a animação da Cápsula\n§7para {name}.\n \n§fRaridade: {rarity}\n§fCusto: §b{cash} Cash\n \n{buy_desc_status}";
      public static String cosmetics$opener$icon$has_desc$start =
          "§7Troca a animação da Cápsula\n§7para {name}.\n \n§fRaridade: {rarity}\n \n{has_desc_status}";
  }
}
