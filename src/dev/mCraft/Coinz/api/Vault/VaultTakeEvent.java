package dev.mCraft.Coinz.api.Vault;

import org.bukkit.event.Event;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

public class VaultTakeEvent extends Event {

	private static final long serialVersionUID = 3871139411791321784L;
	
	private SpoutPlayer player;
	private SpoutItemStack item;
	
	/**
	 * 
	 * Constructor for the vault take event
	 * @param player SpoutPlayer
	 * @param slot SpoutItemStack
	 */
	public VaultTakeEvent(SpoutPlayer player, SpoutItemStack slot) {
		this.player = player;
		this.item = slot;
	}
	
	/**
	 * Gets the item involved in the event
	 * @return The item involved
	 */
	public SpoutItemStack getItem() {
		return item;
	}
	
	/**
	 * Gets the player involved in the event
	 * @return The player involved
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}

}
