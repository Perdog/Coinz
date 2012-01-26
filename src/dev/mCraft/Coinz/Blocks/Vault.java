package dev.mCraft.Coinz.Blocks;

import java.util.HashMap;
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

import dev.mCraft.Coinz.Coinz;

public class Vault extends GenericCuboidCustomBlock {
	
	private static Coinz plugin = Coinz.instance;
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
		builder = SpoutManager.getInventoryBuilder();
		Location loc = new Location(world, x, y ,z);
		ItemStack[] stack = vaultInv.get(loc);
		
		Inventory Vault = builder.construct(stack, "Vault");
		return player.openInventoryWindow(Vault, loc);
	}
	
	@Override
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity entity) {
		Location loc = new Location(world, x, y, z);
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
		
		builder.construct(stack, "Vault");
		vaultInv.put(loc, stack);
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
		Location loc = new Location(world, x, y, z);
		
		vaultInv.remove(loc);
	}
}
