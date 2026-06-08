package dev.slickcollections.kiwizin.skywars.game.events;

import dev.slickcollections.kiwizin.nms.NMS;
import dev.slickcollections.kiwizin.KCoreSettings;
import dev.slickcollections.kiwizin.skywars.game.AbstractSkyWars;
import dev.slickcollections.kiwizin.skywars.game.SkyWarsEvent;
import dev.slickcollections.kiwizin.skywars.game.object.SkyWarsChest;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;

public class RefillEvent extends SkyWarsEvent {
  
  @Override
  public void execute(AbstractSkyWars game) {
    game.listChests().forEach(SkyWarsChest::refill);
    game.listPlayers(false).forEach(player -> {
      EnumSound.CHEST_OPEN.play(player, 0.5F, 1.0F);
      NMS.sendTitle(player, KCoreSettings.SkyWars.ingame$titles$refill$header, KCoreSettings.SkyWars.ingame$titles$refill$footer, 20, 60, 20);
    });
  }
  
  @Override
  public String getName() {
    return KCoreSettings.SkyWars.options$events$refill;
  }
}
