package org.input;

import org.engine.GameLoop;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyInput implements KeyListener{
	
	private static boolean[] keys = new boolean[256];
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		if (e.isAutoRepeat() == false ) {
	         if(e.getKeyCode() == KeyEvent.VK_ESCAPE) GameLoop.finish();  
			keys[e.getKeyCode()] = false;
	    }
		
	}
	
	public static boolean getKey(int keyCode) {
		return keys[keyCode];
	}

}
