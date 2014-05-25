package simpleGridScenario;

import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

import framework.Agent;
import framework.Environnement;
import framework.IActionable;
import framework.IContext;

public class GridEnvironnement extends Environnement implements GridContext, ActionableGrid {
	private int width;
	private int height;
	private Map<Point, TileStatus> grid;
	
	public enum TileStatus { AGENT, OBSTACLE, FREE };
	
	public GridEnvironnement(int width, int height) {
		this.height = height;
		this.width = width;
		
		this.grid = new LinkedHashMap<Point, TileStatus>();
		
		//add Free on all tiles
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	protected IContext make_context() {
		return this;
	}

	@Override
	protected IActionable make_actionable() {
		return this;
	}

	@Override
	public boolean moveAgent(int x, int y, int newX, int newY) throws Exception {
		
		Point startPoint = new Point(x, y);
		Point arrivalPoint = new Point(newX, newY);
		TileStatus arrivalStatus = grid.get(arrivalPoint);
		TileStatus originStatus = grid.get(new Point(x,y));
		
		if (arrivalStatus == null) {
			throw new Exception("Undefined cooridnates out of bondaries, shouldn't be in this state") ;
		}
		
		if (originStatus.equals(TileStatus.AGENT)) {
			throw new Exception("There is not robot to move on the given coordinates, you liar !") ;
		}
		
		if (arrivalStatus.equals(TileStatus.OBSTACLE) || arrivalStatus.equals(TileStatus.AGENT)) {
			return false;
		}
		
		// actually move the agent from env pov
		grid.put(startPoint, TileStatus.FREE);
		grid.put(arrivalPoint, TileStatus.AGENT);
		
		return true;	
	}

	@Override
	public TileStatus getStatus(int x, int y) throws Exception {
		Point p = new Point(x, y);
		
		if (!isInGrid(p)) {
			throw new Exception("Undefined cooridnates out of bondaries, shouldn't be in this state");
		}
		
		return (grid.get(p));
	}

	@Override
	public boolean setStatus(int x, int y, TileStatus s) throws Exception {
		Point p = new Point(x, y);
		
		if (!isInGrid(p)) {
			throw new Exception("Undefined cooridnates out of bondaries, shouldn't be in this state");
		}
		
		return grid.put(p, s).equals(s);
	}
	
	private boolean isInGrid(Point p) {
		//TODO : check also using 
		return (grid.get(p) != null);
	}

}
