package tilegame.entities.creatures;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Creature extends Entity {

	//cannot see tick and render method bc it is abstract
	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 50,
							DEFAULT_CREATURE_HEIGHT = 50;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	
	
	public Creature(Handler handler,float x, float y, int width, int height) {
		//super refers to Entity class
		super(handler, x, y, width, height);
		// TODO Auto-generated constructor stub
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	
	public void moveX() {
		//moving right
		if(xMove > 0){
			
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			//checks bottom right and top right pixels
			if(!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILE_HEIGHT ) &&
			//if no solid tile, if collision tile is false, move
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			}else {
				//set x position of creature to convert to pixel coordinates
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
				//helps go closer up to a tile that we colide with
			}

			//moving left
		}else if(xMove < 0) {
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILE_HEIGHT ) &&
			//if no solid tile, if collision tile is false, move
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				//set x position of creature to convert to pixel coordinates
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
				//helps go closer up to a tile that we colide with
			}
		}
		
	}
	
	
	public void moveY() {
		//moving up
		if(yMove < 0) {
			int ty = (int) (y + yMove + bounds.y)/ Tile.TILE_HEIGHT;

			if(!collisionWithTile((int) (x + bounds.x)/ Tile.TILE_WIDTH,  ty) &&
			//collision check
					!collisionWithTile((int) (x + bounds.x + bounds.width)/ Tile.TILE_WIDTH,  ty)) {
				y += yMove;
			} else {
				//set x position of creature to convert to pixel coordinates
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
				//helps go closer up to a tile that we colide with
			}
		}else if(yMove > 0) {//moving down
			int ty = (int) (y + yMove + bounds.y + bounds.height)/ Tile.TILE_HEIGHT;

			if(!collisionWithTile((int) (x + bounds.x)/ Tile.TILE_WIDTH,  ty) &&
			//collision check
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH,  ty)) {
				y += yMove;
			} else {
				//set x position of creature to convert to pixel coordinates
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
				//helps go closer up to a tile that we colide with
			}
		}

	}
	
	//takes in a tile coordinate to check if it is solid
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	//getters/setters allows us to use outside of class
	
	
	
	public float getxMove() {
		return xMove;
	}


	public void setxMove(float xMove) {
		this.xMove = xMove;
	}


	public float getyMove() {
		return yMove;
	}


	public void setyMove(float yMove) {
		this.yMove = yMove;
	}



	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	
	
}
