package dev.slickcollections.kiwizin.thebridge.listeners.player;

import dev.slickcollections.kiwizin.game.GameState;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.thebridge.cmd.tb.BuildCommand;
import dev.slickcollections.kiwizin.thebridge.game.TheBridge;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerRestListener implements Listener {
  
  @EventHandler
  public void onPlayerItemDamage(PlayerItemDamageEvent evt) {
    Profile profile = Profile.getProfile(evt.getPlayer().getName());
    if (profile != null && profile.playingGame()) {
      evt.setCancelled(true);
      evt.setDamage(0);
      evt.getPlayer().updateInventory();
    }
  }
  
  @EventHandler
  public void onPlayerConsume(PlayerItemConsumeEvent evt) {
    if (evt.getItem().getType() == Material.GOLDEN_APPLE) {
      evt.getPlayer().setHealth(20.0);
    }
  }
  
  @EventHandler
  public void onPlayerDropItem(PlayerDropItemEvent evt) {
    Profile profile = Profile.getProfile(evt.getPlayer().getName());
    if (profile != null) {
      evt.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onPlayerPickupItem(PlayerPickupItemEvent evt) {
    Profile profile = Profile.getProfile(evt.getPlayer().getName());
    if (profile != null) {
      TheBridge game = profile.getGame(TheBridge.class);
      if (game == null) {
        evt.setCancelled(true);
      } else {
        evt.setCancelled(game.getState() != GameState.EMJOGO || game.isSpectator(evt.getPlayer()));
      }
    }
  }
  
  @EventHandler
  public void onBlockBreak(BlockBreakEvent evt) {
    Profile profile = Profile.getProfile(evt.getPlayer().getName());
    if (profile != null) {
      TheBridge game = profile.getGame(TheBridge.class);
      if (game == null) {
        evt.setCancelled(!BuildCommand.hasBuilder(evt.getPlayer()));
      } else {
        evt.setCancelled(game.getState() != GameState.EMJOGO || game.isSpectator(evt.getPlayer()) || !game.getBridgeCube().contains(evt.getBlock().getLocation()));
      }
    }
  }
  
  @EventHandler
  public void onBlockPlace(BlockPlaceEvent evt) {
    Profile profile = Profile.getProfile(evt.getPlayer().getName());
    if (profile != null) {
      TheBridge game = profile.getGame(TheBridge.class);
      if (game == null) {
        evt.setCancelled(!BuildCommand.hasBuilder(evt.getPlayer()));
      } else {
        evt.setCancelled(game.getState() != GameState.EMJOGO || game.isSpectator(evt.getPlayer()) || !game.getBridgeCube().contains(evt.getBlock().getLocation()));
      }
    }
  }
}
