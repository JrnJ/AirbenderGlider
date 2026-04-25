package com.jeroenj.airbenderglider.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "airbender-glider.json");

    public static class ConfigData {
        public boolean renderAirbenderGliderPole = true;
    }

    public static ConfigData configData = new ConfigData();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                configData = GSON.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(configData, writer);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
