package dev.mCraft.Coinz.GUI;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.GenericTextField;

import dev.mCraft.Coinz.Main;
import dev.mCraft.Coinz.GUI.VaultInv.KeyPad;

public class KeyPadListener extends ScreenListener {
	private Main plugin = Main.instance;
	private KeyPad hook = KeyPad.hook;
	
	private Button button;
	private GenericTextField pass;

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		button = event.getButton();
		pass = hook.pass;
		
		if (button.getPlugin() == plugin) {
			if (button.getId() == hook.b0.getId()) {
				pass.setText(pass.getText() + 0);
			}
			if (button.getId() == hook.b1.getId()) {
				pass.setText(pass.getText() + 1);
			}
			if (button.getId() == hook.b2.getId()) {
				pass.setText(pass.getText() + 2);
			}
			if (button.getId() == hook.b3.getId()) {
				pass.setText(pass.getText() + 3);
			}
			if (button.getId() == hook.b4.getId()) {
				pass.setText(pass.getText() + 4);
			}
			if (button.getId() == hook.b5.getId()) {
				pass.setText(pass.getText() + 5);
			}
			if (button.getId() == hook.b6.getId()) {
				pass.setText(pass.getText() + 6);
			}
			if (button.getId() == hook.b7.getId()) {
				pass.setText(pass.getText() + 7);
			}
			if (button.getId() == hook.b8.getId()) {
				pass.setText(pass.getText() + 8);
			}
			if (button.getId() == hook.b9.getId()) {
				pass.setText(pass.getText() + 9);
			}
			if (button.getId() == hook.pound.getId()) {
				pass.setText(pass.getText() + "#");
			}
			if (button.getId() == hook.star.getId()) {
				pass.setText(pass.getText() + "*");
			}
		}
	}

}
