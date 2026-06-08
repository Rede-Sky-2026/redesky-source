package dev.slickcollections.kiwizin.bungee.cmd;

import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.player.role.RolePermissionManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public class PermissionCommand extends Commands {

  public PermissionCommand() {
    super("perm", "permission", "permissions");
  }

  @Override
  public void perform(CommandSender sender, String[] args) {
    if (!sender.hasPermission("kcore.cmd.permission")) {
      sender.sendMessage(TextComponent.fromLegacyText("§cVocê não possui permissão para utilizar este comando."));
      return;
    }

    if (args.length < 2) {
      sender.sendMessage(TextComponent.fromLegacyText("§cUso: /perm <add|remove|list> <cargo|jogador> <alvo> [permissao]"));
      return;
    }

    String action = args[0].toLowerCase();
    String type = args[1].toLowerCase();

    if (action.equals("list")) {
      if (type.equals("cargo") || type.equals("role")) {
        if (args.length < 3) {
          sender.sendMessage(TextComponent.fromLegacyText("§cUso: /perm list cargo <cargo>"));
          return;
        }
        Role role = RolePermissionManager.resolveRole(args[2]);
        if (role == null) {
          sender.sendMessage(TextComponent.fromLegacyText("§cCargo inválido."));
          return;
        }
        List<String> permissions = RolePermissionManager.listRolePermissions(role);
        sender.sendMessage(TextComponent.fromLegacyText("§ePermissões de " + role.getName() + "§e: §f" + (permissions.isEmpty() ? "nenhuma" : String.join(", ", permissions))));
        return;
      }

      if (type.equals("jogador") || type.equals("player")) {
        if (args.length < 3) {
          sender.sendMessage(TextComponent.fromLegacyText("§cUso: /perm list jogador <jogador>"));
          return;
        }
        List<String> permissions = RolePermissionManager.listPlayerPermissions(args[2]);
        sender.sendMessage(TextComponent.fromLegacyText("§ePermissões extras de §f" + args[2] + "§e: §f" + (permissions.isEmpty() ? "nenhuma" : String.join(", ", permissions))));
        return;
      }

      sender.sendMessage(TextComponent.fromLegacyText("§cTipo inválido. Use cargo ou jogador."));
      return;
    }

    if (args.length < 4) {
      sender.sendMessage(TextComponent.fromLegacyText("§cUso: /perm <add|remove> <cargo|jogador> <alvo> <permissao>"));
      return;
    }

    String target = args[2];
    String permission = args[3].toLowerCase();

    if (type.equals("cargo") || type.equals("role")) {
      Role role = RolePermissionManager.resolveRole(target);
      if (role == null) {
        sender.sendMessage(TextComponent.fromLegacyText("§cCargo inválido."));
        return;
      }

      boolean changed = action.equals("add")
          ? RolePermissionManager.addRolePermission(role, permission)
          : RolePermissionManager.removeRolePermission(role, permission);

      sender.sendMessage(TextComponent.fromLegacyText(changed
          ? "§aPermissão §f" + permission + " §a" + (action.equals("add") ? "adicionada ao" : "removida do") + " cargo " + role.getName() + "§a."
          : "§cNada foi alterado."));
      return;
    }

    if (type.equals("jogador") || type.equals("player")) {
      boolean changed = action.equals("add")
          ? RolePermissionManager.addPlayerPermission(target, permission)
          : RolePermissionManager.removePlayerPermission(target, permission);

      sender.sendMessage(TextComponent.fromLegacyText(changed
          ? "§aPermissão §f" + permission + " §a" + (action.equals("add") ? "adicionada ao" : "removida do") + " jogador §f" + target + "§a."
          : "§cNada foi alterado."));
      return;
    }

    sender.sendMessage(TextComponent.fromLegacyText("§cTipo inválido. Use cargo ou jogador."));
  }
}
