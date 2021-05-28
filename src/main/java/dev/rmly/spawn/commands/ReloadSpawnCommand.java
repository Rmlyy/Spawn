package dev.rmly.spawn.commands;

import dev.rmly.spawn.Main;
import dev.rmly.spawn.files.SpawnFile;
import dev.rmly.spawn.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadSpawnCommand implements CommandExecutor {

    String prefix = ChatUtil.color(Main.getInstance().getConfig().getString("Prefix"));
    String noPermission = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NoPermission"));
    String reloaded = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Success.Reloaded"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("spawn.reload")) {
            sender.sendMessage(noPermission);
            return true;
        }

        SpawnFile.reload();
        Main.getInstance().reloadConfig();
        sender.sendMessage(reloaded);
        return true;
    }
}
