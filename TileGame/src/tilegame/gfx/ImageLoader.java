package tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//load images
public class ImageLoader {

	public static BufferedImage loadImage(String path) {//path is name of image and where it is located
		
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}//loads path
		return null;//gets rid of error
	}
	
	
	
	
	
	
}
