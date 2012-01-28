package dev.mCraft.Coinz.GUI.VaultInv;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

import dev.mCraft.Coinz.Coinz;

public class KeyPad extends GenericPopup {
	public Coinz plugin = Coinz.instance;
	public static KeyPad hook;
	
	private GenericTexture background;
	public GenericTextField pass;
	public GenericButton b0;
	public GenericButton b1;
	public GenericButton b2;
	public GenericButton b3;
	public GenericButton b4;
	public GenericButton b5;
	public GenericButton b6;
	public GenericButton b7;
	public GenericButton b8;
	public GenericButton b9;
	public GenericButton pound;
	public GenericButton star;
	public GenericButton enter;
	
	public KeyPad() {
		hook = this;
		
		
		background = new GenericTexture();
		pass = new GenericTextField();
		b0 = new GenericButton();
		b1 = new GenericButton();
		b2 = new GenericButton();
		b3 = new GenericButton();
		b4 = new GenericButton();
		b5 = new GenericButton();
		b6 = new GenericButton();
		b7 = new GenericButton();
		b8 = new GenericButton();
		b9 = new GenericButton();
		pound = new GenericButton();
		star = new GenericButton();
		enter = new GenericButton();
		
		
		background.setUrl("path");
		background.setPriority(RenderPriority.Low);
		
		pass.setEnabled(false);
		
		b0.setText("0");
		
		b1.setText("1");
		
		b2.setText("2");
		
		b3.setText("3");
		
		b4.setText("4");
		
		b5.setText("5");
		
		b6.setText("6");
		
		b7.setText("7");
		
		b8.setText("8");
		
		b9.setText("9");
		
		pound.setText("#");
		
		star.setText("*");
		
		enter.setText("Enter");
		
		this.attachWidgets(plugin, background, pass, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, pound, star, enter);
	}

}
