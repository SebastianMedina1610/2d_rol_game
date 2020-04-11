package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener{
	
	private final static int numberKeys = 120;
	private boolean[] keys = new boolean[numberKeys];
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	} 

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {	
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {	
		
	}

}
