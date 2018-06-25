package xyz.yooniks.simplewarps.warp;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.yooniks.simplewarps.MessageCreator;
import xyz.yooniks.simplewarps.SimpleWarps;
import xyz.yooniks.simplewarps.util.LocationUtil;
import xyz.yooniks.simplewarps.warp.Warp.WarpMessageType;

public class WarpTeleportTask extends BukkitRunnable {

  private final Warp warp;
  private final Player player;
  private final Location startLocation;

  private int time;

  public WarpTeleportTask(Warp warp, Player player) {
    Validate.notNull(warp, "Warp cannot be null!");
    Validate.notNull(player, "Player must be online!");

    this.warp = warp;
    this.player = player;
    this.startLocation = this.player.getLocation().clone();

    this.time = warp.getTimeToTeleport();
  }

   public void start() {
     new MessageCreator(this.player, this.warp.getMessagesMap().get(WarpMessageType.TELEPORTING))
         .send();
    this.runTaskTimer(SimpleWarps.getPlugin(SimpleWarps.class), 0L, 20L);
   }

  @Override
  public void run() {
    if (!this.player.isOnline()) {
      this.cancel();
      return;
    }
    if (!LocationUtil.isSimilar(this.player.getLocation(), this.startLocation)) {

      new MessageCreator(this.player, this.warp.getMessagesMap().get(WarpMessageType.ERROR))
          .send();

      this.cancel();
      return;
    }
    this.time--;
    if (time <= 0) {
      this.player.teleport(this.warp.getLocation(), TeleportCause.PLUGIN);

      new MessageCreator(this.player, this.warp.getMessagesMap().get(WarpMessageType.SUCCESS))
          .send();

      this.cancel();
    }
  }

}
