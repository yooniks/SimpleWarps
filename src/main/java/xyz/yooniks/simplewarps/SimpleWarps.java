package xyz.yooniks.simplewarps;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.simplewarps.command.WarpCommand;
import xyz.yooniks.simplewarps.listener.InventoryClick;
import xyz.yooniks.simplewarps.warp.WarpManager;

public final class SimpleWarps extends JavaPlugin {

  private final WarpManager warpManager;

  public SimpleWarps() {
    this.warpManager = new WarpManager();
  }

  @Override
  public void onEnable() {
    this.saveDefaultConfig();
    this.warpManager.setupWith(this.getConfig());

    this.getCommand("warp").setExecutor(new WarpCommand(this.warpManager));
    this.getServer().getPluginManager().registerEvents(new InventoryClick(this.warpManager), this);
  }

}
