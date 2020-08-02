package org.engine;

import org.graphics.Renderer;
import org.world.World;

public class GameLoop {
	
	private static boolean running = false;
	
	private static int targetFPS = 60;
	private static long targetTime = 1000000000/targetFPS;
	
	
	public static void start() {
		
		Thread thread = new Thread() {
			public void run() {
				running = true;
				int FPS = 0;
				long lastFPSTime = System.nanoTime();
				
				while(running) {
					long startTime = System.nanoTime();
					
					//Input
					
						
					//Update
					World.update();
					
					
					//Render
					Renderer.render();
					
					
					//FPS Count
					FPS++;
					if(System.nanoTime() >= lastFPSTime + 1000000000) {
						System.out.println(FPS);
						FPS = 0;
						lastFPSTime = System.nanoTime();
					}
					
					//FPS Cap
					long timeTaken = System.nanoTime() - startTime;
					if(timeTaken < targetTime) { 
						try{ Thread.sleep((targetTime - timeTaken)/1000000); }
						catch(InterruptedException e) { e.printStackTrace(); }
					}
				}
			}
		};
		thread.setName("Game Loop");
		thread.start();
	}
	
	public static void finish() {
		running = false;
	}
}
