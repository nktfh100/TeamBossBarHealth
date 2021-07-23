package com.nktfh100.TeamBossBarHealth.info;

import org.bukkit.boss.BarColor;

import net.md_5.bungee.api.ChatColor;

public class TeamInfo {

	private String name;
	private String bossbar;
	private BarColor color;
	
	public TeamInfo(String name, String bossbar, BarColor color) {
		this.name = name;
		this.bossbar = ChatColor.translateAlternateColorCodes('&', bossbar);
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public String getBossbar() {
		return bossbar;
	}

	public BarColor getColor() {
		return color;
	}

}
