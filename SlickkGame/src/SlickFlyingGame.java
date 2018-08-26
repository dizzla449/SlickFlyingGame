import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class SlickFlyingGame extends BasicGame {
	//Constants
	private static final float WIDTH = 800f;
	private static final float HEIGHT = 600f;
	//Image instance variables
	private Image background;
	private Image balloon;
	private Image plane;
	
	//Other instance variables
	private float timer = 0f;
	private int killCount = 0;
	private float balloonX = 0f;
	private float balloonY = 0f;
	private player player;
	private BoundingBox bbb;
	
	//Constructor
	public SlickFlyingGame() {
		super("Slick Flying Game");
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		//Initializing background and other images
		background = new Image("assets/land.jpeg");
		balloon = new Image("assets/balloon.png");
		bbb = new BoundingBox(balloon, balloonX, balloonY);
		plane = new Image("assets/plane.png");
		player = new player(plane);
		scramble();
	}


	// randomises the balloon's position
	private void scramble() {
		balloonX =(float) Math.random()*WIDTH;
		balloonY = (float)Math.random()*HEIGHT;
		bbb.setX(balloonX);
		bbb.setY(balloonY);
	}


	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		//Take users input to move plane
		player.update(input, delta);
		if (player.collision(bbb)) {
			scramble();
			killCount++;
			timer = 0f;
		} else {timer+=delta;}
		if (timer>2500f) {
			System.exit(0);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.draw();
		player.render();
		balloon.drawCentered(balloonX, balloonY);
		g.drawString("Kill count =   "+killCount , 25, 90);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new SlickFlyingGame());
			app.setDisplayMode((int)WIDTH, (int)HEIGHT, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}