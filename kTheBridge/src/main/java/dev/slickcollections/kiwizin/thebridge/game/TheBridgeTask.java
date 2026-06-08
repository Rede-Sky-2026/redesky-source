package dev.slickcollections.kiwizin.thebridge.game;

import dev.slickcollections.kiwizin.game.GameState;
import dev.slickcollections.kiwizin.nms.NMS;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.KCoreSettings;
import dev.slickcollections.kiwizin.thebridge.Main;
import dev.slickcollections.kiwizin.thebridge.container.HotbarContainer;
import dev.slickcollections.kiwizin.thebridge.container.SelectedContainer;
import dev.slickcollections.kiwizin.thebridge.cosmetics.CosmeticType;
import dev.slickcollections.kiwizin.thebridge.cosmetics.object.AbstractExecutor;
import dev.slickcollections.kiwizin.thebridge.cosmetics.types.WinAnimation;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.StringUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class TheBridgeTask {
  
  private final TheBridge game;
  private BukkitTask task;
  
  public TheBridgeTask(TheBridge game) {
    this.game = game;
    
    this.reset();
  }
  
  public void cancel() {
    if (this.task != null) {
      this.task.cancel();
      this.task = null;
    }
  }
  
  public void reset() {
    this.cancel();
    this.task = new BukkitRunnable() {
      
      @Override
      public void run() {
        if (game.getTimer() == 0) {
          game.start();
          return;
        }
        
        if (game.getOnline() < game.getMaxPlayers()) {
          if (game.getTimer() != 11) {
            game.setTimer(11);
          }
          
          game.listPlayers().forEach(player -> Profile.getProfile(player.getName()).update());
          return;
        }
        
        if (game.getTimer() == 11) {
          game.setTimer(10);
        }
        
        game.listPlayers().forEach(player -> {
          Profile.getProfile(player.getName()).update();
          if (game.getTimer() <= 5) {
            EnumSound.CLICK.play(player, 0.5F, 1.0F);
          }
        });
        
        if (game.getTimer() <= 5) {
          game.broadcastMessage(KCoreSettings.TheBridge.ingame$broadcast$starting.replace("{time}", StringUtils.formatNumber(game.getTimer())).replace("{s}", game.getTimer() > 1 ? "s" : ""));
        }
        
        game.setTimer(game.getTimer() - 1);
      }
    }.runTaskTimer(Main.getInstance(), 0, 20);
  }
  
  public void swap(TheBridgeTeam winners) {
    this.cancel();
    if (this.game.getState() == GameState.INICIANDO) {
      this.game.setTimer(5);
      this.task = new BukkitRunnable() {
        @Override
        public void run() {
          if (game.getTimer() == 0) {
            game.start();
            return;
          }
          
          game.listPlayers(false).forEach(player -> {
            EnumSound.NOTE_PLING.play(player, 0.5F, 1.0F);
            NMS.sendTitle(player, KCoreSettings.TheBridge.ingame$titles$cage$header.replace("{time}", (game.getTimer() > 3 ? "§a§l" : "§c§l") + game.getTimer()),
                KCoreSettings.TheBridge.ingame$titles$cage$footer, 0, 20, 0);
          });
          game.setTimer(game.getTimer() - 1);
        }
      }.runTaskTimer(Main.getInstance(), 0, 20);
    } else if (this.game.getState() == GameState.EMJOGO) {
      this.task = new BukkitRunnable() {
        @Override
        public void run() {
          game.listPlayers().forEach(player -> {
            Profile profile = Profile.getProfile(player.getName());
            if (profile == null) {
              return;
            }
            
            profile.update();
            
            TheBridgeTeam team = game.getTeam(player);
            if (team != null && team.isArrow(player)) {
              long arrowTime = team.getArrow(player);
              player.setExp((arrowTime * 1.0F) / 5);
              player.setLevel((int) arrowTime);
              if (arrowTime == 0) {
                player.getInventory()
                    .setItem(profile.getAbstractContainer("kCoreTheBridge", "hotbar", HotbarContainer.class).get("AR0", 8), BukkitUtils.deserializeItemStack("ARROW : 1"));
                player.updateInventory();
                team.removeArrow(player);
              }
            }
          });
        }
      }.runTaskTimer(Main.getInstance(), 0, 20);
    } else if (this.game.getState() == GameState.ENCERRADO) {
      this.game.setTimer(10);
      List<AbstractExecutor> executors = new ArrayList<>();
      if (winners != null) {
        winners.listPlayers().forEach(player -> executors.add(Profile.getProfile(player.getName()).getAbstractContainer("kCoreTheBridge", "selected", SelectedContainer.class)
            .getSelected(CosmeticType.WIN_ANIMATION, WinAnimation.class).execute(player)));
      }
      
      this.task = new BukkitRunnable() {
        
        @Override
        public void run() {
          if (game.getTimer() == 0) {
            executors.forEach(AbstractExecutor::cancel);
            executors.clear();
            game.listPlayers().forEach(player -> game.leave(Profile.getProfile(player.getName()), null));
            game.reset();
            return;
          }
          executors.forEach(executor -> {
            if (winners == null || !winners.listPlayers().contains(executor.getPlayer())) {
              return;
            }
            
            executor.tick();
          });
          
          game.setTimer(game.getTimer() - 1);
        }
      }.runTaskTimer(Main.getInstance(), 0, 20);
    }
  }
}
