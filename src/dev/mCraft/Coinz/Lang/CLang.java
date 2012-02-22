package dev.mCraft.Coinz.Lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.logging.Logger;

import dev.mCraft.Coinz.Coinz;
import dev.mCraft.Coinz.api.CoinzManager;

public class CLang {
	private static Coinz plugin = Coinz.instance;
	private static Logger log = CoinzManager.getLogger();
	
	private static File path = new File("plugins/Coinz", "locale");
	
	private static HashMap<String, HashMap<String, String>> languages = new HashMap<String, HashMap<String, String>>();
	
	public static void loadLang() {
		if (!path.exists()) path.mkdirs();
		
		checkDefaultFile();
		
		for (File file : path.listFiles()) {
			try {
				FileReader buff = new FileReader(file);
				BufferedReader reader = new BufferedReader(buff);
				
				String version = reader.readLine();
				if (!version.startsWith("version")) {
					CoinzManager.getLogger().warning("Language file '" + file + "' does not contain the line 'version'");
					continue;
				}
				
				String Version = version.substring(version.indexOf(":") + 1).trim();
				if (!Version.equalsIgnoreCase(CoinzManager.getVersion())) {
					log.severe(Version + " : " + CoinzManager.getVersion());
					CoinzManager.getLogger().warning("Languange file '" + file + "' is out of date");
					continue;
				}
				
				String name = reader.readLine();
				if (!name.startsWith("lang")) {
					CoinzManager.getLogger().warning("Language file '" + file + "' does not contain the line 'lang'");
					continue;
				}
				
				String lang = name.substring(name.indexOf(":") + 1).trim();
				if (languages.containsKey(lang.toLowerCase())) {
					CoinzManager.getLogger().warning("Language file '" + file + "' already exists");
					continue;
				}
				
				HashMap<String, String> keys = new HashMap<String, String>();
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("#") || line.length() == 0) continue;
					
					String key = line.substring(0, line.indexOf(":"));
					String value = line.substring(line.indexOf(":") + 1).replace("%n", "\n").trim();
					
					keys.put(key, value);
				}
				
				languages.put(lang, keys);
				
				CoinzManager.getLogger().info("Language file '" + lang +"' loaded");
				reader.close();
				buff.close();
			}
			catch (Exception e) {
				CoinzManager.getLogger().severe("");
			}
		}
	}
	
	private static void checkDefaultFile() {
		try {
			File defLang = new File(path, "en-CA.txt");
			
			if (defLang.exists()) {
				FileReader buff = new FileReader(defLang);
				BufferedReader reader = new BufferedReader(buff);
				
				String version = reader.readLine();
				
				reader.close();
				buff.close();
				
				if (!version.startsWith("version")) CoinzManager.getLogger().severe("'" + defLang + "' is missing the version line");
				
				String Version = version.substring(version.indexOf(":") + 1).trim();
				if (Version.equalsIgnoreCase(CoinzManager.getVersion())) return;
				
				else {
					CoinzManager.getLogger().warning("Language file en-CA is out dated, refreshing");
					defLang.delete();
				}
			}
			else {
				CoinzManager.getLogger().severe("Language file en-CA not found, creating");
			}
			
			plugin.saveResource("locale/en-CA.txt", true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String lookup(String key) {
		String lang = plugin.getConfig().getString("Language.Default");
		
		if (!languages.containsKey(lang)) lang = "en-CA";
		
		if (!languages.get(lang).containsKey(key)) {
			CoinzManager.getLogger().severe("Language file '" + lang + "' is missing the '" + key + "' key");
			return languages.get("en-CA").get(key);
		}
		
		return languages.get(lang).get(key);
	}
	
	public static String replace(String message, String keyword, String replacement) {
		String regex = "%" + keyword + "%";
		return message.replaceAll(regex, replacement);
	}

}
