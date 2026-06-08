package dev.slickcollections.kiwizin.thebridge.game;

import dev.slickcollections.kiwizin.game.Game;
import dev.slickcollections.kiwizin.game.GameTeam;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.thebridge.container.SelectedContainer;
import dev.slickcollections.kiwizin.thebridge.cosmetics.CosmeticType;
import dev.slickcollections.kiwizin.thebridge.cosmetics.object.IslandBalloon;
import dev.slickcollections.kiwizin.thebridge.cosmetics.types.Balloon;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class TheBridgeTeam extends GameTeam {
  
  private int score;
  private final String color;
  private final String respawn;
  private String balloon;
  private IslandBalloon islandBalloon;
  private final Map<String, Long> arrow;
  
  public TheBridgeTeam(Game<TheBridgeTeam> game, String location, String respawn, String balloon, int size) {
    super(game, location, size);
    this.score = 0;
    this.color = game.listTeams().size() == 0 ? "§c" : "§9";
    this.respawn = respawn;
    this.balloon = balloon;
    this.arrow = new HashMap<>();
  }
  
  public void setBalloon(String balloon) {
    this.balloon = balloon;
  }
  
  @Override
  public void reset() {
    super.reset();
    this.score = 0;
    this.arrow.clear();
    if (this.islandBalloon != null) {
      this.islandBalloon.despawn();
      this.islandBalloon = null;
    }
  }
  
  public void increaseScore() {
    this.score++;
  }
  
  public void setArrow(Player player) {
    this.arrow.put(player.getName(), System.currentTimeMillis() + 5000);
  }
  
  public void removeArrow(Player player) {
    this.arrow.remove(player.getName());
  }
  
  public void spawnBalloon() {
    if (this.balloon != null) {
      Balloon balloon = null;
      List<Profile> profiles = this.listPlayers().stream().map(player -> Profile.getProfile(player.getName()))
          .filter(profile -> profile.getAbstractContainer("kCoreTheBridge", "selected", SelectedContainer.class).getSelected(CosmeticType.BALLOON, Balloon.class) != null)
          .collect(Collectors.toList());
      if (profiles.size() > 0) {
        balloon = profiles.get(ThreadLocalRandom.current().nextInt(profiles.size())).getAbstractContainer("kCoreTheBridge", "selected", SelectedContainer.class)
            .getSelected(CosmeticType.BALLOON, Balloon.class);
      }
      
      if (balloon != null) {
        this.islandBalloon = new IslandBalloon(BukkitUtils.deserializeLocation(this.balloon), balloon);
      }
      
    }
  }
  
  public void buildCage() {
    this.arrow.clear();
    Location location = this.getLocation().add(0, -1, 0);
    Location[] downs =
        {location, location.clone().add(1, 0, 0), location.clone().add(-1, 0, 0), location.clone().add(0, 0, 1), location.clone().add(0, 0, -1), location.clone().add(1, 0, 1),
            location.clone().add(-1, 0, 1), location.clone().add(1, 0, -1), location.clone().add(-1, 0, -1)};
    for (Location down : downs) {
      Block block = down.getBlock();
      block.setType(Material.STAINED_GLASS);
      BlockState state = block.getState();
      state.getData().setData(Byte.parseByte(this.getColorS()));
      state.update(true);
    }
    for (int i = 1; i < 4; i++) {
      location.add(0, 1, 0);
      Location[] uppers =
          {location.clone().add(2, 0, 0), location.clone().add(-2, 0, 0), location.clone().add(0, 0, 2), location.clone().add(0, 0, -2), location.clone().add(2, 0, 1),
              location.clone().add(2, 0, -1), location.clone().add(-2, 0, 1), location.clone().add(-2, 0, -1), location.clone().add(1, 0, 2), location.clone().add(-1, 0, -2),
              location.clone().add(1, 0, -2), location.clone().add(-1, 0, 2)};
      for (Location upper : uppers) {
        Block block = upper.getBlock();
        block.setType(Material.STAINED_GLASS);
        BlockState state = block.getState();
        state.getData().setData(Byte.parseByte(this.getColorS()));
        state.update(true);
      }
    }
    location.add(0, 1, 0);
    downs = new Location[]{location, location.clone().add(1, 0, 0), location.clone().add(-1, 0, 0), location.clone().add(0, 0, 1), location.clone().add(0, 0, -1),
        location.clone().add(1, 0, 1), location.clone().add(-1, 0, 1), location.clone().add(1, 0, -1), location.clone().add(-1, 0, -1)};
    for (Location down : downs) {
      Block block = down.getBlock();
      block.setType(Material.STAINED_GLASS);
      BlockState state = block.getState();
      state.getData().setData(Byte.parseByte(this.getColorS()));
      state.update(true);
    }
  }
  
  public void breakCage() {
    Location location = this.getLocation().add(0, -1, 0);
    Location[] downs =
        {location, location.clone().add(1, 0, 0), location.clone().add(-1, 0, 0), location.clone().add(0, 0, 1), location.clone().add(0, 0, -1), location.clone().add(1, 0, 1),
            location.clone().add(-1, 0, 1), location.clone().add(1, 0, -1), location.clone().add(-1, 0, -1)};
    for (Location down : downs) {
      down.getBlock().setType(Material.AIR);
    }
    for (int i = 1; i < 4; i++) {
      location.add(0, 1, 0);
      Location[] uppers =
          {location.clone().add(2, 0, 0), location.clone().add(-2, 0, 0), location.clone().add(0, 0, 2), location.clone().add(0, 0, -2), location.clone().add(2, 0, 1),
              location.clone().add(2, 0, -1), location.clone().add(-2, 0, 1), location.clone().add(-2, 0, -1), location.clone().add(1, 0, 2), location.clone().add(-1, 0, -2),
              location.clone().add(1, 0, -2), location.clone().add(-1, 0, 2)};
      for (Location upper : uppers) {
        upper.getBlock().setType(Material.AIR);
      }
    }
    location.add(0, 1, 0);
    downs = new Location[]{location, location.clone().add(1, 0, 0), location.clone().add(-1, 0, 0), location.clone().add(0, 0, 1), location.clone().add(0, 0, -1),
        location.clone().add(1, 0, 1), location.clone().add(-1, 0, 1), location.clone().add(1, 0, -1), location.clone().add(-1, 0, -1)};
    for (Location down : downs) {
      down.getBlock().setType(Material.AIR);
    }
  }
  
  public int getScore() {
    return this.score;
  }
  
  public boolean isArrow(Player player) {
    return this.arrow.containsKey(player.getName());
  }
  
  public long getArrow(Player player) {
    long timeMillis = System.currentTimeMillis();
    long currentArrow = this.arrow.getOrDefault(player.getName(), timeMillis) - timeMillis;
    if (currentArrow > 0) {
      return currentArrow / 1000;
    }
    
    return currentArrow;
  }
  
  public String getColor() {
    return this.color;
  }
  
  public String getColorS() {
    return this.color.equals("§c") ? "14" : "11";
  }
  
  public String getColorD() {
    return this.color.equals("§c") ? "RED" : "BLUE";
  }
  
  public Location getRespawn() {
    return BukkitUtils.deserializeLocation(this.respawn);
  }
}
