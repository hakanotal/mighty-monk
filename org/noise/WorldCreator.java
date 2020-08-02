package org.noise;

import org.world.Tile;
import org.world.World;

public class WorldCreator {
	// Marching Squares Algorithm

	public static int size = 2;
	public static int rows = 20;
	public static int cols = 30;
	public static float noiseValue = 0.5f;
	public static double zoff = 0.0f;
	public static OpenSimplexNoise noise = new OpenSimplexNoise(1);

	public static int[][] points = new int[rows][cols];

	public static void createWorld() {
		double yoff = 0.0f;
		for (int j = 0; j < rows; j++) {
			double xoff = 0.0f;
			for (int i = 0; i < cols; i++) {
				points[j][i] = (int) Math.ceil(noise.eval(xoff, yoff, zoff));
				xoff += noiseValue;
			}
			yoff += noiseValue;
		}
		zoff += 0.001f;

		World.clearTiles();
		for (int j = 0; j < rows - 1; j++) {
			for (int i = 0; i < cols - 1; i++) {
				float tile_x = i + (size - cols) / 2.0f;
				float tile_y = j + (size - rows) / 2.0f;
				int type = calculate(i, j);
				
				World.addTile(new Tile(tile_x * size, tile_y * size, size, type));
			}
		}
	}

	public static int calculate(int x, int y) {
		int p1 = points[y + 1][x];
		int p2 = points[y + 1][x + 1];
		int p3 = points[y][x + 1];
		int p4 = points[y][x];
		return p1 * 1 + p2 * 2 + p3 * 4 + p4 * 8;
	}
}
