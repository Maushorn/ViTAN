package textadventure;

import java.io.Serializable;

public class Coord implements Serializable{

	static final long serialVersionUID = 555185l;
	
	private int x;
	private int y;
	
	public Coord(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}
}
