package dev.mCraft.Coinz.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import dev.mCraft.Coinz.Coinz;

public class BlockListener implements Listener {
	
	public Coinz plugin = Coinz.instance;
	
	public BlockListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

}
