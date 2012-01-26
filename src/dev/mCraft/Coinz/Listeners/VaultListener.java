package dev.mCraft.Coinz.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Event.Result;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryCloseEvent;
import org.getspout.spoutapi.event.inventory.InventoryOpenEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Blocks.Vault;
import dev.mCraft.Coinz.GUI.VaultInv.VaultInv;

public class VaultListener implements Listener {
	
	private Coinz plugin = Coinz.instance;
	private Vault vault = Vault.hook;
	private VaultInv vaultInv;
	
	private SpoutPlayer player;

	private Inventory inv;
	private Location loc;
	private ItemStack[] stack;
	
	private String invName;
	private int slotNum;
	private ItemStack cursor;
	private int amount;
	private ItemStack slot;
	
	public VaultListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void openingVaultInv(final InventoryOpenEvent event) {
		player = (SpoutPlayer) event.getPlayer();
		inv = event.getInventory();
		invName = inv.getName();
		vaultInv = new VaultInv();
		
		if (invName == "Vault") {
			plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

				@EventHandler(priority = EventPriority.NORMAL)
				public void run() {
					player = (SpoutPlayer) event.getPlayer();
					player.getCurrentScreen().attachWidget(plugin, vaultInv);
					
				}}, 10);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void closingVaultInv(InventoryCloseEvent event) {
		inv = event.getInventory();
		loc = event.getLocation();
		stack = inv.getContents();
		
		if (inv.getName() == "Vault") {
			vault.vaultInv.put(loc, inv.getContents());
			
			List<ItemStack> temp = new ArrayList<ItemStack>();
			temp.add(stack[0]);
			temp.add(stack[1]);
			temp.add(stack[2]);
			temp.add(stack[3]);
			temp.add(stack[4]);
			temp.add(stack[5]);
			temp.add(stack[6]);
			temp.add(stack[7]);
			temp.add(stack[8]);
			
			vault.vaults.set(loc.toString(), temp);
			plugin.saveConfig();
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void clickingVaultInv(InventoryClickEvent event) {
		player = (SpoutPlayer) event.getPlayer();
		inv = event.getInventory();
		invName = inv.getName();
		slotNum = event.getSlot();
		slot = inv.getItem(slotNum);
		amount = slot.getAmount();
		cursor = event.getCursor();
		
		if (invName == "Vault") {
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
			else if (cursor == null) {
				if (amount == 1) {
					event.setCancelled(true);
				}
				else if (amount > 1) {
					event.setResult(Result.DENY);
					slot.setType(Material.STONE);
					player.sendMessage("test: " + slot.getType());
				}
			}
		}
	}

}
