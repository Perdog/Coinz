package dev.mCraft.Coinz.api.Teller;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.api.Coins.Customer.TransactionResult;

public class TellerWithdrawEvent extends Event implements Cancellable {
	
	private static final long serialVersionUID = 7007223615815445991L;

	private static final HandlerList handlers = new HandlerList();
	
	private boolean cancelled = false;
	private SpoutPlayer player;
	private double amount = -1;
	private TransactionResult result;
	
	/**
	 * Contructor for Teller withdraw event
	 * @param player SpoutPlayer
	 * @param amount Integer
	 * @param result TransactionResult
	 */
	public TellerWithdrawEvent(SpoutPlayer player, double amount, TransactionResult result) {
		this.player = player;
		this.amount = amount;
		this.result = result;
	}
	
	/**
	 * Gets the amount the player is trying to withdraw
	 * @return The amount the player is withdrawing
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
	 * Checks if the event is cancelled
	 * @return True if event is cancelled
	 */
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	/**
	 * Set the amount the player is withdrawing
	 * @param amount Double
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Set if the event is cancelled or not
	 * @param cancel boolean
	 */
	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
	
	/**
	 * {@inheritDoc}
	 * @return Handler List
	 */
	public HandlerList getHandlers() {
		return handlers;
	}
	
	/**
	 * {@inheritDoc}
	 * @return Handler List
	 */
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
