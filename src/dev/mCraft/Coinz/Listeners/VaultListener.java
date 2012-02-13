package dev.mCraft.Coinz.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryCloseEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Blocks.Vault;
import dev.mCraft.Coinz.Serializer.PersistVault;
import dev.mCraft.Coinz.api.Vault.VaultStoreEvent;
import dev.mCraft.Coinz.api.Vault.VaultTakeEvent;

public class VaultListener implements Listener {
	
	private Coinz plugin = Coinz.instance;
	private Vault vault;
	private PersistVault persist;
	private Inventory inv;
	private Location loc;
	
	private SpoutPlayer player;
	private String invName;
	private int slotNum;
	private SpoutItemStack cursor;
	private SpoutItemStack slot;
	
	public VaultListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void closingVaultInv(InventoryCloseEvent event) {
		vault = Vault.hook;
		persist = PersistVault.hook;
		
		inv = event.getInventory();
		loc = event.getLocation();
		
		if (inv.getName() == "Vault" && vault != null) {
			try {
				persist.save(loc, inv);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void clickingVaultInv(InventoryClickEvent event) {
		player = (SpoutPlayer) event.getPlayer();
		inv = event.getInventory();
		invName = inv.getName();
		slotNum = event.getSlot();
		cursor = new SpoutItemStack(event.getCursor());
		slot = new SpoutItemStack(inv.getItem(slotNum));
		
		if (invName == "Vault") {
			
			if (cursor != null) {
				
				VaultStoreEvent storeEvent = new VaultStoreEvent(player, cursor);
				Bukkit.getServer().getPluginManager().callEvent(storeEvent);
				
				SpoutItemStack item = storeEvent.getItemStack();
				
				if (slotNum == 0 && item != Coinz.CopperCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 1 && item != Coinz.HalfBronzeCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 2 && item != Coinz.BronzeCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 3 && item != Coinz.HalfSilverCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 4 && item != Coinz.SilverCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 5 && item != Coinz.HalfGoldCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 6 && item != Coinz.GoldCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 7 && item != Coinz.HalfPlatinumCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 8 && item != Coinz.PlatinumCoin) {
					event.setCancelled(true);
				}
			}
			else {
				VaultTakeEvent takeEvent = new VaultTakeEvent(player, slot);
				Bukkit.getServer().getPluginManager().callEvent(takeEvent);
			}
		}
	}

}
