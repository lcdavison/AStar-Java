package pathfinder.core.pathfinding.grid;

import java.util.ArrayList;
import java.util.Random;

import pathfinder.core.vector.Vector2;

public class Grid {

	public float nodeRadius = 0.5f;
	public float nodeDiameter;
	public int screenX, screenY;
	public int gridSizeX, gridSizeY;

	public Node[][] nodeGrid;

	public Grid(int xSize, int ySize) {

		screenX = xSize;
		screenY = ySize;

		nodeDiameter = nodeRadius * 2;

		gridSizeX = Math.round(screenX / nodeDiameter);
		gridSizeY = Math.round(screenY / nodeDiameter);

		createGrid();

	}

	public void createGrid() {

		nodeGrid = new Node[gridSizeX][gridSizeY];

		for (int x = 0; x < gridSizeX; x++) {

			for (int y = 0; y < gridSizeY; y++) {

				Vector2 position = new Vector2(x, y);
				
				Random random = new Random();
				
				boolean walkable;
				
				if(random.nextInt(100) > 15)
					walkable = true;
				else
					walkable = false;

				nodeGrid[x][y] = new Node(position, walkable, x, y);

			}
		}
	}

	public Node nodeFromVector(Vector2 position) {

		Node node = nodeGrid[(int) position.getX()][(int) position.getY()];

		return node;

	}

	public ArrayList<Node> getNeighbours(Node node) {
		
		ArrayList<Node> neighbours = new ArrayList<Node>();
		
		for(int x = -1; x <= 1; x++) {
			
			for(int y = -1; y <= 1; y++) {
				
				if(x == 0 && y == 0)
					continue;
				
				int checkX = node.gridX + x;
				int checkY = node.gridY + y;
				
				if(checkX >= 0 && checkX < gridSizeX && checkY >= 0 && checkY < gridSizeY) {
					
					neighbours.add(nodeGrid[checkX][checkY]);
					
				}
			}
		}

		return neighbours;

	}
}
