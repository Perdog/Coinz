package dev.mCraft.Coinz;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.player.SpoutPlayer;

import dev.mCraft.Coinz.Blocks.Blocks;
import dev.mCraft.Coinz.Coins.Items;
import dev.mCraft.Coinz.GUI.KeyPadListener;
import dev.mCraft.Coinz.GUI.TellerScreenListener;
import dev.mCraft.Coinz.GUI.VaultInv.KeypadPopup;
import dev.mCraft.Coinz.Listeners.BlockListener;
import dev.mCraft.Coinz.Listeners.InventoryListener;
import dev.mCraft.Coinz.Listeners.PlayerListener;
import dev.mCraft.Coinz.Listeners.TellerListener;
import dev.mCraft.Coinz.Listeners.VaultListener;
import dev.mCraft.Coinz.Serializer.PersistPasswords;
import dev.mCraft.Coinz.Serializer.PersistVault;
import dev.mCraft.Coinz.Statistics.MetricsRunnable;

public class Coinz extends JavaPlugin {
	
	public static Coinz instance;
	
	public String tag = "[Coinz]";
	private String name;
	public Logger log;
	
	public Economy econ;
	public Permission perm;
	
	private MaterialManager mm;
	
	private Blocks blocks;
	private Items items;
	
	//Custom item variables
	public CustomItem copperCoin;
	
	public CustomItem halfbronzeCoin;
	public CustomItem bronzeCoin;
	
	public CustomItem halfsilverCoin;
	public CustomItem silverCoin;
	
	public CustomItem halfgoldCoin;
	public CustomItem goldCoin;
	
	public CustomItem halfplatinumCoin;
	public CustomItem platinumCoin;

	public CustomBlock tellerBlock;
	public CustomBlock vaultBlock;
	
	//Item stack variables
	public static ItemStack CopperCoin;
	public ItemStack CopperCoins;
	
	public static ItemStack HalfBronzeCoin;
	public static ItemStack BronzeCoin;
	public ItemStack BronzeCoins;
	
	public static ItemStack HalfSilverCoin;
	public static ItemStack SilverCoin;
	public ItemStack SilverCoins;
	
	public static ItemStack HalfGoldCoin;
	public static ItemStack GoldCoin;
	public ItemStack GoldCoins;
	
	public static ItemStack HalfPlatinumCoin;
	public static ItemStack PlatinumCoin;
	
	public ItemStack TellerBlock;
	public ItemStack VaultBlock;

	//Recipe variables
	private SpoutShapedRecipe CtoB;
	private SpoutShapedRecipe BtoC;
	private SpoutShapedRecipe MakeHBC;
	private SpoutShapedRecipe BtoS;
	private SpoutShapedRecipe StoB;
	private SpoutShapedRecipe MakeHSC;
	private SpoutShapedRecipe MakeSC;
	private SpoutShapedRecipe StoG;
	private SpoutShapedRecipe GtoS;
	private SpoutShapedRecipe MakeHGC;
	private SpoutShapedRecipe MakeGC;
	public SpoutShapedRecipe MakeGC2;
	private SpoutShapedRecipe GtoP;
	private SpoutShapedRecipe PtoG;
	private SpoutShapedRecipe MakeHPC;
	private SpoutShapedRecipe MakePC;

	public SpoutShapedRecipe tellerRec;
	public SpoutShapedRecipe vaultRec;
	
	public void onEnable() {
		//instancing the plugin so we can hook other classes
		instance = this;
		
		setupEcon();
		setupPerm();
		setupConfig();
		
		name = this.getDescription().getFullName();
		log = Logger.getLogger("Minecraft");
		
		mm = SpoutManager.getMaterialManager();
		
		new Cache();
		
		registerListeners();
		
		createBlocks();
		createItems();
		cookRecipes();
		
		log.info(name + " has been enabled");
		
		MetricsRunnable run = new MetricsRunnable();
		this.getServer().getScheduler().scheduleAsyncDelayedTask(this, run, 300);
	}
	
	public void onDisable() {
		log.info(name + " has been disabled");
	}
	
	public void registerListeners() {
		new BlockListener();
		new InventoryListener();
		new PlayerListener();
		new TellerListener();
		new VaultListener();
		
		new KeyPadListener();
		new TellerScreenListener();
		
		new PersistVault();
		new PersistPasswords();
	}
	
	public void createBlocks() {
		new Blocks();
		
		blocks = Blocks.hook;
		
		//Create blocks
		tellerBlock = blocks.teller;
		vaultBlock = blocks.vault;
		
		//Create itemstacks for blocks
		TellerBlock = new SpoutItemStack(tellerBlock, 1);
		VaultBlock = new SpoutItemStack(vaultBlock, 1);
		
		this.log.info(this.tag + " Blocks have been created");
	}
	
	public void createItems() {
		new Items();
		
		items = Items.hook;
		
		//Create items
		copperCoin = items.copperCoin;
		halfbronzeCoin = items.halfbronzeCoin;
		bronzeCoin = items.bronzeCoin;
		halfsilverCoin = items.halfsilverCoin;
		silverCoin = items.silverCoin;
		halfgoldCoin = items.halfgoldCoin;
		goldCoin = items.goldCoin;
		halfplatinumCoin = items.halfplatinumCoin;
		platinumCoin = items.platinumCoin;
		
		//Create itemstacks for each coin
		CopperCoin = new SpoutItemStack(copperCoin, 1);
		CopperCoins = new SpoutItemStack(copperCoin, 10);
		HalfBronzeCoin = new SpoutItemStack(halfbronzeCoin, 1);
		BronzeCoin = new SpoutItemStack(bronzeCoin, 1);
		BronzeCoins = new SpoutItemStack(bronzeCoin, 10);
		
		HalfSilverCoin = new SpoutItemStack(halfsilverCoin, 1);
		SilverCoin = new SpoutItemStack(silverCoin, 1);
		SilverCoins = new SpoutItemStack(silverCoin, 10);
		
		HalfGoldCoin = new SpoutItemStack(halfgoldCoin, 1);
		GoldCoin = new SpoutItemStack(goldCoin, 1);
		GoldCoins = new SpoutItemStack(goldCoin, 10);
		
		HalfPlatinumCoin = new SpoutItemStack(halfplatinumCoin, 1);
		PlatinumCoin = new SpoutItemStack(platinumCoin, 1);
		
		this.log.info(this.tag + " Items have been created");
				
	}
	
	public void cookRecipes() {
		
		//Register a new recipe for each coin
		CtoB = new SpoutShapedRecipe(BronzeCoin);
		CtoB.shape("A", "A");
		CtoB.setIngredient('A', halfbronzeCoin);
		mm.registerSpoutRecipe(CtoB);
		
		BtoC = new SpoutShapedRecipe(CopperCoins);
		BtoC.shape("A");
		BtoC.setIngredient('A', bronzeCoin);
		mm.registerSpoutRecipe(BtoC);
		
		MakeHBC = new SpoutShapedRecipe(HalfBronzeCoin);
		MakeHBC.shape("AA", "A ", "AA");
		MakeHBC.setIngredient('A', copperCoin);
		mm.registerSpoutRecipe(MakeHBC);
		
		BtoS = new SpoutShapedRecipe(SilverCoin);
		BtoS.shape("A", "A");
		BtoS.setIngredient('A', halfsilverCoin);
		mm.registerSpoutRecipe(BtoS);
		
		StoB = new SpoutShapedRecipe(BronzeCoins);
		StoB.shape("A");
		StoB.setIngredient('A', silverCoin);
		mm.registerSpoutRecipe(StoB);
		
		MakeSC = new SpoutShapedRecipe(SilverCoin);
		MakeSC.shape("A", "A");
		MakeSC.setIngredient('A', halfsilverCoin);
		mm.registerSpoutRecipe(MakeSC);
		
		MakeHSC = new SpoutShapedRecipe(HalfSilverCoin);
		MakeHSC.shape("AA", "A ", "AA");
		MakeHSC.setIngredient('A', bronzeCoin);
		mm.registerSpoutRecipe(MakeHSC);
		
		StoG = new SpoutShapedRecipe(GoldCoin);
		StoG.shape("A", "A");
		StoG.setIngredient('A', halfgoldCoin);
		mm.registerSpoutRecipe(StoG);
		
		GtoS = new SpoutShapedRecipe(SilverCoins);
		GtoS.shape("A");
		GtoS.setIngredient('A', goldCoin);
		mm.registerSpoutRecipe(GtoS);
		
		MakeGC = new SpoutShapedRecipe(GoldCoin);
		MakeGC.shape("A", "A");
		MakeGC.setIngredient('A', halfgoldCoin);
		mm.registerSpoutRecipe(MakeGC);
		
		MakeGC2 = new SpoutShapedRecipe(GoldCoin);
		MakeGC2.shape(" A ", "AAA", " A ");
		MakeGC2.setIngredient('A', MaterialData.goldNugget);
		mm.registerSpoutRecipe(MakeGC2);
		
		MakeHGC = new SpoutShapedRecipe(HalfGoldCoin);
		MakeHGC.shape("AA", "A ", "AA");
		MakeHGC.setIngredient('A', silverCoin);
		mm.registerSpoutRecipe(MakeHGC);
		
		GtoP = new SpoutShapedRecipe(PlatinumCoin);
		GtoP.shape("A", "A");
		GtoP.setIngredient('A', halfplatinumCoin);
		mm.registerSpoutRecipe(GtoP);
		
		PtoG = new SpoutShapedRecipe(GoldCoins);
		PtoG.shape("A");
		PtoG.setIngredient('A', platinumCoin);
		mm.registerSpoutRecipe(PtoG);
		
		MakePC = new SpoutShapedRecipe(PlatinumCoin);
		MakePC.shape("A", "A");
		MakePC.setIngredient('A', halfplatinumCoin);
		mm.registerSpoutRecipe(MakePC);
		
		MakeHPC = new SpoutShapedRecipe(HalfPlatinumCoin);
		MakeHPC.shape("AA", "A ", "AA");
		MakeHPC.setIngredient('A', goldCoin);
		mm.registerSpoutRecipe(MakeHPC);
		
		//Create block recipes
		tellerRec = new SpoutShapedRecipe(TellerBlock);
		tellerRec.shape("ABA", "CDC", "CEC");
		tellerRec.setIngredient('A', MaterialData.ironIngot).setIngredient('B', MaterialData.goldIngot);
		tellerRec.setIngredient('C', MaterialData.wood);
		tellerRec.setIngredient('D', MaterialData.redstoneTorchOn).setIngredient('E', MaterialData.redstone);
		mm.registerSpoutRecipe(tellerRec);
		
		vaultRec = new SpoutShapedRecipe(VaultBlock);
		vaultRec.shape("AAA", "AAA", "AAA");
		vaultRec.setIngredient('A', MaterialData.ironBlock);
		mm.registerSpoutRecipe(vaultRec);
		
		//Just something to add to the stacktrace :P
		this.log.info(this.tag + " Recipes pre-cooked and ready");
		
	}
	
	public void setupConfig() {
		FileConfiguration config = this.getConfig();
		
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean setupEcon() {
		RegisteredServiceProvider<Economy> econProvider = this.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		
		if (econProvider != null) {
			econ = econProvider.getProvider();
		}
		
		return (econ != null);
	}
	
	public boolean setupPerm() {
		RegisteredServiceProvider<Permission> permProvider = this.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		
		if (permProvider != null) {
			perm = permProvider.getProvider();
		}
		return (perm != null);
	}

}
