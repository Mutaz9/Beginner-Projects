package assignment10;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class SnakeGame {

	
	public static void main(String[] args) {

		StdDraw.enableDoubleBuffering(); 
		
		List <Snake> body = new LinkedList <>();
		Snake snake = new Snake(.5, .5, body);
		Apples apple = null;
		String direction = "";
	
		while (true) {
			snake.draw();
			if (apple == null) {
				apple = new Apples (.75*Math.random()+.24, .75*Math.random()+.24);
			}
			
			if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				break; 
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
				direction = "up";
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
				direction = "right";
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
				direction = "down";
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
				direction = "left";
			}
			
			snake.storePrevious();			
			
			if (direction == "up") {
				snake.moveUp();
			} else if (direction == "right") {
				snake.moveRight();
			} else if (direction == "down") {
				snake.moveDown();
			} else if (direction == "left") {
				snake.moveLeft();
			}
			
			if (snake.getX()>1 || snake.getX()<0 || snake.getY()>1 || snake.getY()<0) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(.5, .5, "Game Over!");
				StdDraw.show(30);
				break; 
			} 
			
			snake.draw(); 
			
			if (snake.checkSelf() == true) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(.5, .5, "Game Over!");
				StdDraw.show(30);
				break; 
			}
			
			apple.draw();
			if ((2*.01) >= Math.sqrt((Math.pow(apple.getX() - snake.getX(), 2)) + (Math.pow(apple.getY() - snake.getY(), 2)))) {
				snake.addBody();
				apple = null;
				
			}
			StdDraw.show(30);
		}
	
	}
}
