package xyz.yooniks.simplewarps.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.yooniks.simplewarps.warp.WarpManager;

public class InventoryClick implements Listener {

  private final WarpManager warpManager;

  public InventoryClick(WarpManager warpManager) {
    this.warpManager = warpManager;
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player) || event.getInventory() == null || event.getCurrentItem() == null) {
      return;
    }
    if (event.getInventory().getTitle().equalsIgnoreCase(this.warpManager.getWarpInventory().getTitle())) {

      final Player player = ((Player) event.getWhoClicked());

      this.warpManager.getWarpBySlot(event.getSlot()).ifPresent(warp -> {

        player.closeInventory();
        this.warpManager.teleport(player, warp);

      });

    }
  }

}
