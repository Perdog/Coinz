package dev.mCraft.Coinz.Blocks;

import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Main;
import dev.mCraft.Coinz.GUI.TellerMenu.Popup;

public class Teller extends GenericCuboidCustomBlock {
	
	private static Main plugin = Main.instance;
	
	public static int[] textID = {0, 1, 1, 1, 1, 2};
	private Popup popup;

	private ItemStack item;
	private SpoutItemStack sitem;
	private short dur;
	
	public Teller() {
		super(plugin, "Teller", true, new Design(textID));
		setHardness(MaterialData.log.getHardness());
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		popup = new Popup(player);
		return player.getMainScreen().attachPopupScreen(popup);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
		item = player.getItemInHand();
		sitem = new SpoutItemStack(item);
		dur = sitem.getDurability();
		
		if (dur == plugin.CopperCoin.getDurability()) {
			player.getInventory().removeItem(plugin.CopperCoin);
			plugin.econ.depositPlayer(player.getName(), 0.1);
			player.sendNotification("0.1 dollar", "added to your account", plugin.CopperCoin, 1700);
		}
		if (dur == plugin.BronzeCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.bronzeCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 1);
			player.sendNotification("1 dollar", "added to your account", plugin.BronzeCoin, 1700);
		}
		if (dur == plugin.SilverCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.silverCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 10);
			player.sendNotification("10 dollars", "added to your account", plugin.SilverCoin, 1700);
		}
		if (dur == plugin.GoldCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.goldCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 100);
			player.sendNotification("100 dollars", "added to your account", plugin.GoldCoin, 1700);
		}
		if (dur == plugin.PlatinumCoin.getDurability()) {
			player.getInventory().removeItem(new SpoutItemStack(plugin.platinumCoin, 1));
			plugin.econ.depositPlayer(player.getName(), 1000);
			player.sendNotification("1000 dollars", "added to your account", plugin.PlatinumCoin, 1700);
		}
	}

}
