package dev.mCraft.Coinz.api;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.GUI.TellerMenu.TellerPopup;
import dev.mCraft.Coinz.api.Events.Teller.TellerDepositEvent;
import dev.mCraft.Coinz.api.Events.Teller.TellerWithdrawEvent;

public class Customer {
	private Coinz plugin = Coinz.instance;
	private TellerPopup tellerPopup = TellerPopup.hook;
	
	private SpoutItemStack copp;
	private SpoutItemStack bron;
	private SpoutItemStack silv;
	private SpoutItemStack gold;
	private SpoutItemStack plat;
	
	private SpoutPlayer player;
	private PlayerInventory inv;
	private SpoutItemStack stack;
	private short dur;
	private BigDecimal temp;
	
	private GenericTextField enter = tellerPopup.enter;
	private GenericLabel balance = tellerPopup.amount;
	
	/**
	 * Constructor for customer.
	 * <br>
	 * <br>
	 * To use you need to do:
	 * <br>
	 * <code>private Customer customer = new Customer(Spoutplayer);</code>
	 * @param player SpoutPlayer
	 */
	public Customer(SpoutPlayer player) {
		this.player = player;
		this.inv = player.getInventory();
	}
	
	/**
	 * Tries to deposit money into the players account and remove the coins from the inventory
	 * @param deposit Double
	 */
	public void depositCoins(double deposit) {
		
		//Calling the deposit event
		TellerDepositEvent event = new TellerDepositEvent(player, deposit);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		double amount = event.getAmount();
		
		if (!event.isCancelled() && hasEnoughCoins(amount)) {
			temp = BigDecimal.valueOf(amount);
			
			for (ItemStack item : inv.getContents()) {
				if (item != null) {
					stack = new SpoutItemStack(item);
					dur = stack.getDurability();
					
					if (stack.isCustomItem()) {
						if (dur == CoinzManager.getCopperStack().getDurability()) {
							copp = stack;
						}
						
						if (dur == CoinzManager.getBronzeStack().getDurability()) {
							bron = stack;
						}
						
						if (dur == CoinzManager.getSilverStack().getDurability()) {
							silv = stack;
						}
						
						if (dur == CoinzManager.getGoldStack().getDurability()) {
							gold = stack;
						}
						
						if (dur == CoinzManager.getPlatinumStack().getDurability()) {
							plat = stack;
						}
					}
				}
			}
			
			if (plat != null) {
				while (plat.getAmount() >=1 && temp.doubleValue() >= 1000) {
					inv.removeItem(CoinzManager.getPlatinumStack());
					temp = temp.subtract(BigDecimal.valueOf(1000));
					plat.setAmount(plat.getAmount() - 1);
				}
			}
			
			if (gold != null) {
				while (gold.getAmount() >= 1 && temp.doubleValue() >= 100) {
					inv.removeItem(CoinzManager.getGoldStack());
					temp = temp.subtract(BigDecimal.valueOf(100));
					gold.setAmount(gold.getAmount() - 1);
				}
			}
			
			if (silv != null) {
				while (silv.getAmount() >= 1 && temp.doubleValue() >= 10) {
					inv.removeItem(CoinzManager.getSilverStack());
					temp = temp.subtract(BigDecimal.valueOf(10));
					silv.setAmount(silv.getAmount() - 1);
				}
			}
			
			if (bron != null) {
				while (bron.getAmount() >= 1 && temp.doubleValue() >= 1) {
					inv.removeItem(CoinzManager.getBronzeStack());
					temp = temp.subtract(BigDecimal.valueOf(1));
					bron.setAmount(bron.getAmount() - 1);
				}
			}
			
			if (copp != null) {
				while (copp.getAmount() >= 1 && temp.doubleValue() >= 0.1) {
					inv.removeItem(CoinzManager.getCopperStack());
					temp = temp.subtract(BigDecimal.valueOf(0.1));
					copp.setAmount(copp.getAmount() - 1);
				}
			}
			
			if (temp.doubleValue() > 0) {
				amount = amount - temp.doubleValue();
				tellerPopup.attachWidget(plugin, tellerPopup.wrongChange);
				
				while (amount >= 1000) {
					inv.addItem(Coinz.PlatinumCoin);
					amount = amount - 1000;
				}
				
				while (amount >= 100) {
					inv.addItem(Coinz.GoldCoin);
					amount = amount - 100;
				}
				
				while (amount >= 10) {
					inv.addItem(Coinz.SilverCoin);
					amount = amount - 10;
				}
				
				while (amount >= 1) {
					inv.addItem(Coinz.BronzeCoin);
					amount = amount - 1;
				}
				
				while (amount >= 0.1) {
					inv.addItem(Coinz.CopperCoin);
					amount = amount - 0.1;
				}
			}
			else {
				CoinzManager.getEconomy().depositPlayer(player.getName(), amount);
				player.sendMessage(amount + " has been added to your account");
				enter.setText("");
				balance.setText(CoinzManager.getEconomy().format(CoinzManager.getEconomy().getBalance(player.getName())));
			}
		}
		
		else {
			tellerPopup.attachWidget(plugin, tellerPopup.notEnoughC);
		}
	}
	
	/**
	 * Get the value of coins the player currently has in his inventory
	 * @return The value of coins the player has
	 */
	public double getCoins() {
		double coin = 0;
		
		for (ItemStack item : inv.getContents()) {
			if (item != null) {
				stack = new SpoutItemStack(item);
				dur = stack.getDurability();
				
				if (stack.isCustomItem()) {
					if (dur == Coinz.CopperCoin.getDurability()) {
						coin = coin + (stack.getAmount() * 0.1);
					}
					
					if (dur == Coinz.BronzeCoin.getDurability()) {
						coin = coin + (stack.getAmount() * 1);
					}
					
					if (dur == Coinz.SilverCoin.getDurability()) {
						coin = coin + (stack.getAmount() * 10);
					}
					
					if (dur == Coinz.GoldCoin.getDurability()) {
						coin = coin + (stack.getAmount() * 100);
					}
					
					if (dur == Coinz.PlatinumCoin.getDurability()) {
						coin = coin + (stack.getAmount() * 1000);
					}
				}
			}
		}
		
		return coin;
	}
	
	
	/**
	 * Checks the players inventory to see if they have enough coins to deposit
	 * @param amount Double
	 * @return True if they have enough coins
	 */
	public boolean hasEnoughCoins(double amount) {
		if (getCoins() >= amount) return true;
		return false;
	}

	
	/**
	 * Tries to withdraw money from a players account and add coins to their inventory
	 * @param withdraw Double
	 */
	public void withdrawCoins(double withdraw) {

		//Calling the withdraw event
		TellerWithdrawEvent event = new TellerWithdrawEvent(player, withdraw);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		double amount = event.getAmount();
		
		if (CoinzManager.getEconomy().has(player.getName(), amount)) {
			
			temp = BigDecimal.valueOf(amount);
			
			while (temp.doubleValue() >= 1000) {
				inv.addItem(Coinz.PlatinumCoin);
				temp = temp.subtract(BigDecimal.valueOf(1000));
			}
			
			while (temp.doubleValue() >= 100) {
				inv.addItem(Coinz.GoldCoin);
				temp = temp.subtract(BigDecimal.valueOf(100));
			}
			
			while (temp.doubleValue() >= 10) {
				inv.addItem(Coinz.SilverCoin);
				temp = temp.subtract(BigDecimal.valueOf(10));
			}
			
			while (temp.doubleValue() >= 1) {
				inv.addItem(Coinz.BronzeCoin);
				temp = temp.subtract(BigDecimal.valueOf(1));
			}
			
			while (temp.doubleValue() >= .1) {
				inv.addItem(Coinz.CopperCoin);
				temp = temp.subtract(BigDecimal.valueOf(0.1));
			}
			
			if (temp.doubleValue() > 0) {
				amount = amount - temp.doubleValue();
				
				tellerPopup.attachWidget(plugin, tellerPopup.invalidAmount);
				enter.setText("");
				
				for (ItemStack item : inv.getContents()) {
					if (item != null) {
						stack = new SpoutItemStack(item);
						dur = stack.getDurability();
						
						if (stack.isCustomItem()) {
							if (dur == Coinz.CopperCoin.getDurability()) {
								copp = stack;
							}
							
							if (dur == Coinz.BronzeCoin.getDurability()) {
								bron = stack;
							}
							
							if (dur == Coinz.SilverCoin.getDurability()) {
								silv = stack;
							}
							
							if (dur == Coinz.GoldCoin.getDurability()) {
								gold = stack;
							}
							
							if (dur == Coinz.PlatinumCoin.getDurability()) {
								plat = stack;
							}
						}
					}
				}
				
				while (amount >= 1000) {
					if (plat != null && plat.getAmount() >= 1) {
						inv.removeItem(CoinzManager.getPlatinumStack());
						plat.setAmount(plat.getAmount() - 1);
						amount = amount - 1000;
					}
				}
				
				while (amount >= 100) {
					if (gold != null && gold.getAmount() >= 1) {
						inv.removeItem(CoinzManager.getGoldStack());
						gold.setAmount(gold.getAmount() - 1);
						amount = amount - 100;
					}
				}
				
				while (amount >= 10) {
					if (silv != null && silv.getAmount() >= 1) {
						inv.removeItem(CoinzManager.getSilverStack());
						silv.setAmount(silv.getAmount() - 1);
						amount = amount - 10;
					}
				}
				
				while (amount >= 1) {
					if (bron != null && bron.getAmount() >= 1) {
						inv.removeItem(CoinzManager.getBronzeStack());
						bron.setAmount(bron.getAmount() - 1);
						amount = amount - 1;
					}
				}
					
				while (amount >= 0.1) {
					if (copp != null && copp.getAmount() >= 1) {
						inv.removeItem(CoinzManager.getCopperStack());
						copp.setAmount(copp.getAmount() - 1);
						amount = amount - 0.1;
					}
				}
			}
			
			else {
				CoinzManager.getEconomy().withdrawPlayer(player.getName(), amount);
				player.sendMessage(amount + " " + "has been taken from your account");
				enter.setText("");
				balance.setText(CoinzManager.getEconomy().format(CoinzManager.getEconomy().getBalance(player.getName())));
			}
		}
		
		else {
			tellerPopup.attachWidget(plugin, tellerPopup.notEnoughA);
		}
	}
}

