package dev.mCraft.Coinz.Blocks;

import org.getspout.spoutapi.block.design.Texture;

import dev.mCraft.Coinz.Coinz;

public class Blocks {
	
	private static Coinz plugin = Coinz.instance;
	public static Blocks hook;
	
	public static String textureUrl = "spoutcraft/cache/Coinz/PluginImages.png";
	public static int spriteSize = 16;
	public static int textureSize = 256;

	public static Texture texture = new Texture(plugin, textureUrl, textureSize, textureSize, spriteSize);
	
	public Teller teller;
	public Vault vault;
	
	public Blocks() {
		instance();
		
		teller = new Teller();
		vault = new Vault();
	}

	private void instance() {
		hook = this;
	}

}
