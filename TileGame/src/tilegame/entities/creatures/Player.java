package tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tilegame.Game;
import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

public class Player extends Creature {

	//animations
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	
	//x and y is location, game is used so we can access game class
	public Player(Handler handler, float x, float y) {
		// TODO Auto-generated constructor stub
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		//half of what you expect
		bounds.x = 17;
		bounds.y = 24;
		bounds.width = 16;
		bounds.height = 26;
		
		//animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		getInput();
		move();//from creature class
		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		//from creature class
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		
		
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//must convert float x to int x
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 
				width, height, null);
		
		
		
		/*  ======== hitbox of player
		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
					(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		*/
		
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
			if(xMove < 0) {
				return animLeft.getCurrentFrame();
			} else if(xMove > 0) {
				return animRight.getCurrentFrame();
			} else if(yMove < 0) {
				return animUp.getCurrentFrame();
			} else {
				return animDown.getCurrentFrame();
			}

		
	}

}
