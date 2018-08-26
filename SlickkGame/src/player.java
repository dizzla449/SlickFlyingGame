import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;


public class player{
	
	private float x = 400f;
	private float y = 300f;
	private Image image;
	private final float SPEED = 1f;
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
			image.rotate(0.5f);
		} if (input.isKeyDown(Input.KEY_LEFT)) {
			image.rotate(-0.5f);
		} if (input.isKeyDown(Input.KEY_DOWN)) {
			x -= Math.sin(Math.toRadians(image.getRotation()))*SPEED*delta;
			y += Math.cos(Math.toRadians(image.getRotation()))*SPEED*delta;
		} if (input.isKeyDown(Input.KEY_UP)) {
			x += Math.sin(Math.toRadians(image.getRotation()))*SPEED*delta;
			y -= Math.cos(Math.toRadians(image.getRotation()))*SPEED*delta;
		} if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		} if (x < 0) {
			x = 0;
		}
		if (x > 800) {
			x = 800;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > 600) {
			y = 600;
		}
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

}