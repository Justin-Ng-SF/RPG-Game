package tilegame.worlds;

import java.awt.Graphics;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Tree;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//entities
	private EntityManager entityManager;

	//path is the world.txt file in resources
	public World(Handler handler, String path) {
		this.handler = handler;

		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Tree(handler, 100, 250));
		
		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setX(spawnY);

	}

	public void tick() {
		entityManager.tick();
	}


	//displays the tile
	public void rendor(Graphics g) {
		//=========helps rendor only the map camera, what we can see rather the entire map=========v
		// math.max helps makes sure to not go under 0 'left side'
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / 
		//makes sure to rendor the left most 'tile' of the map,  without rendoring the outside		
				Tile.TILE_WIDTH);
		//right side
		int xEnd = (int) Math.min(width,  (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);

		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / 
				Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height,  (handler.getGameCamera().getyOffset() + handler.getWidth()) / Tile.TILE_HEIGHT + 1);



		/*========rendors entire map
		for(int y=0; y < height; y++) {
			for(int x=0; x < width; x++) {
			 getTile(x, y).rendor(g, (int) (x * Tile.TILE_WIDTH - game.getGameCamera().getxOffset()),
					 (int) (y * Tile.TILE_HEIGHT - game.getGameCamera().getyOffset()));
			 //multiplying by tilewidth/tileheight spaces the tile from each tile
			//game.getgamecamera.getoffset helps move camera	
		 */
		for(int y = yStart; y < yEnd; y++) {
			for(int x= xStart; x < xEnd; x++) {
				getTile(x, y).rendor(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));




			}

		}

		entityManager.render(g);
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}
	//looks up tile id and returns it
	public Tile getTile(int x, int y) {
		//if player is outside of map, it will tell us that player is on grasstile even if not
		if(x < 0 || y < 0 || x >= width || y>= height)
			return Tile.grassTile;




		//tiles[x][y] is the index tile,
		Tile t = Tile.tiles[tiles[x][y]]; 

		//if the tile doesnt exist, return the grass tile
		if(t==null)
			return Tile.grassTile;
		return t;

	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		// an array of every single number in the world.txt file in resources that has been split 
		String[] tokens = file.split("\\s+");
		//splitting it on any white space and making each number into a string to access each one seperately

		//reads in token0(first token) and assigns it as the width
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY= Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}




	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}

}







