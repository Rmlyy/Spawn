package dev.rmly.spawn.commands;

import dev.rmly.spawn.Main;
import dev.rmly.spawn.files.SpawnFile;
import dev.rmly.spawn.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    String prefix = ChatUtil.color(Main.getInstance().getConfig().getString("Prefix"));
    String noConsole = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NoConsole"));
    String noPermission = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NoPermission"));
    String notSet = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NotSet"));
    String offline = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.Offline"));
    String teleported = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Success.Teleported"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(noConsole);
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("spawn.use")) {
            p.sendMessage(noPermission);
            return true;
        }

        if (SpawnFile.get().get("world") == null) {
            p.sendMessage(notSet);
            return true;
        }

        World world = Bukkit.getWorld(SpawnFile.get().getString("world"));
        double x = SpawnFile.get().getDouble("x");
        double y = SpawnFile.get().getDouble("y");
        double z = SpawnFile.get().getDouble("z");
        float yaw = SpawnFile.get().getInt("yaw");
        float pitch = SpawnFile.get().getInt("pitch");
        Location spawn = new Location(world, x, y, z, yaw, pitch);

        if (args.length == 0) {
            p.teleport(spawn);
            p.sendMessage(teleported);
        } else if (args.length > 1) {
            if (!p.hasPermission("spawn.others")) {
                p.sendMessage(noPermission);
                return true;
            }

            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                p.sendMessage(offline);
                return true;
            }

            String teleportedOther = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Success.TeleportedOther").replace("%target%", t.getName()));
            String teleportedBy = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Success.TeleportedBy").replace("%executor%", p.getName()));

            t.teleport(spawn);
            t.sendMessage(teleportedBy);
            p.sendMessage(teleportedOther);
        }
        return true;
    }
}
