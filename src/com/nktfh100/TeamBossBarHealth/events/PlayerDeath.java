package com.nktfh100.TeamBossBarHealth.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.nktfh100.TeamBossBarHealth.info.PlayerInfo;
import com.nktfh100.TeamBossBarHealth.main.TeamBossBarHealth;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onDamage(PlayerDeathEvent ev) {
		for (PlayerInfo pInfo_ : TeamBossBarHealth.getInstance().getPlayersManager().getPlayers()) {
			if (pInfo_.getTargetPlayer() == ev.getEntity()) {
				pInfo_.getBossBar().setVisible(false);
				pInfo_.setTargetPlayer(null);
			}
		}

	}

}
