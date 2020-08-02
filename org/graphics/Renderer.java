package org.graphics;

import org.input.KeyInput;
import org.input.MouseInput;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

public class Renderer {
	
	private static GLWindow window = null;
	private static GLProfile profile = null;
	
	public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	public static float UNITS_W = 16;
	public static float UNITS_H = 0;
	
	public static float cameraX = 0;
	public static float cameraY = 0;
	
	public static void init() {
		GLProfile.initSingleton();
		profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(profile);
		
		window = GLWindow.create(caps);
		window.setSize(WIDTH, HEIGHT);
		window.setResizable(false);
		
		window.addGLEventListener(new EventListener());
		window.addMouseListener(new MouseInput());
		window.addKeyListener(new KeyInput());
		
		window.setFullscreen(true);
		window.requestFocus();
		window.setVisible(true);
		window.setPointerVisible(false);
	}
	
	public static void render() {
		if(window != null) window.display();
	}
	
	public static GLWindow getWindow() { return window; }
	public static GLProfile getProfile() { return profile; }
}
