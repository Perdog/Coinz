package dev.mCraft.Coinz.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryCloseEvent;
import org.getspout.spoutapi.inventory.InventoryBuilder;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Blocks.Vault;
import dev.mCraft.Coinz.Serializer.PersistPasswords;
import dev.mCraft.Coinz.Serializer.PersistVault;
import dev.mCraft.Coinz.api.Vault.VaultStoreEvent;
import dev.mCraft.Coinz.api.Vault.VaultTakeEvent;

public class VaultListener implements Listener {
	
	private Coinz plugin = Coinz.instance;
	private Vault vault;
	private InventoryBuilder builder;
	private PersistVault persist;
	private PersistPasswords password;
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
	public void blockBreak(BlockBreakEvent event) {
		Block b = event.getBlock();
		SpoutBlock block = (SpoutBlock) b;
		
		if (block.isCustomBlock() && block.getCustomBlockId() == plugin.vaultBlock.getCustomId()) {
			persist = PersistVault.hook;
			password = PersistPasswords.hook;
			loc = block.getLocation();
			builder = SpoutManager.getInventoryBuilder();
			
			Inventory Vault = builder.construct(9, "Vault");
			Inventory temp = Vault;
			
			try {
				temp = persist.load(loc, temp);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			Vault = temp;
			
			if (Vault != null) {
				for (ItemStack item : Vault.getContents()) {
					if (item != null) {
						event.getBlock().getWorld().dropItem(loc, item);
					}
				}
			}
			
			try {
				persist.destory(loc);
				password.destory(loc);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		if (slotNum != -999) slot = new SpoutItemStack(inv.getItem(slotNum));
		else {
			return;
		}
		
		if (event.getCursor() != null && invName == "Vault") {
			
			cursor = new SpoutItemStack(event.getCursor());
			
			if (cursor != null) {
				
				VaultStoreEvent storeEvent = new VaultStoreEvent(player, cursor);
				Bukkit.getServer().getPluginManager().callEvent(storeEvent);
				
				SpoutItemStack item = storeEvent.getItemStack();
				
				if (slotNum == 0 && item.getDurability() != Coinz.CopperCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 1 && item.getDurability() != Coinz.HalfBronzeCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 2 && item.getDurability() != Coinz.BronzeCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 3 && item.getDurability() != Coinz.HalfSilverCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 4 && item.getDurability() != Coinz.SilverCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 5 && item.getDurability() != Coinz.HalfGoldCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 6 && item.getDurability() != Coinz.GoldCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 7 && item.getDurability() != Coinz.HalfPlatinumCoin.getDurability()) {
					event.setCancelled(true);
				}
				else if (slotNum == 8 && item.getDurability() != Coinz.PlatinumCoin.getDurability()) {
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
