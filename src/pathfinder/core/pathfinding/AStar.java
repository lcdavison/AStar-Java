package pathfinder.core.pathfinding;

import java.util.ArrayList;
import java.util.Collections;

import pathfinder.core.Main;
import pathfinder.core.Renderer;
import pathfinder.core.pathfinding.grid.Grid;
import pathfinder.core.pathfinding.grid.Node;
import pathfinder.core.vector.Vector2;

public class AStar {
	
	Grid grid;
	
	public AStar() {
		
		grid = Main.grid;
		
	}
	
	public void findPath(Vector2 start, Vector2 target) {
		
		Node startNode = grid.nodeFromVector(start);
		startNode.gCost = 0;
		startNode.hCost = getDistance(start, target);
		
		Node targetNode = grid.nodeFromVector(target);
		
		ArrayList<Node> openSet = new ArrayList<Node>();
		ArrayList<Node> closedSet = new ArrayList<Node>();
		
		openSet.add(startNode);
		
		while(openSet.size() > 0) {
			
			Node currentNode = openSet.get(0);
			
			for(int i = 0; i < openSet.size(); i++) {
				
				if(openSet.get(i).fCost() < currentNode.fCost()  || openSet.get(i).fCost() == currentNode.fCost() && openSet.get(i).hCost < currentNode.hCost) {
					
					currentNode = openSet.get(i);
					
				}
			}
			
			openSet.remove(openSet.indexOf(currentNode));
			closedSet.add(currentNode);
			
			if(currentNode == targetNode) {
				
				System.out.println("Path Found");
				
				Renderer.pathFound = true;
				
				for(Node node : retracePath(startNode, targetNode)) {
					
					Renderer.path.add(node);
					
				}
				
				return;
			}	
			
			for(Node neighbour : grid.getNeighbours(currentNode)) {
				
				if(!neighbour.walkable || closedSet.contains(neighbour))
					continue;
				
				int newMoveCostToNeighbour = currentNode.gCost + getDistance(currentNode.position, neighbour.position);
				
				if(newMoveCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
					
					neighbour.gCost = newMoveCostToNeighbour;
					neighbour.hCost = getDistance(neighbour.position, targetNode.position);
					neighbour.parent = currentNode;
					
					if(!openSet.contains(neighbour))
						openSet.add(neighbour);
					
				}
			}
		}
	}
	
	public int getDistance(Vector2 start, Vector2 target) {
		
		int distanceX = (int) Math.abs(start.getX() - target.getX());
		int distanceY = (int) Math.abs(start.getY() - target.getY());
		
		if(distanceX > distanceY)
			return 14 * distanceY + 10 * (distanceX - distanceY);
		
		return 14 * distanceX + 10 * (distanceY - distanceX);

	}
	
	public ArrayList<Node> retracePath(Node start, Node target) {
		
		ArrayList<Node> path = new ArrayList<Node>();
		
		Node currentNode = target;
		
		while(currentNode != start) {
			
			path.add(currentNode);
			currentNode = currentNode.parent;
			
		}
		
		Collections.reverse(path);
		
		return path;
		
	}
}
