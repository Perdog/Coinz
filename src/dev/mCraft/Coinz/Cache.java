package dev.mCraft.Coinz;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.FileManager;

import dev.mCraft.Coinz.api.CoinzManager;

public class Cache {
	
	private Coinz plugin;
	
	private FileManager fm;
	
	public Cache() {
		plugin = Coinz.instance;
		
		fm = SpoutManager.getFileManager();
		
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/PluginImages.png"), "PluginImages.png");
		CoinzManager.getLogger().info(plugin.tag + " Block designs have been loaded");
		
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/CopperCoin.png"), "CopperCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/HalfBronzeCoin.png"), "HalfBronzeCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/BronzeCoin.png"), "BronzeCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/HalfSilverCoin.png"), "HalfSilverCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/SilverCoin.png"), "SilverCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/HalfGoldCoin.png"), "HalfGoldCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/GoldCoin.png"), "GoldCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/HalfPlatinumCoin.png"), "HalfPlatinumCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/PlatinumCoin.png"), "PlatinumCoin.png");
		fm.addToPreLoginCache(plugin, plugin.getResource("Images/GuiScreen.png"), "GuiScreen.png");
		
		CoinzManager.getLogger().info(plugin.tag + " Item images have been loaded");
	}

}
