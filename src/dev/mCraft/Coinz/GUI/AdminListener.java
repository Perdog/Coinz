package dev.mCraft.Coinz.GUI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Blocks.Blocks;
import dev.mCraft.Coinz.Coins.Items;
import dev.mCraft.Coinz.GUI.Admin.AdminPopup;

public class AdminListener implements Listener {
	
	private Coinz plugin = Coinz.instance;
	private AdminPopup hook;
	private Items items;
	private Blocks blocks;
	
	public AdminListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void radioClick(ButtonClickEvent event) {
		hook = AdminPopup.hook;
		
		if (hook == null) {
			return;
		}
		
		Button button = event.getButton();
		
		if (button.getText() != null && button.getPlugin() == plugin) {
			if (button.getId() == hook.r1.getId() || button.getId() == hook.r2.getId() || button.getId() == hook.r3.getId() || button.getId() == hook.r4.getId() || button.getId() == hook.other.getId()) {
				hook.apply.setEnabled(true);
				hook.setFields(false);
			}
			if (button.getId() == hook.other.getId()) {
				hook.setFields(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void doneAndCancel(ButtonClickEvent event) {
		hook = AdminPopup.hook;
		
		if ( hook == null) {
			return;
		}
		
		Button button = event.getButton();
		SpoutPlayer player = event.getPlayer();
		
		if (button.getText() != null && button.getPlugin() == plugin) {
			if (button.getId() == hook.done.getId()) {
				player.closeActiveWindow();
			}
			if (button.getId() == hook.cancel.getId()) {
				player.closeActiveWindow();
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void applyButton(ButtonClickEvent event) {
		hook = AdminPopup.hook;
		
		if (hook == null) {
			return;
		}
		
		items = Items.hook;
		blocks = Blocks.hook;
		
		Button button = event.getButton();
		
		if (button.getText() != null && button.getPlugin() == plugin) {
			if (button.getId() == hook.apply.getId()) {
				hook.apply.setEnabled(false);
				
				if (hook.r1.isSelected()) {
					new Items("Default_Textures");
					new Blocks("Default_Textures");
				}
				else if (hook.r2.isSelected()) {
					new Items("DG_Tal_Textures");
					new Blocks("DG_Tal_Textures");
				}
				else if (hook.r3.isSelected()) {
					new Items("Galloway_Textures");
					new Blocks("Galloway_Textures");
				}
				else if (hook.r4.isSelected()) {
					new Items("Bow_Textures");
					new Blocks("Bow_Textures");
				}
				else if (hook.other.isSelected()) {
					if (!hook.copp.getText().isEmpty()) {
						items.copperCoin.setTexture(hook.copp.getText());
					} else {
						items.copperCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/CopperCoin.png");
					}
					
					if (!hook.halfBron.getText().isEmpty()) {
						items.halfbronzeCoin.setTexture(hook.halfBron.getText());
					} else {
						items.halfbronzeCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/HalfBronzeCoin.png");
					}
					
					if (!hook.bron.getText().isEmpty()) {
						items.bronzeCoin.setTexture(hook.bron.getText());
					} else {
						items.bronzeCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/BronzeCoin.png");
					}
					
					if (!hook.halfSilv.getText().isEmpty()) {
						items.halfsilverCoin.setTexture(hook.halfSilv.getText());
					} else {
						items.halfsilverCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/HalfSilverCoin.png");
					}
					
					if (!hook.silv.getText().isEmpty()) {
						items.silverCoin.setTexture(hook.silv.getText());
					} else {
						items.silverCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/SilverCoin.png");
					}
					
					if (!hook.halfGold.getText().isEmpty()) {
						items.halfgoldCoin.setTexture(hook.halfGold.getText());
					} else {
						items.halfgoldCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/HalfGoldCoin.png");
					}
					
					if (!hook.gold.getText().isEmpty()) {
						items.goldCoin.setTexture(hook.gold.getText());
					} else {
						items.goldCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/GoldCoin.png");
					}
					
					if (!hook.halfPlat.getText().isEmpty()) {
						items.halfplatinumCoin.setTexture(hook.halfPlat.getText());
					} else {
						items.halfplatinumCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/HalfPlatinumCoin.png");
					}
					
					if (!hook.plat.getText().isEmpty()) {
						items.platinumCoin.setTexture(hook.plat.getText());
					} else {
						items.platinumCoin.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/PlatinumCoin.png");
					}
					
					if (!hook.blocks.getText().isEmpty()) {
						blocks.setTexture("http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/Default_Textures/PluginImages.png");
					}
				}
			}
		}
	}

}
