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

	public World(Handler handler, String path) {//path is the world.txt file in resources
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

	public void rendor(Graphics g) {//displays the tile

		//=========helps rendor only the map camera, what we can see rather the entire map=========v
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / // math.max helps makes sure to not go under 0 'left side'
				Tile.TILE_WIDTH);//makes sure to rendor the left most 'tile' of the map,  without rendoring the outside		
		int xEnd = (int) Math.min(width,  (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		//'right side'^^^^^^^
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / 
				Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height,  (handler.getGameCamera().getyOffset() + handler.getWidth()) / Tile.TILE_HEIGHT + 1);
		//=====================^


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

	public Tile getTile(int x, int y) {//looks up tile id and returns it
		if(x < 0 || y < 0 || x >= width || y>= height)//if player is outside of map, it will tell us that player is on grasstile even if not
			return Tile.grassTile;





		Tile t = Tile.tiles[tiles[x][y]];//tiles[x][y] is the index tile, 

		if(t==null)//if the tile doesnt exist, return the grass tile
			return Tile.grassTile;
		return t;

	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");// an array of every single number in the world.txt file in resources that has been split 
		//splitting it on any white space and making each number into a string to access each one seperately


		width = Utils.parseInt(tokens[0]);//reads in token0(first token) and assigns it as the width
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







