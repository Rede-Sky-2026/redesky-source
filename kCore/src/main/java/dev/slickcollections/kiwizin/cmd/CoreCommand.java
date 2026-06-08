package dev.slickcollections.kiwizin.cmd;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.utils.SlickUpdater;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreCommand extends Commands {
  
  public CoreCommand() {
    super("kcore", "kc");
  }
  
  @Override
  public void perform(CommandSender sender, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (!player.hasPermission("kcore.admin")) {
        player.sendMessage("§6kCore §bv" + Core.getInstance().getDescription().getVersion() + " §7Criado por §6Kiwizin§7.");
        return;
      }
      
      if (args.length == 0) {
        player.sendMessage(" \n§6/kc atualizar §f- §7Atualizar o kCore.\n ");
        return;
      }

      String action = args[0];
      if (action.equalsIgnoreCase("atualizar")) {
        if (SlickUpdater.UPDATER != null) {
          if (!SlickUpdater.UPDATER.canDownload) {
            player.sendMessage(
                " \n§6§l[KCORE]\n \n§aA atualização já está baixada, ela será aplicada na próxima reinicialização do servidor. Caso deseje aplicá-la agora, utilize o comando /stop.\n ");
            return;
          }
          SlickUpdater.UPDATER.canDownload = false;
          SlickUpdater.UPDATER.downloadUpdate(player);
        } else {
          player.sendMessage("§aO plugin já se encontra em sua última versão.");
        }
      } else {
        player.sendMessage(" \n§6/kc atualizar §f- §7Atualizar o kCore.\n ");
      }
    } else {
      sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
    }
  }
}
