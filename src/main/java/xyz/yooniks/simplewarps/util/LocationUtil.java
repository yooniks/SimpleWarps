package xyz.yooniks.simplewarps.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public final class LocationUtil {

  private LocationUtil() {
  }

  public static Location fromSection(ConfigurationSection section) {
    return new Location(
        Bukkit.getWorld(section.getString("world","world")),
        section.getDouble("x", 1.0D),
        section.getDouble("y", 80.0D),
        section.getDouble("z", 1.0D),
        section.getInt("yaw", 0),
        section.getInt("pitch", 0));
  }

  public static boolean isSimilar(Location a, Location b) {
    return a.getX() != b.getX() || a.getY() != b.getY() || a.getZ() != b.getZ();
  }

}
