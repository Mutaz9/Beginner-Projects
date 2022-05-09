package assignment10;

import edu.princeton.cs.introcs.StdDraw;

public class Apples {

	private double x,y,size;
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Apples(double x, double y) {
		this.x = x;
		this.y = y;
		this.size = .01;
	}
	
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(x, y, size);
	}

}
