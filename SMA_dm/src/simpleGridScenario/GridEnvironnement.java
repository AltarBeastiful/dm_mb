package simpleGridScenario;

import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

import framework.Environnement;

//TODO : handle synchronization 
public class GridEnvironnement extends Environnement<GridContext, ActionableGrid> implements GridContext, ActionableGrid {
	private int width;
	private int height;
	private Map<Point, TileStatus> grid;
	
	public enum TileStatus { AGENT, OBSTACLE, FREE };
	
	public GridEnvironnement(int width, int height) {
		this.height = height;
		this.width = width;
		
		this.grid = new LinkedHashMap<Point, TileStatus>();
		
		for (int i = 0; i <= width; i++) {
			for (int j = 0; j <= height; j++) {
				this.grid.put(new Point(i, j), TileStatus.FREE);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	protected GridContext make_context() {
		return this;
	}

	@Override
	protected ActionableGrid make_actionable() {
		return this;
	}

	@Override
	public boolean moveAgent(int x, int y, int newX, int newY) throws Exception, OutOfBondsException {
		
		Point startPoint = new Point(x, y);
		Point arrivalPoint = new Point(newX, newY);
		TileStatus arrivalStatus = grid.get(arrivalPoint);
		TileStatus originStatus = grid.get(new Point(x,y));
		
		if (arrivalStatus == null) {
			throw new OutOfBondsException("Undefined cooridnates out of bondaries, shouldn't be in this state") ;
		}
		
		if (!originStatus.equals(TileStatus.AGENT)) {
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
	public boolean setStatus(int x, int y, TileStatus s) {
		Point p = new Point(x, y);
		
		if (!isInGrid(p)) {
			//throw new Exception("Undefined cooridnates out of bondaries, shouldn't be in this state");
			return false;
		}
		
		grid.put(p, s);
		
		return true;
	}
	
	private boolean isInGrid(Point p) {
		//TODO : check also using width and height
		return (p.x <= width && p.y <= height && grid.get(p) != null);
	}

	public class OutOfBondsException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2731382167002030627L;
		
		public OutOfBondsException(String mess){
			super(mess);
		}
	}
}
