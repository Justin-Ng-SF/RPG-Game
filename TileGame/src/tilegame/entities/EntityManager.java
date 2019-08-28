package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import tilegame.Handler;
import tilegame.entities.creatures.Player;

public class EntityManager {//managers entities
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {//render order
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) //		
				return -1;
			return 1;
			
		}
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();//list of entities
		addEntity(player);
	}
	
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {//itterates through entities
			Entity e = entities.get(i);//gets the entity at i
			e.tick();//ticks the entity
		}
			entities.sort(renderSorter);//for render order
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {//creates an Entity e, and does it for each entity in array list Entity
			e.render(g);
		}
	
	}



	public void addEntity(Entity e) {
		entities.add(e);//adds entity to array list
	}
	
	
	//getter/setter
	
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public ArrayList<Entity> getEntities() {
		return entities;
	}


	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
