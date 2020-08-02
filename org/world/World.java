package org.world;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.graphics.Graphics;
import org.input.MouseInput;
import org.noise.WorldCreator;
import org.objects.Player;
import org.resource.ImageResource;

public class World extends WorldCreator{
	
	public static ConcurrentLinkedQueue<Tile> tiles = new ConcurrentLinkedQueue<Tile>();
	public static ConcurrentLinkedQueue<GameObject> gameObjects = new ConcurrentLinkedQueue<GameObject>();
	public static Boundary[] bounds = new Boundary[4];
	public static ImageResource ch = null;
	
	public static void addTile(Tile tile) { tiles.offer(tile); }
	public static void clearTiles() { tiles.clear(); }
	
	public static void addObject(GameObject obj) { gameObjects.offer(obj); }
	public static void removeObject(GameObject obj) { gameObjects.remove(obj); }
	
	public static void init() {
		//Crosshair
		ch = new ImageResource("/res/crosshair.png");
		
		//Tiles
		for(int i=0; i<16; i++) Tile.tileSet[i] = new ImageResource("/res/"+i+".png");
		createWorld();
		
		//Boundaries
		float w = size * (cols-1)/2;
		float h = size * (rows-1)/2;
		bounds[0] = new Boundary(-w, -h, -w, h);
		bounds[1] = new Boundary(-w, -h, w, -h);
		bounds[2] = new Boundary(-w, h, w, h);
		bounds[3] = new Boundary(w, -h, w, h);
		
		//Objects
		addObject(new Player());
	}
	
	public static void update() {
		//Update All Objects
		for(GameObject obj : gameObjects) obj.update();
	}
	
	public static void render() {	
		
		//Render All Tiles
		for(GameObject tile : tiles) tile.render();
		
		createWorld();
		
		//Render All Objects
		for(GameObject obj : gameObjects) obj.render();
		
		//Render Crosshair
		Graphics.DrawImage(ch, MouseInput.getX(), MouseInput.getY(), 0.2f, 0.2f);
		
		//Outer Boundaries
		for(int i=0; i<4; i++) bounds[i].render();
	}
	
}
