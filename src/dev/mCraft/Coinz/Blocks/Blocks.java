package dev.mCraft.Coinz.Blocks;

import org.getspout.spoutapi.block.design.Texture;

import dev.mCraft.Coinz.Main;

public class Blocks {
	
	private static Main plugin = Main.instance;
	public static Blocks hook;
	
	public static String textureUrl = "spoutcraft/cache/Coinz/PluginImages.png";
	public static int spriteSize = 16;
	public static int textureSize = 256;

	public static Texture texture = new Texture(plugin, textureUrl, textureSize, textureSize, spriteSize);
	
	public Teller teller;
	public Vault vault;
	
	public Blocks() {
		hook = this;
		
		teller = new Teller();
		vault = new Vault();
	}

}