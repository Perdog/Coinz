package dev.mCraft.Coinz.api.Vault;

import org.bukkit.event.Event;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

public class VaultPlacedEvent extends Event {

	private static final long serialVersionUID = -4984092047714434077L;
	
	private SpoutPlayer player;
	private SpoutItemStack item;
	
	/**
	 * Constructor for the vault place event
	 * @param player SpoutPlayer
	 * @param cursor SpoutItemStack
	 */
	public VaultPlacedEvent(SpoutPlayer player, SpoutItemStack cursor) {
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

}
