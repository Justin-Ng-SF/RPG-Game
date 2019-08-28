package tilegame.states;

import java.awt.Graphics;

import tilegame.Game;
import tilegame.Handler;



public abstract class State {//any class that extends this class MUST contain these methods
	//this class is a class for classes that will have a state in which it will all have similar variables/methods etc
	
	
	//this code has nothing to do with the abstract part of the class
	//does not need to be in an abstract class bc all static,, accesible from anywhere in game and we
	// do not use abstract anywhere in it
	//can think of this as a seperate class, can put in seperate class if wanted to
	//basically the game state manager========v
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	//========================^
	
	
	
	
	
	
	//class
	
	protected Handler handler;
	
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();//every state has a tick
	
	public abstract void render(Graphics g);//every state has a render method
			//state is allowed to draw to screen directly
	
	
	
	
	
	
	
}
