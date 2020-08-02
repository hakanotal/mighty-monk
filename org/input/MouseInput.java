package org.input;

import org.graphics.Renderer;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

public class MouseInput implements MouseListener {
	
	private static float x = 0;
	private static float y = 0;
	
	public static float getX() {
		return (x * Renderer.UNITS_W) / Renderer.WIDTH - (Renderer.UNITS_W/2) + Renderer.cameraX;
	}
	
	public static float getY() {
		return (-y * Renderer.UNITS_H) / Renderer.HEIGHT + (Renderer.UNITS_H/2) + Renderer.cameraY;
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
		//
	}

	public void mouseEntered(MouseEvent e) {
		//
	}

	public void mouseExited(MouseEvent e) {
		//
	}

	public void mousePressed(MouseEvent e) {
		//
	}

	public void mouseReleased(MouseEvent e) {
		//
	}
	
	public void mouseWheelMoved(MouseEvent e) {
		//
	}
	
}
