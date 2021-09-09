package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import tilegame.Handler;
import tilegame.entities.creatures.Player;
//managers entities
public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	//render order
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
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
		//list of entities
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	
	public void tick() {
		//itterates through entities
		for(int i = 0; i < entities.size(); i++) {
			//gets the entity at i
			Entity e = entities.get(i);
			//ticks the entity
			e.tick();
		}
		//for render order
			entities.sort(renderSorter);
	}
	
	public void render(Graphics g) {
		//creates an Entity e, and does it for each entity in array list Entity
		for(Entity e : entities) {
			e.render(g);
		}
	
	}



	public void addEntity(Entity e) {
		//adds entity to array list
		entities.add(e);
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
