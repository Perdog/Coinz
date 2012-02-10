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
import dev.mCraft.Coinz.api.Vault.VaultPlacedEvent;
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
		cursor = (SpoutItemStack) event.getCursor();
		slot = (SpoutItemStack) inv.getItem(slotNum);
		
		if (invName == "Vault") {
			
			if (cursor != null) {
				if (slotNum == 0 && cursor != Coinz.CopperCoin) {
					event.setCancelled(true);
				}
				else if (slotNum == 1 && cursor.getDurability() != Coinz.HalfBronzeCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 2 && cursor.getDurability() != Coinz.BronzeCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 3 && cursor.getDurability() != Coinz.HalfSilverCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 4 && cursor.getDurability() != Coinz.SilverCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 5 && cursor.getDurability() != Coinz.HalfGoldCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 6 && cursor.getDurability() != Coinz.GoldCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 7 && cursor.getDurability() != Coinz.HalfPlatinumCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 8 && cursor.getDurability() != Coinz.PlatinumCoin.getDurability()) {
					event.setCancelled(true);
				}
				
				VaultPlacedEvent placeEvent = new VaultPlacedEvent(player, cursor);
				Bukkit.getServer().getPluginManager().callEvent(placeEvent);
			}
			else {
				VaultTakeEvent takeEvent = new VaultTakeEvent(player, slot);
				Bukkit.getServer().getPluginManager().callEvent(takeEvent);
			}
		}
	}

}
