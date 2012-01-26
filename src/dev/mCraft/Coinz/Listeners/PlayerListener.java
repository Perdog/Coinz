package dev.mCraft.Coinz.Listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.FileManager;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;

public class PlayerListener implements Listener {
	
	private Coinz plugin = Coinz.instance;
	
	private SpoutPlayer payer;
	private Entity clicked;
	private SpoutPlayer reciever;

	private ItemStack hand;
	private SpoutItemStack item;
	private short dur;
	
	private FileManager fm = SpoutManager.getFileManager();
	
	public PlayerListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void whenPlayerLeaves(PlayerQuitEvent event) {
		for (String file : fm.getCache(plugin)) {
			File image = new File(file);
			image.delete();
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void passCoins(PlayerInteractEntityEvent event) {
		payer = (SpoutPlayer)event.getPlayer();
		clicked = event.getRightClicked();
		hand = payer.getItemInHand();
		item = new SpoutItemStack(hand);
		dur = item.getDurability();
		
		if (clicked instanceof SpoutPlayer) {
			reciever = (SpoutPlayer)clicked;
			if (item.isCustomItem()) {
				if (!reciever.getInventory().contains(item, 1) && reciever.getInventory().firstEmpty() == -1) {
					payer.sendMessage(reciever.getName() + "'s inventory is full, you cannot give them any money");
					reciever.sendMessage("You can't get paid because your inventory is full!");
				}
				else {
					if (dur == plugin.CopperCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.CopperCoin);
						reciever.getInventory().addItem(plugin.CopperCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.BronzeCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.BronzeCoin);
						reciever.getInventory().addItem(plugin.BronzeCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.SilverCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.SilverCoin);
						reciever.getInventory().addItem(plugin.SilverCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.GoldCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.GoldCoin);
						reciever.getInventory().addItem(plugin.GoldCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.PlatinumCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.PlatinumCoin);
						reciever.getInventory().addItem(plugin.PlatinumCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
				}
			}
		}
	}

}
