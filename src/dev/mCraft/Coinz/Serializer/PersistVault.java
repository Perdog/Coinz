package dev.mCraft.Coinz.Serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import dev.mCraft.Coinz.Coinz;
 
public class PersistVault implements Serializable {
	public static PersistVault hook;
	
    private List<VaultStore> stacks;
    private String direct = "plugins/Coinz/Vault_Locations";
    private static final long serialVersionUID = 1L;
    
    public PersistVault() {
    	hook = this;
    }
    
    public void save(Location loc, Inventory inv) throws IOException {
    	
    	stacks = new ArrayList<VaultStore>();
    	
    	for (ItemStack item : inv.getContents()) {
    		if (item != null) {
    			SpoutItemStack stack = new SpoutItemStack(item);
    			stacks.add(new VaultStore(this.serialize(stack)));
    		}
    	}
    	
    	File file = getLocationFile(loc);
    	
    	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false));
    	out.writeObject(this);
    	out.close();
    }
 
    public Inventory load(Location loc, Inventory inv) throws IOException, ClassNotFoundException {
    	File file = getLocationFile(loc);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        PersistVault pInv = (PersistVault) in.readObject();
        in.close();
        
        for(VaultStore stack : pInv.stacks) {
        	SpoutItemStack item = stack.convert();
        	if (item.getDurability() == Coinz.CopperCoin.getDurability()) {
        		inv.setItem(0, item);
        	}
        	if (item.getDurability() == Coinz.HalfBronzeCoin.getDurability()) {
        		inv.setItem(1, item);
        	}
        	if (item.getDurability() == Coinz.BronzeCoin.getDurability()) {
        		inv.setItem(2, item);
        	}
        	if (item.getDurability() == Coinz.HalfSilverCoin.getDurability()) {
        		inv.setItem(3, item);
        	}
        	if (item.getDurability() == Coinz.SilverCoin.getDurability()) {
        		inv.setItem(4, item);
        	}
        	if (item.getDurability() == Coinz.HalfGoldCoin.getDurability()) {
        		inv.setItem(5, item);
        	}
        	if (item.getDurability() == Coinz.GoldCoin.getDurability()) {
        		inv.setItem(6, item);
        	}
        	if (item.getDurability() == Coinz.HalfPlatinumCoin.getDurability()) {
        		inv.setItem(7, item);
        	}
        	if (item.getDurability() == Coinz.PlatinumCoin.getDurability()) {
        		inv.setItem(8, item);
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
    	File file = getLocationFile(loc);
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
    	File file = new File(direct, name);
        
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
