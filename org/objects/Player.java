package org.objects;

import org.graphics.Animation;
import org.graphics.Graphics;
import org.graphics.Renderer;
import org.input.KeyInput;
import org.input.MouseInput;
import org.resource.ImageResource;
import org.world.GameObject;
import org.world.Tile;
import org.world.World;

import com.jogamp.newt.event.KeyEvent;

public class Player extends GameObject {

	private boolean jumping = false;
	private boolean running = false;

	private float x_velocity = 0.0f;
	private float y_velocity = 0.0f;
	private float gravity = 0.8f;

	public Player() {
		// Set Data
		x = -1;
		y = 3;
		width = 2f;
		height = 1.5f;

		// Import Sprites
		ImageResource[] idle = new ImageResource[4];
		for (int i = 1; i <= 4; i++)
			idle[i - 1] = new ImageResource("/res/idle" + i + ".png");

		ImageResource[] lightnings = new ImageResource[12];
		for (int i = 1; i <= 12; i++)
			lightnings[i - 1] = new ImageResource("/res/lightning_line1a" + i + ".png");

		ImageResource[] jump = new ImageResource[2];
		for (int i = 1; i <= 2; i++)
			jump[i - 1] = new ImageResource("/res/jump" + i + ".png");

		ImageResource[] run = new ImageResource[6];
		for (int i = 1; i <= 6; i++)
			run[i - 1] = new ImageResource("/res/walk" + i + ".png");

		// Set Animations
		animations = new Animation[5];
		animations[0] = new Animation(idle, true);
		animations[1] = new Animation(lightnings, true);
		animations[2] = new Animation(lightnings, true);
		animations[3] = new Animation(jump, true);
		animations[4] = new Animation(run, true);
	}

	public void update() {
		float x_prev = x;
		float y_prev = y;

		// Inputs
		if (KeyInput.getKey(KeyEvent.VK_W) && !jumping) {
			y_velocity += 25.0f;
			jumping = true;
		}
		if (KeyInput.getKey(KeyEvent.VK_D)) {
			x_velocity += 1.0f;
			running = true;
		}
		if (KeyInput.getKey(KeyEvent.VK_A)) {
			x_velocity -= 1.0f;
			running = true;
		}
		
		x += x_velocity / 60.0f;
		y += y_velocity / 60.0f;
		x_velocity *= 0.9f;
		y_velocity *= 0.9f;

		if (Math.floor(Math.abs(x_velocity)) == 0) running = false;
		

		// Detect Collisions
		switch (detectCollision()) {
		case -1:// On the flat ground
			y_velocity = 0;
			y = y_prev;

			jumping = false;
			break;

		case 0: // In the air
			y_velocity -= gravity;
			break;

		case 1: // Normal collision
			x_velocity = 0;
			x = x_prev;

			y_velocity = 0;
			y = y_prev + 0.01f;

			jumping = false;
			running = false;
			break;
		}

		// Rotation
		rotation = (float) Math.toDegrees(Math.atan2(MouseInput.getY() - y, MouseInput.getX() - x));

		// Camera
		Renderer.cameraX = x;
		Renderer.cameraY = y;
	}

	public void render() {
		// Animations and Player
		animations[1].play();
		animations[2].play();

		if (jumping) {
			if (x_velocity < 0) Graphics.DrawImage(animations[3].getImage(), x, y, -width, height);
			else Graphics.DrawImage(animations[3].getImage(), x, y, width, height);
			animations[3].play();
		} 
		else if (running) {
			if (x_velocity < 0.01f) Graphics.DrawImage(animations[4].getImage(), x, y, -width, height);
			else if(x_velocity > 0.01f) Graphics.DrawImage(animations[4].getImage(), x, y, width, height);
			animations[4].play();
		} 
		else { //Idle
			Graphics.DrawImage(animations[0].getImage(), x, y, width, height);
			animations[0].play();
		}

		// Lightnings
		Graphics.setRotation(rotation);
		Graphics.DrawImage(animations[1].getImage(), x + 1, y, width, height);
		Graphics.DrawImage(animations[2].getImage(), x - 1, y, width, height);
		Graphics.setRotation(0);
	}

	public int detectCollision() {
		for (Tile tile : World.tiles) {
			// Only checks the near tiles
			if (Math.abs(tile.x - x) < World.size * 2 && Math.abs(tile.y - y) < World.size * 2) {
				// Calculates the distances to boundaries
				for (int i = 0; i < tile.b_count; i++) {
					int state = tile.b[i].getDistance(x, y - 0.4f);
					if (state != 0) return state;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			int state = World.bounds[i].getDistance(x, y - 0.4f);
			if (state != 0) return state;
		}
		return 0;
	}

}
