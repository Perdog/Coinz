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
		
		fm.getCache(plugin).clear();
		
		//fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/PluginImages.png");
		
		CoinzManager.getLogger().info(plugin.tag + " Block designs have been loaded");
		
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/CopperCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/HalfBronzeCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/BronzeCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/HalfSilverCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/SilverCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/HalfGoldCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/GoldCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/HalfPlatinumCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/PlatinumCoin.png");
		fm.addToPreLoginCache(plugin, "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/default_textures/GuiScreen.png");
		
		CoinzManager.getLogger().info(plugin.tag + " Item images have been loaded");
	}

}
