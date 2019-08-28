package tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//static stuff======v
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);//id of 0 will now refer to the grass tile now
	public static Tile waterTile = new WaterTile(1);
	public static Tile treeTile = new TreeTile(2);
	public static Tile rockTile = new RockTile(3);
	
	//===========^
	
	protected BufferedImage texture;
	protected final int id;
	
	public static final int TILE_WIDTH = 50,
							TILE_HEIGHT = 50;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
		
	}
	
	
	
	
	public void tic() {
		
	}
	
	public void rendor(Graphics g, int x, int y) {
		g.drawImage(texture,  x,  y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getID() {
		return id;
	}
	
	
	
}
