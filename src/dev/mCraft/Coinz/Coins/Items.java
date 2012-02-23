package dev.mCraft.Coinz.Coins;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.CustomItem;

import dev.mCraft.Coinz.Coinz;

public class Items {
	
	private Coinz plugin = Coinz.instance;
	public static Items hook;
	
	public CustomItem copperCoin;
	public CustomItem halfbronzeCoin;
	public CustomItem bronzeCoin;
	public CustomItem halfsilverCoin;
	public CustomItem silverCoin;
	public CustomItem halfgoldCoin;
	public CustomItem goldCoin;
	public CustomItem halfplatinumCoin;
	public CustomItem platinumCoin;
	
	public String pack;
	
	public Items(String textPack) {
		hook = this;
		
		pack = textPack;
		
		SpoutManager.getFileManager().getCache(plugin).clear();
		
		copperCoin = new CopperCoin(plugin, "Copper coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/CopperCoin.png");
		halfbronzeCoin = new HalfBronzeCoin(plugin, "Half-Bronze coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/HalfBronzeCoin.png");
		bronzeCoin = new BronzeCoin(plugin, "Bronze coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/BronzeCoin.png");
		halfsilverCoin = new HalfSilverCoin(plugin, "Half-Silver coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/HalfSilverCoin.png");
		silverCoin = new SilverCoin(plugin, "Silver coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/SilverCoin.png");
		halfgoldCoin = new HalfGoldCoin(plugin, "Half-Gold coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/HalfGoldCoin.png");
		goldCoin = new GoldCoin(plugin, "Gold coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/GoldCoin.png");
		halfplatinumCoin = new HalfPlatinumCoin(plugin, "Half-Platinum coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/HalfPlatinumCoin.png");
		platinumCoin = new PlatinumCoin(plugin, "Platinum coin", "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/PlatinumCoin.png");
	}
	
	public String getPack() {
		return pack;
	}
	
	public void setPack(String newPack) {
		pack = newPack;
	}

}
