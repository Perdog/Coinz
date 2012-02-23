package dev.mCraft.Coinz.Serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
 
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;
 
public class PersistVault implements Serializable {
	public static PersistVault hook;

    private static final long serialVersionUID = 1L;

    private String direct = "plugins/Coinz/Vault_Locations";
    private VaultStore[] stacks;
    
    public PersistVault() {
    	hook = this;
    }
    
    public void save(Location loc, Inventory inv) throws IOException {
    	
    	stacks = new VaultStore[9];
    	
    	ItemStack[] contents = inv.getContents();
    	
    	for (int i = 0; i < contents.length; i++) {
    		ItemStack item = contents[i];
    		if (item != null) {
    			SpoutItemStack stack = new SpoutItemStack(item);
    			stacks[i] = new VaultStore(this.serialize(stack));
    		}
    	}
    	
    	File file = getLocationFile(loc);
    	
    	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false));
    	out.writeObject(stacks);
    	out.close();
    }
 
    public Inventory load(Location loc, Inventory inv) throws IOException, ClassNotFoundException {
    	File file = getLocationFile(loc);
    	
    	FileInputStream stream = new FileInputStream(file);
    	ObjectInputStream in = new ObjectInputStream(stream);
        VaultStore[] loaded = (VaultStore[]) in.readObject();
        
        in.close();
        stream.close();
        
        for (int i = 0; i < loaded.length; i++) {
        	VaultStore stack = loaded[i];
        	
        	if (stack != null) {
        		SpoutItemStack item = stack.convert();
        		
        		inv.setItem(i, item);
        	}
        }
        return inv;
    }
    
    private Map<String, Object> serialize(SpoutItemStack stack) {
    	Map<String, Object> result = new LinkedHashMap<String, Object>();
    	
    	result.put("ID", stack.getTypeId());
    	
    	if (stack.getAmount() != 1) {
    		result.put("Amount", stack.getAmount());
    	}
    	
    	if (stack.getDurability() != 0 ) {
    		result.put("Data", stack.getDurability());
    	}
    	
    	return result;
    }
    
    private SpoutItemStack deserialize(Map<String, Object> map) {
    	int id = (Integer) map.get("ID");
    	int amount = 1;
    	short data = 0;
    	
    	if (map.containsKey("Amount")) {
    		amount = (Integer) map.get("Amount");
    	}
    	
    	if (map.containsKey("Data")) {
    		data = (Short) map.get("Data");
    	}
    	
    	return new SpoutItemStack(id, amount, data);
    }
    
    public void destory(Location loc) throws IOException, ClassNotFoundException {
    	String name = loc.toString();
    	File file = new File(direct, name + ".txt");
    	file.delete();
    }
 
    public void createLocationFile(File file) {
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
    
    public File getLocationFile(Location loc) {
    	String name = loc.toString();
    	File file = new File(direct, name + ".txt");
        
        if(!file.exists()){
            createLocationFile(file);
        }
        
        return file;
    }
 
    private class VaultStore implements Serializable {
    	PersistVault persist = PersistVault.hook;
    	
        private static final long serialVersionUID = 1L;
        private Map<String, Object> stack;
        private SpoutItemStack item;
        
		public VaultStore(Map<String, Object> stack) {
			this.stack = stack;
		}

		private SpoutItemStack convert() {
			item = persist.deserialize(stack);
            return item;
        }
    }
}
