package dev.mCraft.Coinz.api.Teller;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.api.Coins.Customer.TransactionResult;

public class TellerDepositEvent extends Event implements Cancellable {
	
	private static final long serialVersionUID = 1348381732347730573L;
	
	private boolean cancelled = false;
	private SpoutPlayer player;
	private double amount;
	private TransactionResult result;
	
	/**
	 * Constructor for teller deposit event
	 * @param player SpoutPlayer
	 * @param amount Integer
	 * @param result TransactionResult
	 */
	public TellerDepositEvent(SpoutPlayer player, double amount, TransactionResult result) {
		this.player = player;
		this.amount = amount;
		this.result = result;
	}
	
	/**
	 * Gets the amount of coins the player is trying to deposit
	 * @return The amount the player is depositing
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Gets the player involved in the event
	 * @return The player involved
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}
	
	/**
	 * Gets the result of the transaction
	 * @return The transaction result
	 */
	public TransactionResult getResult() {
		return result;
	}
	
	/**
	 * Checks if the event is cancelled or not
	 * @return True if the event is cancelled
	 */
	public boolean isCancelled() {
		return cancelled;
	}
	
	/**
	 * Set the amount being deposited
	 * @param amount Double
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Set if the event is cancelled or not
	 * @param cancel boolean
	 */
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
