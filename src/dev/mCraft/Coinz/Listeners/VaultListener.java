package dev.mCraft.Coinz.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryCloseEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Blocks.Vault;
import dev.mCraft.Coinz.Serializer.PersistVault;

public class VaultListener implements Listener {
	
	private Coinz plugin = Coinz.instance;
	private Vault vault;
	private PersistVault persist;
	
	private SpoutPlayer player;

	private Inventory inv;
	private Location loc;
	
	private String invName;
	private int slotNum;
	private ItemStack cursor;
	private int amount;
	private ItemStack slot;
	
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
		if (slotNum != -999) {
		slot = inv.getItem(slotNum);
		}
		amount = slot.getAmount();
		cursor = event.getCursor();
		
		if (invName == "Vault") {
			player.sendMessage(slotNum + "");
			if (cursor != null) {
				if (slotNum == 0 && cursor.getDurability() != plugin.CopperCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 1 && cursor.getDurability() != plugin.HalfBronzeCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 2 && cursor.getDurability() != plugin.BronzeCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 3 && cursor.getDurability() != plugin.HalfSilverCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 4 && cursor.getDurability() != plugin.SilverCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 5 && cursor.getDurability() != plugin.HalfGoldCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 6 && cursor.getDurability() != plugin.GoldCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 7 && cursor.getDurability() != plugin.HalfPlatinumCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 8 && cursor.getDurability() != plugin.PlatinumCoin.getDurability()) {
					event.setCancelled(true);
				}
			}
		}
	}

}
