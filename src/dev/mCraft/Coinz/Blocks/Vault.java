package dev.mCraft.Coinz.Blocks;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.World;
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
		Location loc = new Location(world, x, y ,z);
		Inventory test = SpoutManager.getInventoryBuilder().construct(vaultInv.get(loc), "Vault");
		return player.openInventoryWindow(test, loc);
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
		
		Location loc = new Location(world, x, y, z);
		
		builder.construct(stack, "Vault");
		
		vaultInv.put(loc, stack);
		
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
		Location loc = new Location(world, x, y, z);
		
		vaultInv.remove(loc);
	}
}
