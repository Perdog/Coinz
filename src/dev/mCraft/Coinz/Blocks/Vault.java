package dev.mCraft.Coinz.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.InventoryBuilder;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Main;

public class Vault extends GenericCuboidCustomBlock {
	
	private static Main plugin = Main.instance;
	public static Vault hook;
	public ConfigurationSection vaults = plugin.getConfig().getConfigurationSection("Vaults");
	
	public static int[] textID = {16, 17, 16, 16, 16, 16};
	
	private InventoryBuilder builder;
	
	public HashMap<Location, ItemStack[]> vaultInv = new HashMap<Location, ItemStack[]>();
	
	public Vault() {
		super(plugin, "Vault", true, new Design(textID));
		setHardness(MaterialData.furnace.getHardness());
		hook = this;
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		plugin.reloadConfig();
		Location loc = new Location(world, x, y ,z);
		ItemStack[] stack = new ItemStack[9];
		@SuppressWarnings("unchecked")
		List<ItemStack> asd = vaults.getList(loc.toString());
		
		stack[0] = asd.get(1);
		stack[1] = asd.get(2);
		stack[2] = asd.get(3);
		stack[3] = asd.get(4);
		stack[4] = asd.get(5);
		stack[5] = asd.get(6);
		stack[6] = asd.get(7);
		stack[7] = asd.get(8);
		stack[8] = asd.get(9);
		
		Inventory Vault = SpoutManager.getInventoryBuilder().construct(stack, "Vault");
		return player.openInventoryWindow(Vault, loc);
	}
	
	@Override
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity entity) {
		builder = SpoutManager.getInventoryBuilder();
		
		ItemStack[] stack = new ItemStack[9];
		stack[0] = plugin.CopperCoin;
		stack[1] = plugin.HalfBronzeCoin;
		stack[2] = plugin.BronzeCoin;
		stack[3] = plugin.HalfSilverCoin;
		stack[4] = plugin.SilverCoin;
		stack[5] = plugin.HalfGoldCoin;
		stack[6] = plugin.GoldCoin;
		stack[7] = plugin.HalfPlatinumCoin;
		stack[8] = plugin.PlatinumCoin;
		
		List<ItemStack[]> temp = new ArrayList<ItemStack[]>();
		temp.add(stack);
		
		
		Location loc = new Location(world, x, y, z);
		builder.construct(stack, "Vault");
		
		vaultInv.put(loc, stack);
		vaults.set(loc.toString(), temp);
		plugin.saveConfig();
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
		Location loc = new Location(world, x, y, z);
		
		vaultInv.remove(loc);
		vaults.set(loc.toString(), null);
		plugin.saveConfig();
	}
}
