package dev.mCraft.Coinz.api;

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
	private double temp;
	
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
			temp = amount;
			
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
			
			if (plat != null && plat.getDurability() == Coinz.PlatinumCoin.getDurability()) {
				while (plat.getAmount() >=1 && temp >= 1000) {
					inv.removeItem(Coinz.PlatinumCoin);
					temp = temp - 1000;
					plat.setAmount(plat.getAmount() - 1);
				}
			}
			
			if (gold != null && gold.getDurability() == Coinz.GoldCoin.getDurability()) {
				while (gold.getAmount() >= 1 && temp >= 100) {
					inv.removeItem(Coinz.GoldCoin);
					temp = temp - 100;
					gold.setAmount(gold.getAmount() - 1);
				}
			}
			
			if (silv != null && silv.getDurability() == Coinz.SilverCoin.getDurability()) {
				while (silv.getAmount() >= 1 && temp >= 10) {
					inv.removeItem(Coinz.SilverCoin);
					temp = temp - 10;
					silv.setAmount(silv.getAmount() - 1);
				}
			}
			
			if (bron != null && bron.getDurability() == Coinz.BronzeCoin.getDurability()) {
				while (bron.getAmount() >= 1 && temp >= 1) {
					inv.removeItem(Coinz.BronzeCoin);
					temp = temp - 1;
					bron.setAmount(bron.getAmount() - 1);
				}
			}
			
			if (copp != null && copp.getDurability() == Coinz.CopperCoin.getDurability()) {
				while (copp.getAmount() >= 1 && temp >= 0.1) {
					inv.removeItem(Coinz.CopperCoin);
					temp = temp - 0.1;
					copp.setAmount(copp.getAmount() - 1);
				}
			}
			
			if (temp > 0) {
				amount = amount - temp;
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
				plugin.econ.depositPlayer(player.getName(), amount);
				player.sendMessage(amount + " has been added to your account");
				enter.setText("");
				balance.setText(plugin.econ.format(plugin.econ.getBalance(player.getName())));
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
		
		if (coin >= amount) {
			return true;
		}
		else {
			return false;
		}
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
		
		if (plugin.econ.has(player.getName(), amount)) {
			
			temp = amount;
			
			while (temp >= 1000) {
				inv.addItem(Coinz.PlatinumCoin);
				temp = temp - 1000;
			}
			
			while (temp >= 100) {
				inv.addItem(Coinz.GoldCoin);
				temp = temp - 100;
			}
			
			while (temp >= 10) {
				inv.addItem(Coinz.SilverCoin);
				temp = temp - 10;
			}
			
			while (temp >= 1) {
				inv.addItem(Coinz.BronzeCoin);
				temp = temp - 1;
			}
			
			while (temp >= 0.1) {
				inv.addItem(Coinz.CopperCoin);
				temp = temp - 0.1;
			}
			
			if (temp > 0) {
				amount = amount - temp;
				
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
						plat.setAmount(plat.getAmount() - 1);
						amount = amount - 1000;
					}
				}
				
				while (amount >= 100) {
					if (gold != null && gold.getAmount() >= 1) {
						gold.setAmount(gold.getAmount() - 1);
						amount = amount - 100;
					}
				}
				
				while (amount >= 10) {
					if (silv != null && silv.getAmount() >= 1) {
						silv.setAmount(silv.getAmount() - 1);
						amount = amount - 10;
					}
				}
				
				while (amount >= 1) {
					if (bron != null && bron.getAmount() >= 1) {
						bron.setAmount(bron.getAmount() - 1);
						amount = amount - 1;
					}
				}
					
				while (amount >= 0.1) {
					if (copp != null && copp.getAmount() >= 1) {
						copp.setAmount(copp.getAmount() - 1);
						amount = amount - 0.1;
					}
				}
			}
			
			else {
				plugin.econ.withdrawPlayer(player.getName(), amount);
				player.sendMessage(amount + " " + "has been taken from your account");
				enter.setText("");
				balance.setText(plugin.econ.format(plugin.econ.getBalance(player.getName())));
			}
		}
		
		else {
			tellerPopup.attachWidget(plugin, tellerPopup.notEnoughA);
		}
	}
}

