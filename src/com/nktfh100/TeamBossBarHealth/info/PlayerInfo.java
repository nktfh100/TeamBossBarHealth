package com.nktfh100.TeamBossBarHealth.info;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.nktfh100.TeamBossBarHealth.main.TeamBossBarHealth;

public class PlayerInfo {

	private Player player;
	private BossBar bossBar;
	private Player targetPlayer;
	private int combatTimeout;

	public PlayerInfo(Player player) {
		this.player = player;

		this.bossBar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID);
		this.bossBar.addPlayer(player);
		this.bossBar.setVisible(false);
	}

	public Player getPlayer() {
		return player;
	}

	@SuppressWarnings("deprecation")
	public TeamInfo getPlayerTeam() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

		for (TeamInfo team : TeamBossBarHealth.getInstance().getConfigManager().getTeams()) {
			Team scoreBoardTeam = scoreboard.getTeam(team.getName());
			if (scoreBoardTeam != null) {
				if (scoreBoardTeam.hasPlayer(player)) {
					return team;
				}
			}
		}
		return null;
	}

	public BossBar getBossBar() {
		return bossBar;
	}

	public void setBossBar(BossBar bossBar) {
		this.bossBar = bossBar;
	}

	public Player getTargetPlayer() {
		return targetPlayer;
	}

	public void setTargetPlayer(Player targetPlayer) {
		this.targetPlayer = targetPlayer;
	}

	public int getCombatTimeout() {
		return combatTimeout;
	}

	public void setCombatTimeout(int combatTimeout) {
		this.combatTimeout = combatTimeout;
	}

}
