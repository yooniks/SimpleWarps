package xyz.yooniks.simplewarps.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.yooniks.simplewarps.warp.WarpManager;

public class WarpCommand implements CommandExecutor {

  private final WarpManager warpManager;

  public WarpCommand(WarpManager warpManager) {
    this.warpManager = warpManager;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Command is only executable by player!");
      return true;
    }

    final Player player = ((Player) sender);
    player.openInventory(this.warpManager.getWarpInventory());

    return true;
  }
}
