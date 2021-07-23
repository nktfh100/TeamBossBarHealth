package com.nktfh100.TeamBossBarHealth.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.nktfh100.TeamBossBarHealth.info.PlayerInfo;
import com.nktfh100.TeamBossBarHealth.main.TeamBossBarHealth;

public class PlayersManager implements Listener {

	private TeamBossBarHealth plugin;
	private HashMap<String, PlayerInfo> players = new HashMap<>();

	public PlayersManager(TeamBossBarHealth plugin) {
		this.plugin = plugin;
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			players.put(player.getUniqueId().toString(), new PlayerInfo(player));
		}
	}

	public void startTask() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (PlayerInfo pInfo : getPlayers()) {
					if (pInfo.getTargetPlayer() != null) {
						pInfo.setCombatTimeout(pInfo.getCombatTimeout() - 1);
						if (pInfo.getCombatTimeout() < 0) {
							pInfo.getBossBar().setVisible(false);
							pInfo.setTargetPlayer(null);
						}
					}
				}
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

	public PlayerInfo getPlayerInfo(Player player) {
		return players.get(player.getUniqueId().toString());
	}

	public PlayerInfo getPlayerByUUID(String uuid) {
		return this.players.get(uuid);
	}

	public List<PlayerInfo> getPlayers() {
		List<PlayerInfo> players_ = new ArrayList<PlayerInfo>(this.players.values());
		return players_;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		players.put(ev.getPlayer().getUniqueId().toString(), new PlayerInfo(ev.getPlayer()));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		PlayerInfo pInfo = this.players.get(player.getUniqueId().toString());
		if (pInfo == null) {
			return;
		}
		pInfo.getBossBar().removeAll();
		players.remove(player.getUniqueId().toString());
	}
}
