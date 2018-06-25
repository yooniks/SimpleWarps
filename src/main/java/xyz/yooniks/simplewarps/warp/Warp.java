package xyz.yooniks.simplewarps.warp;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Warp {

  private final Location location;
  private final int timeToTeleport;

  private final WarpItem warpItem;
  private final Map<WarpMessageType, String> messagesMap = new HashMap<>();

  public Warp(Location location, WarpItem warpItem, int timeToTeleport) {
    this.location = location;
    this.warpItem = warpItem;
    this.timeToTeleport = timeToTeleport;
  }

  public int getTimeToTeleport() {
    return this.timeToTeleport;
  }

  public Location getLocation() {
    return this.location;
  }

  public WarpItem getWarpItem() {
    return this.warpItem;
  }

  public Map<WarpMessageType, String> getMessagesMap() {
    return this.messagesMap;
  }

  public static class WarpItem {
    private final ItemStack item;
    private final int slot;

    public WarpItem(ItemStack item, int slot) {
      this.item = item;
      this.slot = slot;
    }

    public ItemStack getItem() {
      return this.item;
    }

    public int getSlot() {
      return this.slot;
    }
  }

  public static enum WarpMessageType {
    ERROR,
    SUCCESS,
    TELEPORTING
  }

}
