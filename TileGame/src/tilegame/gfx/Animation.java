package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	//speed of animation
	private int speed, index;
	private long lastTime, timer;
	//frames we want to rotate through
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}
	
	//ticks everytime we call the game
	public void tick() {
		//time passed since tick method has been called from the other tick
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			//resets animation
			timer = 0;
			
			if(index >= frames.length) {
				//resets animation
				index = 0;
			}
		}
		
	}	
	
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	
	
	
	
}
