package assignment10;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {
	
	private double x,y,size;
	private List <Snake> body; 
	private List <Double> previous;
	
	public Snake(double x, double y, List body) {
		this.x = x;
		this.y = y;
		this.size = .01;
		this.body = body;
		this.previous = new LinkedList<>();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(x, y, size);
		for (int i = 0; i < body.size(); i ++) {
			Snake snakeI = body.get(i); 
			snakeI.setX(previous.get((previous.size()-2*(i+1))));
			snakeI.setY((previous.get(previous.size()-1-(2*(i)))));
			StdDraw.filledCircle(snakeI.getX(), snakeI.getY(), size);
		}
	}
	
	public void moveUp() {
		StdDraw.clear();
		y += .02;
	}
	
	public void moveRight() {
		StdDraw.clear();
		x += .02;
	}
	
	public void moveDown() {
		StdDraw.clear();
		y -= .02;
	}
	
	public void moveLeft() {
		StdDraw.clear();
		x -= .02;
	}
	
	public void addBody() {
		body.add(new Snake (.5, .5, this.body));
	}
	
	public void storePrevious() {
		previous.add(this.x);
		previous.add(this.y);
	}
	
	public boolean checkSelf() {
		boolean touching = false; 
		for (int i = 0; i < body.size(); i++) {
			if ((2*.01-1e-13) > Math.sqrt((Math.pow(body.get(i).getX() - this.x, 2)) + (Math.pow(body.get(i).getY() - this.y, 2)))) {
				touching = true;
			}
		}
		return touching;
	}
	
}
