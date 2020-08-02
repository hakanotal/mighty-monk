package org.world;

import org.graphics.Animation;
import org.graphics.Graphics;
import org.resource.ImageResource;

public class Tile extends GameObject {

	public static ImageResource[] tileSet = new ImageResource[16];

	public Boundary[] b = new Boundary[2];
	public int b_count = 0;

	public Tile(float X, float Y, float size, int type) {
		// Set Data
		x = X;
		y = Y;
		width = size;
		height = size;
		b_count = findLine(type);

		// Set Sprite
		ImageResource[] tile = new ImageResource[1];
		tile[0] = tileSet[type];

		// Set Animation
		animations = new Animation[1];
		animations[0] = new Animation(tile, true);
	}

	public void render() {
		for (int i = 0; i < animations.length; i++) {
			animations[i].play();
			Graphics.DrawImage(animations[0].getImage(), x, y, width, height);
		}
		
		for (int i = 0; i < b_count; i++) {
			b[i].render();
		}
	}

	public int findLine(int type) {
		float a_x = x;
		float a_y = y + height / 2;

		float b_x = x + width / 2;
		float b_y = y;

		float c_x = x;
		float c_y = y - height / 2;

		float d_x = x - width / 2;
		float d_y = y;

		switch (type) {
		case 1:
			b[0] = new Boundary(a_x, a_y, d_x, d_y);
			b[1] = null;
			break;
		case 2:
			b[0] = new Boundary(a_x, a_y, b_x, b_y);
			b[1] = null;
			break;
		case 3:
			b[0] = new Boundary(d_x, d_y, b_x, b_y);
			b[1] = null;
			break;
		case 4:
			b[0] = new Boundary(b_x, b_y, c_x, c_y);
			b[1] = null;
			break;
		case 5:
			b[0] = new Boundary(a_x, a_y, d_x, d_y);
			b[1] = new Boundary(b_x, b_y, c_x, c_y);
			break;
		case 6:
			b[0] = new Boundary(a_x, a_y, c_x, c_y);
			b[1] = null;
			break;
		case 7:
			b[0] = new Boundary(d_x, d_y, c_x, c_y);
			b[1] = null;
			break;
		case 8:
			b[0] = new Boundary(d_x, d_y, c_x, c_y);
			b[1] = null;
			break;
		case 9:
			b[0] = new Boundary(a_x, a_y, c_x, c_y);
			b[1] = null;
			break;
		case 10:
			b[0] = new Boundary(a_x, a_y, b_x, b_y);
			b[1] = new Boundary(d_x, d_y, c_x, c_y);
			break;
		case 11:
			b[0] = new Boundary(c_x, c_y, b_x, b_y);
			b[1] = null;
			break;
		case 12:
			b[0] = new Boundary(d_x, d_y, b_x, b_y);
			b[1] = null;
			break;
		case 13:
			b[0] = new Boundary(a_x, a_y, b_x, b_y);
			b[1] = null;
			break;
		case 14:
			b[0] = new Boundary(a_x, a_y, d_x, d_y);
			b[1] = null;
			break;
		default:
			break;
		}
		if (b[0] == null && b[1] == null) return 0;
		else if(b[0] != null && b[1] == null) return 1;
		else return 2;
	}
}
