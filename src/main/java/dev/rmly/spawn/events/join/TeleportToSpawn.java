package dev.rmly.spawn.events.join;

import dev.rmly.spawn.Main;
import dev.rmly.spawn.files.SpawnFile;
import dev.rmly.spawn.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TeleportToSpawn implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        String prefix = ChatUtil.color(Main.getInstance().getConfig().getString("Prefix"));
        String notSet = prefix + ChatUtil.color(Main.getInstance().getConfig().getString("Errors.NotSet"));

        if (Main.getInstance().getConfig().getBoolean("TeleportOnJoin")) {
            if (SpawnFile.get().getString("world") == null) {
                Main.getInstance().getLogger().warning(notSet);
            } else {
                Player p = e.getPlayer();
                World world = Bukkit.getWorld(SpawnFile.get().getString("world"));
                double x = SpawnFile.get().getDouble("x");
                double y = SpawnFile.get().getDouble("y");
                double z = SpawnFile.get().getDouble("z");
                float yaw = SpawnFile.get().getInt("yaw");
                float pitch = SpawnFile.get().getInt("pitch");
                Location spawn = new Location(world, x, y, z, yaw, pitch);

                p.teleport(spawn);
            }
        }
    }
}
