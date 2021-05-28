package dev.rmly.spawn.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnFile {
    private static File file;
    private static FileConfiguration spawnFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Spawn").getDataFolder(), "spawn.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        spawnFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return spawnFile;
    }

    public static void save() {
        try {
            spawnFile.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void reload() {
        spawnFile = YamlConfiguration.loadConfiguration(file);
    }

}
