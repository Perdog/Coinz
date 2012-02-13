package dev.mCraft.Coinz.api.Teller;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.player.SpoutPlayer;

public class TellerDepositEvent extends Event implements Cancellable {
	
	private static final long serialVersionUID = 1348381732347730573L;

	private static final HandlerList handlers = new HandlerList();
	
	private boolean cancelled;
	private SpoutPlayer player;
	private double amount;
	
	/**
	 * Constructor for teller deposit event
	 * @param player SpoutPlayer
	 * @param amount Integer
	 */
	public TellerDepositEvent(SpoutPlayer player, double amount) {
		this.player = player;
		this.amount = amount;
		this.cancelled = false;
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
