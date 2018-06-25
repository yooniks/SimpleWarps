package xyz.yooniks.simplewarps.warp;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.yooniks.simplewarps.util.LocationUtil;
import xyz.yooniks.simplewarps.util.WarpItemUtil;
import xyz.yooniks.simplewarps.warp.Warp.WarpMessageType;

public class WarpManager {

  private final List<Warp> warps = new ArrayList<>();
  private Inventory warpInventory;

  public void setupWith(FileConfiguration configuration) {
    for (String warpId : configuration.getConfigurationSection("warps").getKeys(false)) {
      final ConfigurationSection section = configuration.getConfigurationSection("warps").getConfigurationSection(warpId);
      final Warp warp = new Warp(
          LocationUtil.fromSection(section.getConfigurationSection("location")),
          WarpItemUtil.fromSection(section.getConfigurationSection("warp-item")),
          section.getInt("teleport-time", 10));

      final ConfigurationSection messages = section.getConfigurationSection("messages");
      warp.getMessagesMap().put(WarpMessageType.ERROR, messages.getString("error"));
      warp.getMessagesMap().put(WarpMessageType.SUCCESS, messages.getString("success"));
      warp.getMessagesMap().put(WarpMessageType.TELEPORTING, messages.getString("teleporting"));

      this.warps.add(warp);
    }

    final ConfigurationSection inventoryConfiguration = configuration
        .getConfigurationSection("inventory");

    this.warpInventory = Bukkit.createInventory(
        null,
        inventoryConfiguration.getInt("size", 9),
        ChatColor.translateAlternateColorCodes('&', inventoryConfiguration.getString("name")));

    this.warps
        .stream()
        .map(Warp::getWarpItem)
        .forEach(warpItem ->
            this.warpInventory.setItem(warpItem.getSlot(), warpItem.getItem()));
  }

  public void teleport(Player player, Warp warp) {
    new WarpTeleportTask(warp, player).start();
  }

  public Inventory getWarpInventory() {
    return this.warpInventory;
  }

  public Optional<Warp> getWarpBySlot(int slot) {
    return this.warps.stream()
        .filter(warp -> warp.getWarpItem().getSlot() == slot)
        .findFirst();
  }

  public ImmutableList<Warp> getWarps() {
    return ImmutableList.copyOf(this.warps);
  }

}
