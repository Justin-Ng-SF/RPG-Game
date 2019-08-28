package tilegame.tiles;

import java.awt.image.BufferedImage;

import tilegame.gfx.Assets;

public class WaterTile extends Tile{

	public WaterTile(int id) {
		super(Assets.water, id);
		// TODO Auto-generated constructor stub
	}

	public boolean isSolid() {
		return true;
	}
	
}
