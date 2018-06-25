package xyz.yooniks.simplewarps;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageCreator {

  private final CommandSender commandSender;
  private final String text;

  public MessageCreator(CommandSender commandSender, String text) {
    this.commandSender = commandSender;
    this.text = text;
  }

  public void send() {
    this.commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.text));
  }

}
