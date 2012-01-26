package dev.mCraft.Coinz.Metrics;

import java.io.IOException;

import dev.mCraft.Coinz.Main;

public class MetricsRunnable implements Runnable {
	private Main plugin = Main.instance;
	
	private Metrics metrics = null;
	
	@Override
	public void run() {
		try {
			metrics = new Metrics();
			
			metrics.beginMeasuringPlugin(plugin);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
