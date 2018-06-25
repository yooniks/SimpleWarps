package xyz.yooniks.simplewarps.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.yooniks.simplewarps.warp.Warp.WarpItem;

public final class WarpItemUtil {

  private WarpItemUtil() {
  }

  public static WarpItem fromSection(ConfigurationSection section) {
    final ItemStack item = new ItemStack(Material.GRASS);
    final ItemMeta meta = item.getItemMeta();

    final int slot = section.getInt("slot", 1);

    if (section.isString("material")) {
      final Material material = Material.getMaterial(section.getString("material"));
      if (material != null) {
        item.setType(material);
      }
    }
    if (section.isList("lore")) {
      final List<String> current_lore = section.getStringList("lore");
      final List<String> lore = new ArrayList<>(current_lore.size());

      current_lore
          .forEach(string ->
              lore.add(ChatColor.translateAlternateColorCodes('&', string)));

      meta.setLore(lore);
    }
    if (section.isString("name")) {
      meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', section.getString("name")));
    }
    if (section.isInt("amount")) {
      item.setAmount(section.getInt("amount"));
    }
    if (section.isInt("data") || section.isInt("durability")) {
      item.setDurability((short) section.getInt("data", section.getInt("durability")));
    }

    item.setItemMeta(meta);
    return new WarpItem(item, slot);
  }

}
