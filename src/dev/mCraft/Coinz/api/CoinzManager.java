package dev.mCraft.Coinz.api;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;

import dev.mCraft.Coinz.Coinz;

public class CoinzManager {
	private static Coinz plugin = Coinz.instance;
	private static CoinzManager instance = new CoinzManager();
	
	/**
	 * Get an instance of the CoinzManager class
	 * @return CoinzManager instance
	 */
	public static CoinzManager getInstance() {
		return instance;
	}
	
	/**
	 * Gets the current economy provider that Coinz is using
	 * @return Economy plugin
	 */
	public static Economy getEconomy() {
		return plugin.econ;
	}
	
	/**
	 * Gets the name of the plugin, including the version
	 * @return String name
	 */
	public static String getFullName() {
		return plugin.getDescription().getFullName();
	}
	
	/**
	 * Gets the logger for coinz
	 * @return Logger
	 */
	public static Logger getLogger() {
		return plugin.log;
	}
	
	/**
	 * Gets the name of the plugin
	 * @return String name
	 */
	public static String getName() {
		return plugin.getDescription().getName();
	}
	
	/**
	 * Gets the version of the plugin
	 * @return String version
	 */
	public static String getVersion() {
		return plugin.getDescription().getVersion();
	}
	
	/**
	 * Get the copper coin item
	 * @return Copper coin
	 */
	public static CustomItem getCopperCoin() {
		return plugin.copperCoin;
	}
	
	/**
	 * Gets the half bronze coin item
	 * @return Half-Bronze coin
	 */
	public static CustomItem getHalfBronzeCoin() {
		return plugin.halfbronzeCoin;
	}

	/**
	 * Gets the bronze coin item
	 * @return Bronze coin
	 */
	public static CustomItem getBronzeCoin() {
		return plugin.bronzeCoin;
	}
	
	/**
	 * Gets the half silver coin item
	 * @return Half-Silver coin
	 */
	public static CustomItem getHalfSilverCoin() {
		return plugin.halfsilverCoin;
	}

	/**
	 * Get the silver coin item
	 * @return Silver coin
	 */
	public static CustomItem getSilverCoin() {
		return plugin.silverCoin;
	}
	
	/**
	 * Gets the half gold coin item
	 * @return Half-Gold coin
	 */
	public static CustomItem getHalfGoldCoin() {
		return plugin.halfgoldCoin;
	}

	/**
	 * Get the gold coin item
	 * @return Gold coin
	 */
	public static CustomItem getGoldCoin() {
		return plugin.goldCoin;
	}
	
	/**
	 * Gets the half platinum coin item
	 * @return Half-Platinum coin
	 */
	public static CustomItem getHalfPlatinumCoin() {
		return plugin.halfplatinumCoin;
	}

	/**
	 * Get the platinum coin item
	 * @return Platinum coin
	 */
	public static CustomItem getPlatinumCoin() {
		return plugin.platinumCoin;
	}

	/**
	 * Gets the teller custom block
	 * @return Teller
	 */
	public static CustomBlock getTellerBlock() {
		return plugin.tellerBlock;
	}

	/**
	 * Gets the vault custom block
	 * @return Vault
	 */
	public static CustomBlock getVaultBlock() {
		return plugin.vaultBlock;
	}
}
