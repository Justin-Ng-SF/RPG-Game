package tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import tilegame.Game;
import tilegame.Handler;

public abstract class Entity {
	
	protected Handler handler;
	//private variable, but classes that extend this can use it
	//use float so it is more smooth
	protected float x, y;
	protected int width, height;//used for dimensions of entity
	
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//for hitbox, aka bounds of entity
		bounds = new Rectangle(0, 0, width, height);
	}
	

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	//tests each entity to see if it collides with entity
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		//get list of entitties and loop through it
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))//makes sure it does not check against itself
				continue;
			if(e.getCollisionBounds(0f,  0f).intersects(getCollisionBounds(xOffset, yOffset)))
			//if two rectancles overlap, return true
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset),  (int) (y + bounds.y + yOffset), 
				bounds.width, bounds.height);
	}
	
	
	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	
}			
