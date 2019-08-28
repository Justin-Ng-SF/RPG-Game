package tilegame.gfx;

import java.awt.image.BufferedImage;

//any image/sound/piece of music
public class Assets {
	
	private static final int width = 100, height = 100;
	
	public static BufferedImage grass, water, tree, rock, player;
	
	public static BufferedImage[] player_down, player_up, player_left, player_right;

	public static void init() {//loads everything into game, should be called only once
		SpriteSheet tileSprite = new SpriteSheet(ImageLoader.loadImage("/textures/tileSprite.png"));//images from sprite.png on resources
		SpriteSheet playerSprite = new SpriteSheet(ImageLoader.loadImage("/textures/playerSprite.png"));
		
		player_down = new BufferedImage[2];//represents the movement and how many different frames represent this movement
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = playerSprite.crop(0,0, width, height);
		player_down[1] = playerSprite.crop(width,0, width, height);
		
		player_up[0] = playerSprite.crop(width * 2,0, width, height);
		player_up[1] = playerSprite.crop(width * 3,0, width, height);
				
		player_right[0] = playerSprite.crop(0, height, width, height);
		player_right[1] = playerSprite.crop(width , height, width, height);
		
		player_left[0] = playerSprite.crop(width * 2, height, width, height);
		player_left[1] = playerSprite.crop(width * 3, height, width, height);
		
		
		player = ImageLoader.loadImage("/textures/player.png");
		grass = tileSprite.crop(width, 0, width, height);
		water = tileSprite.crop(width * 2, 0, width, height);
		tree = tileSprite.crop(0, height, width, height);
		rock = tileSprite.crop(width, height, width, height);
	}

}
