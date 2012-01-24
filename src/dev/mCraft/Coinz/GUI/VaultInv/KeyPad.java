package dev.mCraft.Coinz.GUI.VaultInv;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

public class KeyPad extends GenericPopup {
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
	
	public KeyPad() {
		hook = this;
		
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
	}

}
