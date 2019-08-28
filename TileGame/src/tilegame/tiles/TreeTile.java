package tilegame.tiles;

import java.awt.image.BufferedImage;

import tilegame.gfx.Assets;

public class TreeTile extends Tile {

	public TreeTile(int id) {
		super(Assets.tree, id);
		// TODO Auto-generated constructor stub
	}

	public boolean isSolid() {
		return true;
	}
	
}
