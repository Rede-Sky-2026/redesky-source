package dev.slickcollections.kiwizin.murder.game.object.runnables;

import dev.slickcollections.kiwizin.murder.game.types.ClassicMurder;
import dev.slickcollections.kiwizin.nms.NMS;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.StringUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.scheduler.BukkitRunnable;
import dev.slickcollections.kiwizin.KCoreSettings;
import dev.slickcollections.kiwizin.murder.game.Murder;

public class ClassicWaiting extends BukkitRunnable {

  private ClassicMurder game;

  public ClassicWaiting(Murder game) {
    this.game = (ClassicMurder) game;
  }

  @Override
  public void run() {
    if (this.game.getTimer() == 0) {
      this.game.start();
      return;
    }

    if (this.game.getOnline() < this.game.getConfig().getMinPlayers()) {
      if (this.game.getTimer() != (KCoreSettings.Murder.options$start$waiting + 1)) {
        this.game.setTimer(KCoreSettings.Murder.options$start$waiting + 1);
      }

      this.game.listPlayers().forEach(player -> Profile.getProfile(player.getName()).update());
      return;
    }

    if (this.game.getTimer() == (KCoreSettings.Murder.options$start$waiting + 1)) {
      this.game.setTimer(KCoreSettings.Murder.options$start$waiting);
    }

    this.game.listPlayers().forEach(player -> {
      Profile.getProfile(player.getName()).update();
      NMS.sendActionBar(player, "§a" + this.game.getDetectivePercentage(player) + "% Detetive - " + this.game.getKillerPercentage(player) + "% Assassino");
      if (this.game.getTimer() == 10 || game.getTimer() <= 5) {
        EnumSound.CLICK.play(player, 0.5F, 2.0F);
      }
    });

    if (this.game.getTimer() == 30 || this.game.getTimer() == 15 || this.game.getTimer() == 10 || this.game.getTimer() <= 5) {
      this.game
        .broadcastMessage(KCoreSettings.Murder.ingame$broadcast$starting.replace("{time}", StringUtils.formatNumber(this.game.getTimer())).replace("{s}", this.game.getTimer() > 1 ? "s" : ""));
    }

    this.game.setTimer(this.game.getTimer() - 1);
  }

  @Override
  public synchronized void cancel() throws IllegalStateException {
    super.cancel();
    this.game = null;
  }
}
