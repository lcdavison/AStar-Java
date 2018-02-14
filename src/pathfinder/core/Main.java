package pathfinder.core;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import pathfinder.core.pathfinding.AStar;
import pathfinder.core.pathfinding.grid.Grid;
import pathfinder.core.time.Time;
import pathfinder.core.vector.Vector2;

public class Main extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = 1500, HEIGHT = WIDTH / 16 * 9;
	private final int TARGET_UPS = 30;

	private Thread thread;
	private Renderer renderer;
	private Time time;
	private boolean running = false;

	public static Grid grid;
	public static Vector2 start, target;
	
	public Main() {

		grid = new Grid(30, 30);
		renderer = new Renderer();
		time = new Time();

		super.setTitle("Pathfinder");
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setLayout(new BorderLayout());
		super.add(renderer, BorderLayout.NORTH);
		super.pack();
		super.setVisible(true);

	}

	synchronized void start() {

		thread = new Thread(this, "PATHFINDER");

		if (running)
			return;

		running = true;
		thread.start();

	}

	synchronized void stop() {

		if (!running)
			return;

		try {

			running = false;
			thread.join();

		} catch (InterruptedException e) {

			e.printStackTrace();

		}
	}

	public void run() {

		float deltaTime;
		float interval = 1.0f / TARGET_UPS;
		float accumulator = 0.0f;

		while (running) {
			
			deltaTime = time.deltaTime();
			
			accumulator += deltaTime;
			
			while(accumulator >= interval) {
				
				accumulator -= interval;
				
			}

			renderer.render();

		}
	}

	public static void main(String[] args) {

		Main coreProgram = new Main();
		AStar astar = new AStar();

		coreProgram.start();
		
		start = new Vector2(0, 0);
		target = new Vector2(28, 28);
		
		astar.findPath(start, target);

	}

}
