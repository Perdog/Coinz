package dev.mCraft.Coinz.GUI.TellerMenu;

import org.bukkit.configuration.ConfigurationSection;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;

public class Popup extends GenericPopup {
	
	public Coinz plugin = Coinz.instance;
	private ConfigurationSection locale;
	
	public static Popup hook;
	
	//private GenericTexture backGround;
	public GenericButton escape;
	private GenericLabel title;
	public GenericTextField enter;
	public GenericButton deposit;
	public GenericButton withdraw;
	public GenericLabel amount;
	public GenericLabel notEnoughA;
	public GenericLabel notEnoughC;
	public GenericLabel wrongChange;

	private GenericTexture copper;
	private GenericTexture bronze;
	private GenericTexture silver;
	private GenericTexture gold;
	private GenericTexture platinum;
	
	private double balance = 0;
	
	public Popup(SpoutPlayer player) {
		hook = this;
		
		locale = plugin.getConfig().getConfigurationSection("Language");
		
		copper = new GenericTexture();
		bronze = new GenericTexture();
		silver = new GenericTexture();
		gold = new GenericTexture();
		platinum = new GenericTexture();
		
		//backGround = new GenericTexture();
		escape = new GenericButton();
		title = new GenericLabel();
		enter = new GenericTextField();
		deposit = new GenericButton();
		withdraw = new GenericButton();
		amount = new GenericLabel();
		notEnoughA = new GenericLabel();
		notEnoughC = new GenericLabel();
		wrongChange = new GenericLabel();
		
		Integer coinsize = 25;
		Integer coindist = 30;
		
		copper.setUrl("spoutcraft/cache/Coinz/CopperCoin.png");
		copper.setTooltip(locale.getString("ToolTips.CoppCoin"));
		copper.setX(150).setY(100);
		copper.setHeight(coinsize).setWidth(coinsize);
		copper.setPriority(RenderPriority.High);
		
		bronze.setUrl("spoutcraft/cache/Coinz/BronzeCoin.png");
		bronze.setTooltip(locale.getString("ToolTips.BronCoin"));
		bronze.setX(copper.getX() + coindist).setY(100);
		bronze.setHeight(coinsize).setWidth(coinsize);
		bronze.setPriority(RenderPriority.High);
		
		silver.setUrl("spoutcraft/cache/Coinz/SilverCoin.png");
		silver.setTooltip(locale.getString("ToolTips.SilvCoin"));
		silver.setX(bronze.getX() + coindist).setY(100);
		silver.setHeight(coinsize).setWidth(coinsize);
		silver.setPriority(RenderPriority.High);
		
		gold.setUrl("spoutcraft/cache/Coinz/GoldCoin.png");
		gold.setTooltip(locale.getString("ToolTips.GoldCoin"));
		gold.setX(silver.getX() + coindist).setY(100);
		gold.setHeight(coinsize).setWidth(coinsize);
		gold.setPriority(RenderPriority.High);
		
		platinum.setUrl("spoutcraft/cache/Coinz/PlatinumCoin.png");
		platinum.setTooltip(locale.getString("ToolTips.PlatCoin"));
		platinum.setX(gold.getX() + coindist).setY(100);
		platinum.setHeight(coinsize).setWidth(coinsize);
		platinum.setPriority(RenderPriority.High);
		
		//backGround.setUrl("spoutcraft/cache/Coinz/GuiScreen.png");
		//backGround.setX(141).setY(38);
		//backGround.setHeight(150).setWidth(160);
		//backGround.setPriority(RenderPriority.Highest);
		
		escape.setText("X").setColor(new Color(1.0F, 0, 0, 1.0F));
		escape.setTooltip(locale.getString("ToolTips.Escape"));
		escape.setX(287).setY(84);
		escape.setHeight(10).setWidth(10);
		escape.setPriority(RenderPriority.Normal);
		
		title.setTextColor(new Color(205F, 127F, 50F, 1.0F));
		title.setText(player.getName() + "'s bank");
		title.setX(186).setY(69);
		title.setHeight(20).setWidth(30);
		title.setPriority(RenderPriority.Normal);
		
		balance = plugin.econ.getBalance(player.getName());
		amount.setTextColor(new Color(0, 1.0F, 0, 1.0F));
		amount.setText(plugin.econ.format(balance));
		amount.setX(159).setY(87);
		amount.setHeight(20).setWidth(30);
		amount.setPriority(RenderPriority.Normal);
		
		enter.setHeight(9).setWidth(45);
		enter.setX(200).setY(131);
		enter.setPlaceholder(locale.getString("PlaceHolder"));
		enter.setMaximumCharacters(4);
		enter.setPriority(RenderPriority.Normal);
		
		deposit.setX(enter.getX() - 33).setY(enter.getY()-4);
		deposit.setHeight(14).setWidth(30);
		deposit.setText(locale.getString("Buttons.Deposit")).setTooltip(locale.getString("ToolTips.Deposit"));
		deposit.setPriority(RenderPriority.Normal);
		
		withdraw.setX(enter.getX() + 47).setY(enter.getY()-4);
		withdraw.setHeight(14).setWidth(33);
		withdraw.setText(locale.getString("Buttons.Withdraw")).setTooltip(locale.getString("ToolTips.Withdraw"));
		withdraw.setPriority(RenderPriority.Normal);
		
		this.attachWidgets(plugin, copper, bronze, silver, gold, platinum, /*backGround,*/ escape, title, amount, enter, deposit, withdraw);
		this.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		notEnoughA.setX(110).setY(150);
		notEnoughA.setHeight(5).setWidth(30);
		notEnoughA.setText(locale.getString("Errors.NotEnoughA")).setTextColor(new Color(1.0F, 0, 0, 1.0F));
		notEnoughA.setPriority(RenderPriority.Normal);
		
		notEnoughC.setX(110).setY(150);
		notEnoughC.setHeight(5).setWidth(30);
		notEnoughC.setText(locale.getString("Errors.NotEnoughC")).setTextColor(new Color(1.0F, 0, 0, 1.0F));
		notEnoughC.setPriority(RenderPriority.Normal);
		
		wrongChange.setX(110).setY(150);
		wrongChange.setHeight(5).setWidth(20);
		wrongChange.setText(locale.getString("Errors.WrongChange")).setTextColor(new Color(1.0F, 0, 0, 1.0F));
		wrongChange.setPriority(RenderPriority.Normal);
	}
}