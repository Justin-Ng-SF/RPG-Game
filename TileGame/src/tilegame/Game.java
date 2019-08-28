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


public class Game implements Runnable {//implements runnable allows Game class to run on a thread, needs run method
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;//thread that we can run on, thread is a mini program, can run a class seperately from program
	//helps with efficiency
	
	private BufferStrategy bs;//way for cpu to draw things to screen, using buffers
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
		display.getFrame().addKeyListener(keyManager);//allows us to access keyboard by passing to keyManager
		Assets.init();//initalizes assets, aka images,sound,audio
		//test = ImageLoader.loadImage("/textures/sprite.png");
		//sheet = new SpriteSheet(test);
		
		handler = new Handler(this);
		
		gameCamera = new GameCamera(handler, 0, 0);//this is for the Game object, aka this Game class
		
		gameState = new GameState(handler);//changing from type state to gamestate, this is important
									//bc it lets it use the game state manager part in the State class
									//lets us declare it as a State but initialize it to w/e we want
		menuState = new MenuState(handler);
		settingState = new SettingState(handler);
		
		
		State.setState(gameState);//sents current state of game to gamestate that we just created
	}
	

	private void tick() {//updates all variables, i.e  positions of objects, etc---game loop
		keyManager.tick();
		if(State.getState() != null)//if state class exists, 
			State.getState().tick();//calls tick method of current state
									//used in render method as well
	}
	
	private void render() {//rendors(draw) everything to screen---game loop
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);//if there is no buffer strat, create 3 buffers
			return;//if we dont return we get errors
		}
		g = bs.getDrawGraphics();//g is essentially the paintbrush, allows to draw to screen
		
		//before drawing, clear screen 
		g.clearRect(0, 0, width, height);//clears everything within bounds of width and height
		//draw here--v
		
		if(State.getState() != null)//if state class exists
			State.getState().render(g);//calls rendor method of current state
		
		//end drawing--^
		bs.show();//tells code that it is done drawing and shows
		g.dispose();//makes sure graphics object is done w/ properly
		
	}
	
	public void run() {//when using impements runnable, you need a run method inside the class
		//majority of where our game code goes
		init();
		
		//for tick method//limits all comp to run at this speed regardless of how good===========================v
		int fps = 60;
		double timePerTick = 1000000000/fps;//1000000000 = 1billion nano sec = 1 second
		double delta = 0;//amt of time we have until we need to call tick/render method
		long now;//current time of comp
		long lastTime = System.nanoTime();//returns amt of time in nano secs that comp is running at, i.e. returns the time of our internal cpu clock
		long timer = 0;//time until we hit 1 second
		int ticks = 0;//once hit 1 second,shows how many times tick and render method is called
		//===========================================^
		
		while(running) {
			//for tick method===============================================v
			now = System.nanoTime();//tick
			delta += (now-lastTime) / timePerTick; // (amt of time passed since last called this line) / amt of maximum time allowed to have tic and render method
													//tells comp when and when to not call tick/render method
			timer += now -lastTime;//amt of time since we last ran this block of code
			lastTime = now;
			//============================================================^
			
			if(delta >=1 ) {//<====helps to show how many times tick/rendor method is used
				tick();
				render();
				ticks++;//<====for ticks method
				delta--;//<===for ticks method
			}
			
			if(timer>= 1000000000) {//checks to see if timer exceeds 1 second, after 1 second it shows how many ticks occured in 1 second, shows tics and frames every second
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	
		stop();//runs stop if not already stopped
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
	
	public synchronized void start() {//synchornized keyword is when we work with threads directly, 
										//so nothing gets messed up in process
										//i.e when using start/stop
		if(running)
			return;
		running = true;
		thread = new Thread(this);//thread constructor takes in class we want to run, 
									//i.e the Game class, 'this' refers to our Game class
									//initalizes thread w/ new thread
		thread.start();//calls the run method
	}
	
	public synchronized void stop() {//stops thread
		if(!running)
			return;
		running = false;
		try {
			thread.join();//stops thread safely
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
