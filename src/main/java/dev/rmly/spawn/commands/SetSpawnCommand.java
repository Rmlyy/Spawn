package dev.rmly.spawn.commands;

import dev.rmly.spawn.Main;
import dev.rmly.spawn.files.SpawnFile;
import dev.rmly.spawn.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    String prefix = ChatUtil.color(Main.getInstance().getConfig().getString("Prefix"));
    String noConsole = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NoConsole"));
    String noPermission = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NoPermission"));
    String set = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Success.SpawnSet"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(noConsole);
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("spawn.set")) {
            p.sendMessage(noPermission);
            return true;
        }

        String world = p.getWorld().getName();
        double x = p.getLocation().getX();
        double y = p.getLocation().getY();
        double z = p.getLocation().getZ();
        float yaw = p.getLocation().getYaw();
        float pitch = p.getLocation().getPitch();

        SpawnFile.get().set("world", world);
        SpawnFile.get().set("x", x);
        SpawnFile.get().set("y", y);
        SpawnFile.get().set("z", z);
        SpawnFile.get().set("yaw", yaw);
        SpawnFile.get().set("pitch", pitch);
        SpawnFile.save();
        SpawnFile.reload();
        p.sendMessage(set);

        return true;
    }
}
