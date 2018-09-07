import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;


public class player{
	
	private float x = 400f;
	private float y = 300f;
	private float dx =0f;
	private float dy = 0f;
	private Image image;
	private final float SPEED_INCREASER = 0.005f;
	private final float SPEED_DIVISOR = 3f;
	private final float MAX_SPEED = 0.4f;
	private final float TORQUE_SPEED = 0.5f;
	private BoundingBox bb;
	
	public player(Image plane) {
		image = plane;
		bb = new BoundingBox(image, x, y);
	}
	public void update(Input input, int delta) {
		this.move(input, delta);
		bb.setX(x);
		bb.setY(y);
	}
	public void move(Input input, int delta) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			image.rotate(TORQUE_SPEED);
		} if (input.isKeyDown(Input.KEY_LEFT)) {
			image.rotate(-TORQUE_SPEED);
		} if (input.isKeyDown(Input.KEY_DOWN)) { 
			dx -= (Math.sin(Math.toRadians(image.getRotation()))*SPEED_INCREASER)/delta;
			dy += (Math.cos(Math.toRadians(image.getRotation()))*SPEED_INCREASER)/delta;
		} if (input.isKeyDown(Input.KEY_UP)) {
			dx += (Math.sin(Math.toRadians(image.getRotation()))*SPEED_INCREASER)/delta;
			dy -= (Math.cos(Math.toRadians(image.getRotation()))*SPEED_INCREASER)/delta;
		} if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		dx = Math.max(dx, -MAX_SPEED);
		dx = Math.min(dx, MAX_SPEED);
		dy = Math.max(dy, -MAX_SPEED);
		dy = Math.min(dy, MAX_SPEED);
		
		dx = Math.abs(dx)>0.00001f ? dx-((Math.signum(dx))*(SPEED_INCREASER/SPEED_DIVISOR))/delta : dx;
		/*
		if (Math.abs(dx)>0.00001f){
			dx -= ((Math.signum(dx))*(SPEED_ADDITIVE/10))/delta;
		}if (Math.abs(dy)>0.00001f) {
			dy -= ((Math.signum(dy))*(SPEED_ADDITIVE/10))/delta;
		}
		*/
		dy = Math.abs(dy)>0.00001f ? dy-((Math.signum(dy))*(SPEED_INCREASER/SPEED_DIVISOR))/delta : dx;
		
		x += dx*delta;
		y += dy*delta;
		x = Math.min(x, 800f);
		x = Math.max(0f, x);
		y = Math.min(y, 600f);
		y = Math.max(0f, y);
	}
	public void render() {
		image.drawCentered(x, y);
	}
	public int getX() {
		int xCopy = (int) x;
		return xCopy;
	}
	public int getY() {
		int yCopy = (int) y;
		return yCopy;
	}
	public boolean collision(BoundingBox other) {
		return bb.intersects(other);
	}
	public float getDX() { return dx;}
	public float getDY() { return dy;}

}