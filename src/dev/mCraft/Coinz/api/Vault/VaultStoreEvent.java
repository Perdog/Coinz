package dev.mCraft.Coinz.api.Vault;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

public class VaultStoreEvent extends Event {

	private static final long serialVersionUID = -4984092047714434077L;

	private static final HandlerList handlers = new HandlerList();
	
	private SpoutPlayer player;
	private SpoutItemStack item;
	
	/**
	 * Constructor for the vault place event
	 * @param player SpoutPlayer
	 * @param cursor SpoutItemStack
	 */
	public VaultStoreEvent(SpoutPlayer player, SpoutItemStack cursor) {
		this.player = player;
		this.item = cursor;
	}
	
	/**
	 * Get the item involved in the event
	 * @return The item involved
	 */
	public SpoutItemStack getItemStack() {
		return item;
	}
	
	/**
	 * Get the player involved in the event
	 * @return The player involved
	 */
	public SpoutPlayer getPlayer() {
		return player;
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
