package com.jeroenj.airbenderglider.client;

import com.jeroenj.airbenderglider.client.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;

public class AirbenderGliderClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ConfigManager.load();
	}
}