package dev.mCraft.Coinz.Blocks;

import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.GUI.TellerMenu.TellerPopup;

public class Teller extends GenericCuboidCustomBlock {
	
	private static Coinz plugin = Coinz.instance;
	
	public static int[] textID = {0, 1, 1, 1, 1, 2};
	private TellerPopup tellerPopup;

	private ItemStack item;
	private SpoutItemStack sitem;
	private short dur;
	
	public Teller() {
		super(plugin, "Teller", true, new Design(textID));
		setHardness(MaterialData.log.getHardness());
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		tellerPopup = new TellerPopup(player);
		return player.getMainScreen().attachPopupScreen(tellerPopup);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
		item = player.getItemInHand();
		sitem = new SpoutItemStack(item);
		dur = sitem.getDurability();
		
		if (dur == Coinz.CopperCoin.getDurability()) {
			player.getInventory().removeItem(Coinz.CopperCoin);
			plugin.econ.depositPlayer(player.getName(), 0.1);
			player.sendNotification("0.1 dollar", "added to your account", Coinz.CopperCoin, 1700);
		}
		if (dur == Coinz.BronzeCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.bronzeCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 1);
			player.sendNotification("1 dollar", "added to your account", Coinz.BronzeCoin, 1700);
		}
		if (dur == Coinz.SilverCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.silverCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 10);
			player.sendNotification("10 dollars", "added to your account", Coinz.SilverCoin, 1700);
		}
		if (dur == Coinz.GoldCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.goldCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 100);
			player.sendNotification("100 dollars", "added to your account", Coinz.GoldCoin, 1700);
		}
		if (dur == Coinz.PlatinumCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.platinumCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 1000);
			player.sendNotification("1000 dollars", "added to your account", Coinz.PlatinumCoin, 1700);
		}
	}

}
