package assignment8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.ArgsProcessor;
import support.cse131.NotYetImplementedException;
import support.cse131.Timing;
import zombies.ZombieSimulationFiles;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class ZombieSimulator {
	private static final String ZOMBIE_TOKEN_VALUE = "Zombie";
	private List<Entity> entities; 

	

	/**
	 * Constructs a ZombieSimulator with an empty list of Entities.
	 */
	public ZombieSimulator() {
		this.entities = new LinkedList<>();
	}

	/**
	 * @return the current list of active entities (that is: those which have not
	 *         been consumed).
	 */
	public List<Entity> getEntities() {
		return entities; 
	}

	/**
	 * Reads an entire zombie simulation file from a specified ArgsProcessor, adding
	 * each Entity to the list of entities.
	 * 
	 * @param ap ArgsProcessor to read the complete zombie simulation file format.
	 */
	public void readEntities(ArgsProcessor ap) {
		// from assignment 4
		int N = ap.nextInt();
		for (int i = 0; i < N; i ++) {
			if (ap.nextString().equals("Nonzombie")) {
				entities.add(new Entity(false, ap.nextDouble(), ap.nextDouble()));
			} else {
				entities.add(new Entity(true, ap.nextDouble(), ap.nextDouble()));
			}
		}
	}

	/**
	 * @return the number of zombies in entities.
	 */
	public int getZombieCount() {
		int zombieCount = 0;
		for (Entity entity : entities) {
			if (entity.isZombie() == true) {
				zombieCount ++; 
			}
		}
		return zombieCount;
	}

	/**
	 * @return the number of nonzombies in entities.
	 */
	public int getNonzombieCount() {
		int nonzombieCount = 0;
		for (Entity entity : entities) {
			if (entity.isZombie() == false) {
				nonzombieCount ++; 
			}
		}
		return nonzombieCount;
	}

	/**
	 * Draws a frame of the simulation.
	 */
	public void draw() {
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();

		for (Entity entity : getEntities()) {
			entity.draw();
		}
		String ratio = getNonzombieCount() + " / " + (getNonzombieCount() + getZombieCount());
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.text (.1, .9, ratio);
		
		StdDraw.show(); 
	}

	/**
	 * Updates the entities for the current frame of the simulation given the amount
	 * of time passed since the previous frame.
	 * 
	 * Note: some entities may be consumed and will not remain for future frames of
	 * the simulation.
	 * 
	 * @param deltaTime the amount of time since the previous frame of the
	 *                  simulation.
	 */
	public void update(double deltaTime) {
		List<Entity> newEntities = new LinkedList<>();
		for (Entity entity : this.entities) {
			if (entity.update(entities, deltaTime) == true) {
				newEntities.add(entity);
			}
		}
		entities = newEntities; 
	}

	/**
	 * Runs the zombie simulation.
	 * 
	 * @param args arguments from the command line
	 */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering(); // reduce unpleasant drawing artifacts, speed things up

		ArgsProcessor ap = ZombieSimulationFiles.createArgsProcessorFromFile(args);
		ZombieSimulator zombieSimulator = new ZombieSimulator();
		zombieSimulator.readEntities(ap);
		zombieSimulator.draw();
		StdDraw.pause(500);

		double prevTime = Timing.getCurrentTimeInSeconds();
		while (zombieSimulator.getNonzombieCount() > 0) {
			double currTime = Timing.getCurrentTimeInSeconds();
			double deltaTime = currTime - prevTime;
			if (deltaTime > 0.0) {
				zombieSimulator.update(deltaTime);
				zombieSimulator.draw();
			}
			StdDraw.pause(10);
			prevTime = currTime;
		}
	}
}
