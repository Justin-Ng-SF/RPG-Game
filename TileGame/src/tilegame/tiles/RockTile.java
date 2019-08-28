package tilegame.tiles;

import java.awt.image.BufferedImage;

import tilegame.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		// TODO Auto-generated constructor stub
		super(Assets.rock, id);

	}

	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	
	
	
	
}
