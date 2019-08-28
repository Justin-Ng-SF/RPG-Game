package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index;//speed of animation
	private long lastTime, timer;
	private BufferedImage[] frames;//frames we want to rotate through
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {//ticks everytime we call the game
		timer += System.currentTimeMillis() - lastTime;//time passed since tick method has been called from the other tick
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;//resets animation
			
			if(index >= frames.length) {
				index = 0;//resets animation
			}
		}
		
	}	
	
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	
	
	
	
}
