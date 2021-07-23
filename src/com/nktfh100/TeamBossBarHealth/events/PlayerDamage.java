package com.nktfh100.TeamBossBarHealth.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.nktfh100.TeamBossBarHealth.info.PlayerInfo;
import com.nktfh100.TeamBossBarHealth.main.TeamBossBarHealth;

public class PlayerDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent ev) {
		if (ev.getEntity() instanceof Player) {
			Player player = (Player) ev.getEntity();
			double finalHealth = player.getHealth() - ev.getFinalDamage();
			if (finalHealth < 0) {
				finalHealth = 0;
			}
			for (PlayerInfo pInfo_ : TeamBossBarHealth.getInstance().getPlayersManager().getPlayers()) {
				if (pInfo_.getTargetPlayer() == player) {
					pInfo_.getBossBar().setProgress(finalHealth / 20D);
				}
			}
		}
	}

}
