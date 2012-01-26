package dev.mCraft.Coinz.Coins;

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
	
	public Items() {
		hook = this;
		
		copperCoin = new CopperCoin(plugin, "Copper coin", "spoutcraft/cache/Coinz/CopperCoin.png");
		halfbronzeCoin = new HalfBronzeCoin(plugin, "Half-Bronze coin", "spoutcraft/cache/Coinz/HalfBronzeCoin.png");
		bronzeCoin = new BronzeCoin(plugin, "Bronze coin", "spoutcraft/cache/Coinz/BronzeCoin.png");
		halfsilverCoin = new HalfSilverCoin(plugin, "Half-Silver coin", "spoutcraft/cache/Coinz/HalfSilverCoin.png");
		silverCoin = new SilverCoin(plugin, "Silver coin", "spoutcraft/cache/Coinz/SilverCoin.png");
		halfgoldCoin = new HalfGoldCoin(plugin, "Half-Gold coin", "spoutcraft/cache/Coinz/HalfGoldCoin.png");
		goldCoin = new GoldCoin(plugin, "Gold coin", "spoutcraft/cache/Coinz/GoldCoin.png");
		halfplatinumCoin = new HalfPlatinumCoin(plugin, "Half-Platinum coin", "spoutcraft/cache/Coinz/HalfPlatinumCoin.png");
		platinumCoin = new PlatinumCoin(plugin, "Platinum coin", "spoutcraft/cache/Coinz/PlatinumCoin.png");
	}

}
