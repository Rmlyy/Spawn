package dev.rmly.spawn;

import dev.rmly.spawn.commands.ReloadSpawnCommand;
import dev.rmly.spawn.commands.SetSpawnCommand;
import dev.rmly.spawn.commands.SpawnCommand;
import dev.rmly.spawn.commands.SpawnHelpCommand;
import dev.rmly.spawn.events.join.TeleportToSpawn;
import dev.rmly.spawn.files.SpawnFile;
import dev.rmly.spawn.utils.ChatUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfigs();
        registerCommands();
        registerEvents();
        getLogger().info(ChatUtil.color("&e| &fEnabled &ev1.0"));
    }

    public void loadConfigs() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        SpawnFile.setup();
    }

    public void registerCommands() {
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("reloadspawn").setExecutor(new ReloadSpawnCommand());
        getCommand("spawnhelp").setExecutor(new SpawnHelpCommand());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new TeleportToSpawn(), this);
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatUtil.color("&e| &fDisabled &ev1.0"));
    }
}
