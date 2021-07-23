package com.nktfh100.TeamBossBarHealth.managers;

import java.util.ArrayList;

import org.bukkit.boss.BarColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.nktfh100.TeamBossBarHealth.info.TeamInfo;
import com.nktfh100.TeamBossBarHealth.main.TeamBossBarHealth;

public class ConfigManager {

	private TeamBossBarHealth plugin;

	private int combatTimeout;

	private ArrayList<TeamInfo> teams;

	public ConfigManager(TeamBossBarHealth instance) {
		this.plugin = instance;
	}

	public void loadConfig() {
		this.plugin.saveDefaultConfig();
		this.plugin.reloadConfig();

		FileConfiguration config = this.plugin.getConfig();

		this.teams = new ArrayList<TeamInfo>();

		this.combatTimeout = config.getInt("combat-timeout", 20);

		ConfigurationSection teamsSC = config.getConfigurationSection("teams");
		if (teamsSC != null) {
			for (String key : teamsSC.getKeys(false)) {
				ConfigurationSection teamSC = teamsSC.getConfigurationSection(key);
				this.teams.add(new TeamInfo(teamSC.getString("name", ""), teamSC.getString("bossbar", ""), BarColor.valueOf(teamSC.getString("color", "WHITE"))));
			}
		}
	}

	public ArrayList<TeamInfo> getTeams() {
		return teams;
	}

	public int getCombatTimeout() {
		return combatTimeout;
	}

}
