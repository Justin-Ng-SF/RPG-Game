package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.input.KeyManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.SettingState;
import tilegame.states.State;

//implements runnable allows Game class to run on a thread, needs run method
public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	//thread that we can run on, thread is a mini program, can run a class seperately from program
	private Thread thread;
	//helps with efficiency
	
	//way for cpu to draw things to screen, using buffers
	private BufferStrategy bs;
	private Graphics g;
	
	//states
	private State gameState;
	private State menuState;
	private State settingState;
	
	//input
	private KeyManager keyManager;
	
	//camera
	private GameCamera gameCamera;
	
	private Handler handler;
	
	

	//private BufferedImage test;//test is the image being used
	//private SpriteSheet sheet;
	
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		
	}
	
	
	private void init() {//initalizer
		display = new Display(title, width, height);
		//allows us to access keyboard by passing to keyManager
		display.getFrame().addKeyListener(keyManager);
		Assets.init();//initalizes assets, aka images,sound,audio
		//test = ImageLoader.loadImage("/textures/sprite.png");
		//sheet = new SpriteSheet(test);
		
		handler = new Handler(this);
		
		//this is for the Game object, aka this Game class
		gameCamera = new GameCamera(handler, 0, 0);
		
		//changing from type state to gamestate, this is important
		//bc it lets it use the game state manager part in the State class
		//lets us declare it as a State but initialize it to w/e we want
		gameState = new GameState(handler);

		menuState = new MenuState(handler);
		settingState = new SettingState(handler);
		
		
		//sents current state of game to gamestate that we just created
		State.setState(gameState);
	}
	
	//updates all variables, i.e  positions of objects, etc---game loop
	private void tick() {
		keyManager.tick();
		//if state class exists, 
		if(State.getState() != null)
		//calls tick method of current state
		
			State.getState().tick();
									
	}
	//rendors(draw) everything to screen---game loop
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs==null) {
			//if there is no buffer strat, create 3 buffers
			display.getCanvas().createBufferStrategy(3);
			return;//if we dont return we get errors
		}
		//g is essentially the paintbrush, allows to draw to screen
		g = bs.getDrawGraphics();
		
		//before drawing, clear screen 
		//clears everything within bounds of width and height
		g.clearRect(0, 0, width, height);
		//draw here--v
		
		//if state class exists
		if(State.getState() != null)
		//calls rendor method of current state
			State.getState().render(g);
		
		//end drawing--^
		//tells code that it is done drawing and shows
		bs.show();
		//makes sure graphics object is done w/ properly
		g.dispose();
		
	}
	
	//when using impements runnable, you need a run method inside the class
	public void run() {
		//majority of where our game code goes
		init();
		
		//for tick method//limits all comp to run at this speed regardless of how good===========================v
		int fps = 60;
		//1000000000 = 1billion nano sec = 1 second
		double timePerTick = 1000000000/fps;
		//amt of time we have until we need to call tick/render method
		double delta = 0;
		//current time of comp
		long now;
		//returns amt of time in nano secs that comp is running at, i.e. returns the time of our internal cpu clock
		long lastTime = System.nanoTime();
		//time until we hit 1 second
		long timer = 0;
		//once hit 1 second,shows how many times tick and render method is called
		int ticks = 0;
		//===========================================^
		
		while(running) {
			//for tick method===============================================v
			now = System.nanoTime();//tick
			// (amt of time passed since last called this line) / amt of maximum time allowed to have tic and render method
			//tells comp when and when to not call tick/render method
			delta += (now-lastTime) / timePerTick; 
													
			//amt of time since we last ran this block of code
			timer += now -lastTime;
			lastTime = now;
			//============================================================^
			

			//helps to show how many times tick/rendor method is used
			if(delta >=1 ) {
				tick();
				render();
				//for ticks method
				ticks++;
				//for ticks method
				delta--;
			}
			
			//checks to see if timer exceeds 1 second, 
			//after 1 second it shows how many ticks occured in 1 second, 
			//shows tics and frames every second
			if(timer>= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	
		//runs stop if not already stopped
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	//synchornized keyword is when we work with threads directly, 
										//so nothing gets messed up in process
										//i.e when using start/stop
	public synchronized void start() {
		if(running)
			return;
		running = true;

		//thread constructor takes in class we want to run, 
		//i.e the Game class, 'this' refers to our Game class
		//initalizes thread w/ new thread
		thread = new Thread(this);

		//calls the run method
		thread.start();
	}
	
	//stops thread
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			//stops thread safely
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
