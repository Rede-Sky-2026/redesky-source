package dev.slickcollections.kiwizin.bungee.cmd;

import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.player.role.RolePermissionManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

public class RankCommand extends Commands {

  public RankCommand() {
    super("rank", "cargo", "grupo");
  }

  @Override
  public void perform(CommandSender sender, String[] args) {
    String targetName = args.length > 0 ? args[0] : sender.getName();
    if (!targetName.equalsIgnoreCase(sender.getName()) && !sender.hasPermission("kcore.cmd.setrank")) {
      sender.sendMessage(TextComponent.fromLegacyText("§cVocê não possui permissão para ver o cargo de outros jogadores."));
      return;
    }

    Role role = RolePermissionManager.getAssignedRole(targetName);
    List<String> rolePermissions = RolePermissionManager.listRolePermissions(role);
    List<String> playerPermissions = RolePermissionManager.listPlayerPermissions(targetName);

    sender.sendMessage(TextComponent.fromLegacyText("§eCargo de §f" + targetName + "§e: " + role.getName()));
    if (!role.isDefault()) {
      sender.sendMessage(TextComponent.fromLegacyText("§7Permissão base: §f" + role.getPermission()));
    }
    sender.sendMessage(TextComponent.fromLegacyText("§7Permissões do cargo (" + rolePermissions.size() + "): §f" + (rolePermissions.isEmpty() ? "nenhuma" : String.join(", ", rolePermissions))));
    sender.sendMessage(TextComponent.fromLegacyText("§7Permissões extras (" + playerPermissions.size() + "): §f" + (playerPermissions.isEmpty() ? "nenhuma" : String.join(", ", playerPermissions))));

    ProxiedPlayer online = ProxyServer.getInstance().getPlayer(targetName);
    if (online != null) {
      sender.sendMessage(TextComponent.fromLegacyText("§7Cargo ativo online: §f" + Role.getPlayerRole(online).getName()));
    }
  }
}
