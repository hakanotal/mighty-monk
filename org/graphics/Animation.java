package org.graphics;

import org.resource.ImageResource;

public class Animation {
	
	private ImageResource[] frames;
	private boolean loop = true;
	
	private int currentFrame = 0;
	private int fps = 10;
	private long lastTime = 0;
	
	public Animation(ImageResource[] images, boolean l){
		frames = images;
		loop = l;
	}
	
	public void play() {
		long currentTime = System.nanoTime();
		
		if(currentTime > lastTime + 1000000000/fps) {
			currentFrame++;
			
			if(currentFrame >= frames.length) {
				if(loop) currentFrame = 0;
				else currentFrame--;
			}
			lastTime = currentTime;
		}
		
	}
	
	public ImageResource getImage() {
		return frames[currentFrame];
	}
}
