package tilegame.tiles;

import java.awt.image.BufferedImage;

import tilegame.gfx.Assets;

public class GrassTile extends Tile{

	public GrassTile(int id) {
		// TODO Auto-generated constructor stub
		super(Assets.grass, id);
		
	}
	//bc it does not have the issolid method from Tile class, it will run isSolid from the Tile class as false

}
