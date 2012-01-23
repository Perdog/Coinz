package dev.mCraft.Coinz;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MyPlayerListener extends PlayerListener {
	
	private Main plugin = Main.instance;
	
	private SpoutPlayer payer;
	private Entity clicked;
	private SpoutPlayer reciever;

	private ItemStack hand;
	private SpoutItemStack item;
	private short dur;
	
	//private VaultInv vault;
	private Action action;
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		//for (String file : fm.getCache(plugin)) {
			//File image = new File(file);
			//image.delete();
		//}
	}
	
	@Override
	public void onPlayerInteract(PlayerInteractEvent event) {
		//SpoutPlayer player = (SpoutPlayer) event.getPlayer();
		Block block = event.getClickedBlock();
		action = event.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.DIRT) {
			//vault = new VaultInv(player);
			//player.getMainScreen().attachPopupScreen(vault);
			//player.sendMessage("Clicking dirt");
		}
	}
	
	@Override
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		payer = (SpoutPlayer)event.getPlayer();
		clicked = event.getRightClicked();
		hand = payer.getItemInHand();
		item = new SpoutItemStack(hand);
		dur = item.getDurability();
		
		if (clicked instanceof SpoutPlayer) {
			reciever = (SpoutPlayer)clicked;
			if (item.isCustomItem()) {
				if (!reciever.getInventory().contains(item, 1) && reciever.getInventory().firstEmpty() == -1) {
					payer.sendMessage(reciever.getName() + "'s inventory is full, you cannot give them any money");
					reciever.sendMessage("You can't get paid because your inventory is full!");
				}
				else {
					if (dur == plugin.CopperCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.CopperCoin);
						reciever.getInventory().addItem(plugin.CopperCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.BronzeCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.BronzeCoin);
						reciever.getInventory().addItem(plugin.BronzeCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.SilverCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.SilverCoin);
						reciever.getInventory().addItem(plugin.SilverCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.GoldCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.GoldCoin);
						reciever.getInventory().addItem(plugin.GoldCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
					if (dur == plugin.PlatinumCoin.getDurability()) {
						payer.getInventory().removeItem(plugin.PlatinumCoin);
						reciever.getInventory().addItem(plugin.PlatinumCoin);
						payer.sendMessage("Payment recieved");
						reciever.sendMessage("Payment recieved");
					}
				}
			}
		}
	}

}
