package dev.mCraft.Coinz.GUI.VaultInv;

import org.bukkit.inventory.Inventory;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

import dev.mCraft.Coinz.Main;

public class VaultInv extends GenericContainer {
	
	public Main plugin = Main.instance;
	
	public static VaultInv hook;
	
	public GenericTexture iw1;
	public GenericTexture iw2;
	public GenericTexture iw3;
	public GenericTexture iw4;
	public GenericTexture iw5;
	public GenericTexture iw6;
	public GenericTexture iw7;
	public GenericTexture iw8;
	public GenericTexture iw9;
	
	public Inventory vaultInv;
	
	private int X1 = 193;
	private int X2 = X1 + 19;
	private int X3 = X2 + 19;
	private int Y1 = 87;
	private int Y2 = Y1 + 19;
	private int Y3 = Y2 + 19;
	
	private int h = 2;
	private int w = 2;
	
	public VaultInv() {
		hook = this;
		
		iw1 = new GenericTexture();
		iw2 = new GenericTexture();
		iw3 = new GenericTexture();
		iw4 = new GenericTexture();
		iw5 = new GenericTexture();
		iw6 = new GenericTexture();
		iw7 = new GenericTexture();
		iw8 = new GenericTexture();
		iw9 = new GenericTexture();

		iw1.setHeight(h).setWidth(w);
		iw1.setUrl("spoutcraft/cache/Coinz/CopperCoin.png");
		iw1.setLeft(30).setTop(85);
		iw1.setPriority(RenderPriority.Lowest);
		
		iw2.setHeight(h).setWidth(w);
		iw2.setUrl("spoutcraft/cache/Coinz/HalfBronzeCoin.png");
		iw2.setX(X2).setY(Y1);
		iw2.setPriority(RenderPriority.Normal);
		
		iw3.setHeight(h).setWidth(w);
		iw3.setUrl("spoutcraft/cache/Coinz/BronzeCoin.png");
		iw3.setX(X3).setY(Y1);
		iw3.setPriority(RenderPriority.Highest);
		
		iw4.setHeight(h).setWidth(w);
		iw4.setUrl("spoutcraft/cache/Coinz/HalfSilverCoin.png");
		iw4.setX(X1).setY(Y2);
		iw4.setPriority(RenderPriority.Normal);
		
		iw5.setHeight(h).setWidth(w);
		iw5.setUrl("spoutcraft/cache/Coinz/SilverCoin.png");
		iw5.setX(X2).setY(Y2);
		iw5.setPriority(RenderPriority.Normal);
		
		iw6.setHeight(h).setWidth(w);
		iw6.setUrl("spoutcraft/cache/Coinz/HalfGoldCoin.png");
		iw6.setX(X3).setY(Y2);
		iw6.setPriority(RenderPriority.Normal);
		
		iw7.setHeight(h).setWidth(w);
		iw7.setUrl("spoutcraft/cache/Coinz/GoldCoin.png");
		iw7.setX(X1).setY(Y3);
		iw7.setPriority(RenderPriority.Normal);
		
		iw8.setHeight(h).setWidth(w);
		iw8.setUrl("spoutcraft/cache/Coinz/HalfPlatinumCoin.png");
		iw8.setX(X2).setY(Y3);
		iw8.setPriority(RenderPriority.Normal);
		
		iw9.setHeight(h).setWidth(w);
		iw9.setUrl("spoutcraft/cache/Coinz/PlatinumCoin.png");
		iw9.setX(X3).setY(Y3);
		iw9.setPriority(RenderPriority.Normal);
		
		this.addChildren(iw1, iw2, iw3, iw4, iw5, iw6, iw7, iw8, iw9);
	}
	
}
