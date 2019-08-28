package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;//storing if our keys are being pressed or not
	public boolean up, down, left, right;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_UP];//up arrow key
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {//sets key to true if being pressed down
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
		System.out.println("Pressed!");
	}

	@Override
	public void keyReleased(KeyEvent e) {//sets key to false if released
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
