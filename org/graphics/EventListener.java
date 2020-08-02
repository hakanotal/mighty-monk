package org.graphics;

import org.world.World;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class EventListener implements GLEventListener{

	public static GL2 gl = null;
	
	public void init(GLAutoDrawable drawable) {
		//INITIALIZE
		gl = drawable.getGL().getGL2();
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glEnable(GL2.GL_BLEND);
		
		gl.glEnable(GL2.GL_LINE_SMOOTH);
		gl.glLineWidth(3.0f);
		
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void display(GLAutoDrawable drawable) {
		//DRAW LOOP
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		gl.glTranslatef(-Renderer.cameraX, -Renderer.cameraY, 0);
		World.render();	
		gl.glTranslatef(Renderer.cameraX, Renderer.cameraY, 0);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {	
		//RESIZE
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		Renderer.WIDTH = Renderer.getWindow().getWidth();
		Renderer.HEIGHT = Renderer.getWindow().getHeight();
		Renderer.UNITS_H = Renderer.getWindow().getHeight() / (Renderer.getWindow().getWidth() / Renderer.UNITS_W);
		gl.glOrtho(-(Renderer.UNITS_W/2), Renderer.UNITS_W/2, -(Renderer.UNITS_H/2), Renderer.UNITS_H/2, -1, 1);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	
	public void dispose(GLAutoDrawable drawable) {
		//---
	}
}
