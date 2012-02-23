package dev.mCraft.Coinz.Blocks;

import org.getspout.spoutapi.block.design.Texture;

import dev.mCraft.Coinz.Coinz;

public class Blocks {
	
	private static Coinz plugin = Coinz.instance;
	public static Blocks hook;
	
	private String pack;
	public static String textureUrl;
	public static int spriteSize = 16;
	public static int textureSize = 256;

	public static Texture texture;
	
	public Teller teller;
	public Vault vault;
	
	public Blocks(String newPack) {
		hook = this;
		this.pack = newPack;
		
		textureUrl = "http://dl.dropbox.com/u/36338911/Coinz/Coinz_textures/" + pack + "/PluginImages.png";
		texture = new Texture(plugin, textureUrl, textureSize, textureSize, spriteSize);
		
		teller = new Teller();
		vault = new Vault();
	}
	
	public void setTexture(String newText) {
		textureUrl = newText;
	}

}
