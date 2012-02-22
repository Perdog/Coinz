package dev.mCraft.Coinz.GUI.Keypad;

import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Lang.CLang;

public class KeypadPopup extends GenericPopup {
	public Coinz plugin = Coinz.instance;
	public static KeypadPopup hook;
	
	private GenericTexture background;
	
	public GenericTextField pass;
	public GenericButton b1;
	public GenericButton b2;
	public GenericButton b3;
	public GenericButton b4;
	public GenericButton b5;
	public GenericButton b6;
	public GenericButton b7;
	public GenericButton b8;
	public GenericButton b9;
	public GenericButton star;
	public GenericButton b0;
	public GenericButton pound;
	
	public GenericButton corr;
	public GenericButton enter;
	public GenericButton cancel;
	
	private int X;
	private int Y;
	
	private int bheight;
	private int bwidth;
	
	public KeypadPopup() {
		hook = this;
		
		background = new GenericTexture();
		
		pass = new GenericTextField();
		b1 = new GenericButton();
		b2 = new GenericButton();
		b3 = new GenericButton();
		b4 = new GenericButton();
		b5 = new GenericButton();
		b6 = new GenericButton();
		b7 = new GenericButton();
		b8 = new GenericButton();
		b9 = new GenericButton();
		star = new GenericButton();
		b0 = new GenericButton();
		pound = new GenericButton();
		
		corr = new GenericButton();
		enter = new GenericButton();
		cancel = new GenericButton();
		
		X = 165;
		Y = 88;
		
		bheight = 13;
		bwidth = 20;
		
		background.setUrl("spoutcraft/cache/Coinz/KeyPad.png");
		background.setX(X-5).setY(Y-5);
		background.setHeight(59).setWidth(115);
		background.setPriority(RenderPriority.Low);
		
		pass.setEnabled(false);
		pass.setMaximumCharacters(10);
		pass.setX(X+1).setY(Y-20);
		pass.setHeight(15).setWidth(68);
		pass.setPriority(RenderPriority.Normal);
		
		b1.setText("1");
		b1.setX(X).setY(Y);
		b1.setHeight(bheight).setWidth(bwidth);
		b1.setPriority(RenderPriority.Normal);
		
		b2.setText("2");
		b2.setX(X+25).setY(Y);
		b2.setHeight(bheight).setWidth(bwidth);
		b2.setPriority(RenderPriority.Normal);
		
		b3.setText("3");
		b3.setX(X+50).setY(Y);
		b3.setHeight(bheight).setWidth(bwidth);
		b3.setPriority(RenderPriority.Normal);
		
		b4.setText("4");
		b4.setX(X).setY(Y+18);
		b4.setHeight(bheight).setWidth(bwidth);
		b4.setPriority(RenderPriority.Normal);
		
		b5.setText("5");
		b5.setX(X+25).setY(Y+18);
		b5.setHeight(bheight).setWidth(bwidth);
		b5.setPriority(RenderPriority.Normal);
		
		b6.setText("6");
		b6.setX(X+50).setY(Y+18);
		b6.setHeight(bheight).setWidth(bwidth);
		b6.setPriority(RenderPriority.Normal);
		
		b7.setText("7");
		b7.setX(X).setY(Y+36);
		b7.setHeight(bheight).setWidth(bwidth);
		b7.setPriority(RenderPriority.Normal);
		
		b8.setText("8");
		b8.setX(X+25).setY(Y+36);
		b8.setHeight(bheight).setWidth(bwidth);
		b8.setPriority(RenderPriority.Normal);
		
		b9.setText("9");
		b9.setX(X+50).setY(Y+36);
		b9.setHeight(bheight).setWidth(bwidth);
		b9.setPriority(RenderPriority.Normal);
		
		star.setText("*");
		star.setX(X).setY(Y+54);
		star.setHeight(bheight).setWidth(bwidth);
		star.setPriority(RenderPriority.Normal);
		
		b0.setText("0");
		b0.setX(X+25).setY(Y+54);
		b0.setHeight(bheight).setWidth(bwidth);
		b0.setPriority(RenderPriority.Normal);
		
		pound.setText("#");
		pound.setX(X+50).setY(Y+54);
		pound.setHeight(bheight).setWidth(bwidth);
		pound.setPriority(RenderPriority.Normal);
		
		corr.setText(CLang.lookup("button_corr")).setColor(new Color(1.0F, 1.0F, 0, 1.0F));
		corr.setX(X+80).setY(Y);
		corr.setHeight(bheight).setWidth(bwidth+5);
		corr.setPriority(RenderPriority.Normal);
		
		enter.setText(CLang.lookup("button_enter"));
		enter.setX(X+80).setY(Y+27);
		enter.setHeight(bheight).setWidth(bwidth+5);
		enter.setPriority(RenderPriority.Normal);
		
		cancel.setText(CLang.lookup("button_cancel")).setColor(new Color(1.0F, 0, 0, 1.0F));
		cancel.setX(X+80).setY(Y+54);
		cancel.setHeight(bheight).setWidth(bwidth+5);
		cancel.setPriority(RenderPriority.Normal);
		
		this.attachWidgets(plugin, background, pass, b1, b2, b3, b4, b5, b6, b7, b8, b9, star, b0, pound, corr, enter, cancel);
	}

}
