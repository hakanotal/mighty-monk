package org.engine;

import org.graphics.Renderer;
import org.world.World;

public class Main {

	public static void main(String[] args) {
		World.init();
		Renderer.init();
		GameLoop.start();
	}

}
