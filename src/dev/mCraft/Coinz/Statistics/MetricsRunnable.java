package dev.mCraft.Coinz.Statistics;

import java.io.IOException;

import dev.mCraft.Coinz.Coinz;

public class MetricsRunnable implements Runnable {
	private Coinz plugin = Coinz.instance;
	
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
