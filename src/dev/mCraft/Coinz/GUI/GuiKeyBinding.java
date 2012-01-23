package dev.mCraft.Coinz.GUI;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.GUI.TellerMenu.Popup;
@Deprecated
public class GuiKeyBinding  implements BindingExecutionDelegate {
	
	private SpoutPlayer player;
	private ScreenType type;
	private Popup popup;

	@Override
	public void keyPressed(KeyBindingEvent event) {
		player = event.getPlayer();
		type = event.getScreenType();
		
		if (type == ScreenType.GAME_SCREEN) {
			popup = new Popup(player);
			player.getMainScreen().attachPopupScreen(popup);
		}
	}

	@Override
	public void keyReleased(KeyBindingEvent event) {
		
	}

}
