package tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
		
	}
	//returns bufferedImage but cropped
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width,  height);
	}
	
	
	
	
	
}
