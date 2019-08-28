package tilegame.states;

import java.awt.Graphics;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Tree;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;
import tilegame.worlds.World;

public class GameState extends State{
	//when extending State(abstract class), must implement abstract class' methods

	private World world;

	
	
	public GameState(Handler handler) {
		super(handler);

		world = new World(handler, "resources/worlds/world1.txt");//reads in worlds1.txt 
		handler.setWorld(world);
		

	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		world.tick();

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		world.rendor(g);

	}//most used game state, where actual gameplay is at
	
	
	
	

}
