package dev.rmly.spawn.commands;

import dev.rmly.spawn.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatUtil.color("&e| &fThis server is running &eSpawn v1.0 &fdeveloped by &eRmly @ https://rmly.dev"));
        sender.sendMessage(ChatUtil.color("&ehttps://www.spigotmc.org/members/rmly.1075826/"));
        sender.sendMessage("\n");
        if (sender.hasPermission("spawn.set")) {
            sender.sendMessage(ChatUtil.color("&e| &f/setspawn &7> &fSet the spawn point to your current location."));
        }
        if (sender.hasPermission("spawn.use")) {
            sender.sendMessage(ChatUtil.color("&e| &f/spawn <Player> &7> &fTeleport you or another player to the spawn point."));
        }
        if (sender.hasPermission("spawn.reload")) {
            sender.sendMessage(ChatUtil.color("&e| &f/spawnreload &7> &fReload the plugin's configuration"));
        } else {
            sender.sendMessage(ChatUtil.color("&e| &fYou don't have permission to use any commands."));
        }
        return true;
    }
}
