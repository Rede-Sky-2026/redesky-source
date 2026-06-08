package dev.slickcollections.kiwizin.bungee.cmd;

import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.player.role.RolePermissionManager;
import dev.slickcollections.kiwizin.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class SetRankCommand extends Commands {

  public SetRankCommand() {
    super("setrank", "rankset", "tag");
  }

  @Override
  public void perform(CommandSender sender, String[] args) {
    if (!sender.hasPermission("kcore.cmd.setrank")) {
      sender.sendMessage(TextComponent.fromLegacyText("§cVocê não possui permissão para utilizar este comando."));
      return;
    }

    if (args.length < 2) {
      sender.sendMessage(TextComponent.fromLegacyText("§cUso: /setrank <jogador> <cargo>"));
      return;
    }

    Role role = RolePermissionManager.resolveRole(args[1]);
    if (role == null) {
      sender.sendMessage(TextComponent.fromLegacyText("§cCargo inválido. Utilize nomes como Master, VIP, Admin..."));
      return;
    }

    RolePermissionManager.setPlayerRole(args[0], role);
    sender.sendMessage(TextComponent.fromLegacyText("§aCargo de §f" + args[0] + " §aalterado para " + role.getName() + "§a."));
  }
}
