package dev.mCraft.Coinz.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;
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
	
	//private FileManager fm = SpoutManager.getFileManager();
	
	public PlayerListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	/*
	@EventHandler(priority = EventPriority.NORMAL)
	public void whenPlayerLeaves(PlayerQuitEvent event) {
		SpoutPlayer player = (SpoutPlayer) event.getPlayer();
		for (String file : fm.getCache(plugin)) {
			try {
			File image = new File(file);
			image.delete();
			}
			catch (Exception e) {
				plugin.log.warning("Could not remove " + file + " from player")
			}
		}
	}
	*/
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
					if (dur == Coinz.CopperCoin.getDurability()) {
						payer.getInventory().removeItem(Coinz.CopperCoin);
						reciever.getInventory().addItem(Coinz.CopperCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == Coinz.BronzeCoin.getDurability()) {
						payer.getInventory().removeItem(Coinz.BronzeCoin);
						reciever.getInventory().addItem(Coinz.BronzeCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == Coinz.SilverCoin.getDurability()) {
						payer.getInventory().removeItem(Coinz.SilverCoin);
						reciever.getInventory().addItem(Coinz.SilverCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == Coinz.GoldCoin.getDurability()) {
						payer.getInventory().removeItem(Coinz.GoldCoin);
						reciever.getInventory().addItem(Coinz.GoldCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == Coinz.PlatinumCoin.getDurability()) {
						payer.getInventory().removeItem(Coinz.PlatinumCoin);
						reciever.getInventory().addItem(Coinz.PlatinumCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
				}
			}
		}
	}

}
