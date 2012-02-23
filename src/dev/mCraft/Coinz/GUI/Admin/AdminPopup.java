package dev.mCraft.Coinz.GUI.Admin;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericRadioButton;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.RenderPriority;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.Coins.Items;
import dev.mCraft.Coinz.Lang.CLang;
import dev.mCraft.Coinz.api.CoinzManager;

public class AdminPopup extends GenericPopup {
	
	private Coinz plugin = Coinz.instance;
	public static AdminPopup hook = null;
	
	private GenericContainer side;
	
	public GenericRadioButton r1;
	public GenericRadioButton r2;
	public GenericRadioButton r3;
	public GenericRadioButton r4;
	public GenericRadioButton other;
	
	public GenericTextField copp;
	public GenericTextField halfBron;
	public GenericTextField bron;
	public GenericTextField halfSilv;
	public GenericTextField silv;
	public GenericTextField halfGold;
	public GenericTextField gold;
	public GenericTextField halfPlat;
	public GenericTextField plat;
	public GenericTextField blocks;
	
	public GenericButton done;
	public GenericButton apply;
	public GenericButton cancel;
	
	private int radioX = 150;
	private int radioY = 70;
	
	private int fieldX = 50;
	private int fieldY = 70;
	
	public AdminPopup() {
		hook = this;
		
		//Creating the buttons and stuff
		side = new GenericContainer();
		
		r1 = new GenericRadioButton();
		r2 = new GenericRadioButton();
		r3 = new GenericRadioButton();
		r4 = new GenericRadioButton();
		other = new GenericRadioButton();
		
		copp = new GenericTextField();
		halfBron = new GenericTextField();
		bron = new GenericTextField();
		halfSilv = new GenericTextField();
		silv = new GenericTextField();
		halfGold = new GenericTextField();
		gold = new GenericTextField();
		halfPlat = new GenericTextField();
		plat = new GenericTextField();
		blocks = new GenericTextField();
		
		done = new GenericButton();
		apply = new GenericButton();
		cancel = new GenericButton();
		
		//Setting variables for the radio buttons
		r1.setX(radioX).setY(radioY);
		r1.setHeight(12).setWidth(12);
		r1.setText(CLang.lookup("radio_default"));
		r1.setGroup(4);
		r1.setPriority(RenderPriority.Normal);
		
		r2.setX(radioX).setY(radioY+15);
		r2.setHeight(12).setWidth(12);
		r2.setText(CLang.lookup("radio_dg_tal"));
		r2.setGroup(4);
		r2.setPriority(RenderPriority.Normal);
		
		r3.setX(radioX).setY(radioY+30);
		r3.setHeight(12).setWidth(12);
		r3.setText(CLang.lookup("radio_galloway"));
		r3.setGroup(4);
		r3.setPriority(RenderPriority.Normal);
		
		r4.setX(radioX).setY(radioY+45);
		r4.setHeight(12).setWidth(12);
		r4.setText(CLang.lookup("radio_bow"));
		r4.setGroup(4);
		r4.setPriority(RenderPriority.Normal);
		
		other.setX(radioX).setY(radioY+60);
		other.setHeight(12).setWidth(12);
		other.setText(CLang.lookup("radio_other"));
		other.setGroup(4);
		other.setPriority(RenderPriority.Normal);
		
		//Setting variables for the text fields
		copp.setX(fieldX).setY(fieldY);
		copp.setHeight(10).setWidth(80);
		copp.setPlaceholder(CLang.lookup("placeholder_copp_url"));
		
		halfBron.setX(fieldX).setY(fieldY+15);
		halfBron.setHeight(10).setWidth(80);
		halfBron.setPlaceholder(CLang.lookup("placeholder_hbron_url"));
		
		bron.setX(fieldX).setY(fieldY+30);
		bron.setHeight(10).setWidth(80);
		bron.setPlaceholder(CLang.lookup("placeholder_bron_url"));
		
		halfSilv.setX(fieldX).setY(fieldY+45);
		halfSilv.setHeight(10).setWidth(80);
		halfSilv.setPlaceholder(CLang.lookup("placeholder_hsilv_url"));
		
		silv.setX(fieldX).setY(fieldY+60);
		silv.setHeight(10).setWidth(80);
		silv.setPlaceholder(CLang.lookup("placeholder_silv_url"));
		
		halfGold.setX(fieldX).setY(fieldY+75);
		halfGold.setHeight(10).setWidth(80);
		halfGold.setPlaceholder(CLang.lookup("placeholder_hgold_url"));
		
		gold.setX(fieldX).setY(fieldY+90);
		gold.setHeight(10).setWidth(80);
		gold.setPlaceholder(CLang.lookup("placeholder_gold_url"));
		
		halfPlat.setX(fieldX).setY(fieldY+105);
		halfPlat.setHeight(10).setWidth(80);
		halfPlat.setPlaceholder(CLang.lookup("placeholder_hplat_url"));
		
		plat.setX(fieldX).setY(fieldY+120);
		plat.setHeight(10).setWidth(80);
		plat.setPlaceholder(CLang.lookup("placeholder_plat_url"));
		
		blocks.setX(fieldX).setY(fieldY+135);
		blocks.setHeight(10).setWidth(80);
		blocks.setPlaceholder(CLang.lookup("placeholder_blocks_url"));
		
		//Setting variables for the buttons
		done.setText(CLang.lookup("button_done"));
		apply.setText(CLang.lookup("button_apply")).setTooltip(CLang.lookup("tooltip_apply"));
		apply.setEnabled(false);
		cancel.setText(CLang.lookup("button_cancel"));
		
		//Attach widgets to the containers
		side.setX(300).setY(150);
		side.setHeight(50).setWidth(30);
		side.addChildren(done, apply, cancel);
		
		this.attachWidgets(plugin, r1, r2, r3, r4, other, copp, halfBron, bron, halfSilv, silv, halfGold, gold, halfPlat, plat, side);
		
		//Returning buttons to their previous selection and disabling the fields if they aren't needed
		String text = CoinzManager.getCopperCoin().getTexture();
		Items items = Items.hook;
		if (text.contains("36338911") && items.getPack().equalsIgnoreCase("Default_Textures")) {
			r1.setSelected(true);
		}
		else if (text.contains("36338911") && items.getPack().equalsIgnoreCase("DG_Tal_Textures")) {
			r2.setSelected(true);
		}
		else if (text.contains("36338911") && items.getPack().equalsIgnoreCase("Galloway_Textures")) {
			r3.setSelected(true);
		}
		else if (text.contains("36338911") && items.getPack().equalsIgnoreCase("Bow_Textures")) {
			r4.setSelected(true);
		}
		else {
			other.setSelected(true);
		}
		
		if (r1.isSelected() || r2.isSelected() || r3.isSelected() || r4.isSelected()) {
			setFields(false);
			if (!copp.getText().isEmpty()) {
				copp.setText("");
				halfBron.setText("");
				bron.setText("");
				halfSilv.setText("");
				silv.setText("");
				halfGold.setText("");
				gold.setText("");
				halfPlat.setText("");
				plat.setText("");
				blocks.setText("");
			}
		}
		else if (other.isSelected()) {
			setFields(true);
			copp.setText(CoinzManager.getCopperCoin().getTexture());
			halfBron.setText(CoinzManager.getHalfBronzeCoin().getTexture());
			bron.setText(CoinzManager.getBronzeCoin().getTexture());
			halfSilv.setText(CoinzManager.getHalfSilverCoin().getTexture());
			silv.setText(CoinzManager.getSilverCoin().getTexture());
			halfGold.setText(CoinzManager.getHalfGoldCoin().getTexture());
			gold.setText(CoinzManager.getGoldCoin().getTexture());
			halfPlat.setText(CoinzManager.getHalfPlatinumCoin().getTexture());
			plat.setText(CoinzManager.getPlatinumCoin().getTexture());
			blocks.setText(CoinzManager.getTellerBlock().getBlockDesign().getTexureURL());
		}
	}
	
	public void setFields(boolean state) {
		if (!state) {
			copp.setText("");
			halfBron.setText("");
			bron.setText("");
			halfSilv.setText("");
			silv.setText("");
			halfGold.setText("");
			gold.setText("");
			halfPlat.setText("");
			plat.setText("");
			blocks.setText("");
		}
		copp.setEnabled(state);
		halfBron.setEnabled(state);
		bron.setEnabled(state);
		halfSilv.setEnabled(state);
		silv.setEnabled(state);
		halfGold.setEnabled(state);
		gold.setEnabled(state);
		halfPlat.setEnabled(state);
		plat.setEnabled(state);
		blocks.setEnabled(state);
	}

}
