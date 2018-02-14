package pathfinder.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import pathfinder.core.pathfinding.grid.Grid;
import pathfinder.core.pathfinding.grid.Node;

public class Renderer extends Canvas {

	private static final long serialVersionUID = 1L;
	

	public static boolean pathFound = false;
	
	public static ArrayList<Node> path = new ArrayList<Node>();
	
	boolean gridRendered = false;
	
	Grid grid;

	public Renderer() {

		/**TODO: Get width and height from the main class*/
		super.setSize(1500, 1500 / 16 * 9);
		
		grid = Main.grid;

	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(2);
			return;

		}

		Graphics g = bs.getDrawGraphics();
		
		if(pathFound) {
			
			gridRendered = false;
			
		}
			

		if (!gridRendered) {

			for (int x = 0; x < Main.grid.gridSizeX; x++) {

				for (int y = 0; y < Main.grid.gridSizeY; y++) {
					
					if(grid.nodeGrid[x][y] == grid.nodeFromVector(Main.start)) {
						
						g.setColor(Color.RED);
						
					} else if(grid.nodeGrid[x][y] == grid.nodeFromVector(Main.target)) {
						
						g.setColor(new Color(0, 150, 0));
						
					} else if(path.contains(grid.nodeGrid[x][y])) {

						g.setColor(Color.BLUE);
						
					} else if(!grid.nodeGrid[x][y].walkable) {
						
						g.setColor(Color.GRAY);
						
					} else {
						
						g.setColor(Color.BLACK);
						
					}

					g.fillRect(Main.grid.nodeGrid[x][y].gridX * 20, Main.grid.nodeGrid[x][y].gridY * 20, 10, 10);

				}
			}
			
			gridRendered = true;
			pathFound = false;
			
		}

		g.dispose();

		bs.show();

	}

}
