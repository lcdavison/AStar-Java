package pathfinder.core.pathfinding.grid;

import pathfinder.core.vector.Vector2;

public class Node {
	
	public boolean walkable;
	public Node parent;
	public int gCost, hCost;
	public int gridX, gridY;
	
	public Vector2 position;
	
	public Node(Vector2 position, boolean walkable, int gridX, int gridY) {
		
		this.position = position;
		this.walkable = walkable;
		this.gridX = gridX;
		this.gridY = gridY;
		
	}
	
	public int fCost() {
		
		return gCost + hCost;
		
	}

}
