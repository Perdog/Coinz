package dev.mCraft.Coinz.Serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.bukkit.Location;

import dev.mCraft.Coinz.GUI.VaultInv.KeypadPopup;

public class PersistPasswords implements Serializable {
	public static PersistPasswords hook;
	public KeypadPopup keypad;
	
	private static final long serialVersionUID = 1L;
	
	private String direct = "plugins/Coinz/Vault_Passwords";
	private String password;
	
	public PersistPasswords() {
		hook = this;
	}
	
	public void save(Location loc, String pass) throws IOException {
		password = pass;
		
		File file = getPasswordFile(loc);
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false));
		out.writeObject(password);
		out.flush();
		out.close();
	}
	
	public String load(Location loc) throws IOException, ClassNotFoundException {
		File file = getPasswordFile(loc);
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		String loaded = (String) in.readObject();
		in.close();
		
		return loaded;
	}
    
    public void destory(Location loc) throws IOException, ClassNotFoundException {
    	String name = loc.toString();
    	File file = new File(direct, name + ".txt");
    	file.delete();
    }
	
	public void createPasswordFile(File file) {
    	try {
    		File path = new File(direct);
    		
        	if (!path.exists()) {
        		path.mkdirs();
        	}
        	
			file.createNewFile();
		}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public boolean doesPasswordFileExist(Location loc) {
		String name = loc.toString();
		File file = new File(direct, name + ".txt");
		
		if (file.exists()) {
			return true;
		}
		
		return false;
	}
	
	public File getPasswordFile(Location loc) {
		String name = loc.toString();
		File file = new File(direct, name + ".txt");
		
		if (!file.exists()) {
			createPasswordFile(file);
		}
		
		return file;
	}

}
