package com.nktfh100.TeamBossBarHealth.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.nktfh100.TeamBossBarHealth.events.PlayerDamage;
import com.nktfh100.TeamBossBarHealth.events.PlayerDamagePlayer;
import com.nktfh100.TeamBossBarHealth.events.PlayerDeath;
import com.nktfh100.TeamBossBarHealth.info.PlayerInfo;
import com.nktfh100.TeamBossBarHealth.managers.ConfigManager;
import com.nktfh100.TeamBossBarHealth.managers.PlayersManager;

public class TeamBossBarHealth extends JavaPlugin {

	private static TeamBossBarHealth instance;

	public TeamBossBarHealth() {
		instance = this;
	}

	private ConfigManager configManager;
	private PlayersManager playersManager;

	public void onEnable() {

		this.configManager = new ConfigManager(this);
		this.configManager.loadConfig();

		this.playersManager = new PlayersManager(this);
		this.playersManager.startTask();

		this.getServer().getPluginManager().registerEvents(this.playersManager, this);
		this.getServer().getPluginManager().registerEvents(new PlayerDamage(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDamagePlayer(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
	}

	public void onDisable() {
		for (PlayerInfo pInfo : this.playersManager.getPlayers()) {
			pInfo.getBossBar().removeAll();
		}
	}

	public static TeamBossBarHealth getInstance() {
		return instance;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public PlayersManager getPlayersManager() {
		return playersManager;
	}
}
