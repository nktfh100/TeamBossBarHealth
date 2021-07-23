package com.nktfh100.TeamBossBarHealth.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.nktfh100.TeamBossBarHealth.info.PlayerInfo;
import com.nktfh100.TeamBossBarHealth.info.TeamInfo;
import com.nktfh100.TeamBossBarHealth.main.TeamBossBarHealth;

public class PlayerDamagePlayer implements Listener {

	@EventHandler
	public void onPlayerDamagePlayer(EntityDamageByEntityEvent ev) {
		if (ev.getEntity() instanceof Player && ev.getDamager() instanceof Player) {
			TeamBossBarHealth plugin = TeamBossBarHealth.getInstance();
			Player victim = (Player) ev.getEntity();
			Player attacker = (Player) ev.getDamager();

			PlayerInfo victimInfo = plugin.getPlayersManager().getPlayerInfo(victim);
			PlayerInfo attackerInfo = plugin.getPlayersManager().getPlayerInfo(attacker);
			TeamInfo teamInfo = victimInfo.getPlayerTeam();
			if (teamInfo == null)
				return;

			double finalHealth = victim.getHealth() - ev.getFinalDamage();
			if (finalHealth < 0) {
				finalHealth = 0;
			}
			attackerInfo.setTargetPlayer(victim);
			attackerInfo.setCombatTimeout(plugin.getConfigManager().getCombatTimeout());
			attackerInfo.getBossBar().setTitle(teamInfo.getBossbar().replace("%player%", victim.getName()));
			attackerInfo.getBossBar().setProgress(finalHealth / 20D);
			attackerInfo.getBossBar().setColor(teamInfo.getColor());
			attackerInfo.getBossBar().setVisible(true);

		}
	}

}
