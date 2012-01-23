package dev.mCraft.Coinz;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.block.SpoutBlock;

public class MyBlockListener extends BlockListener {
	
	public Main plugin = Main.instance;
	
	private SpoutBlock clicked;
	private Block block;
	private SpoutBlock placed;

	@Override
	public void onBlockPlace(BlockPlaceEvent event) {
		clicked = (SpoutBlock) event.getBlockAgainst();
		block = event.getBlockPlaced();
		placed = (SpoutBlock)block;
		
		if (placed.getCustomBlockId() == plugin.tellerBlock.getCustomId()) {
			
			if (event.getBlockReplacedState().getType() != Material.AIR) {
				event.setCancelled(true);
			}
			
			if (clicked.getType() == Material.WOODEN_DOOR 
					|| clicked.getType() == Material.TRAP_DOOR
					|| clicked.getType() == Material.STORAGE_MINECART
					|| clicked.getType() == Material.POWERED_MINECART
					|| clicked.getType() == Material.MINECART
					|| clicked.getType() == Material.LEVER
					|| clicked.getType() == Material.IRON_DOOR
					|| clicked.getType() == Material.FENCE_GATE
					|| clicked.getType() == Material.ENCHANTMENT_TABLE
					|| clicked.getType() == Material.DIODE_BLOCK_OFF
					|| clicked.getType() == Material.DIODE_BLOCK_ON
					|| clicked.getType() == Material.CAULDRON
					|| clicked.getType() == Material.BREWING_STAND
					|| clicked.getType() == Material.BOAT
					|| clicked.getCustomBlockId() == plugin.tellerBlock.getCustomId()) {
				event.setCancelled(true);
			}
		}
	}

}