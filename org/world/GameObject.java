package org.world;

import org.graphics.Animation;

public abstract class GameObject {
	public float x = 0;
	public float y = 0;
	public float width = 1;
	public float height = 1;
	public float rotation = 0;
	
	public Animation[] animations;
	
	public void update() {
		//Virtual Function
	}
	
	public void render() {
		//Virtual Function
	}
}
