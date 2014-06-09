package robotAndBoxScenario;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

import simpleGridScenario.OutOfBondsException;
import framework.Environnement;

public class WarehouseEnvironnement extends Environnement<WharehouseContext, ActionableWharehouse> implements WharehouseContext, ActionableWharehouse {
	private int width;
	private int height;
	private Map<Point, TileStatus> grid;
	
	private int startMountain;
	private int endMountain;
	
	public enum TileStatus { ROBOT, ROBOT_CARRYING, OBSTACLE, FREE, BOX };
	
	public WarehouseEnvironnement(int width, int height) {
		this.height = height;
		this.width = width;
		
		this.grid = new LinkedHashMap<Point, TileStatus>();
		
		startMountain = (int) Math.round(width*0.2);
		endMountain = (int) Math.round(width*0.8);
		
		// TODO for each tile is here
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.grid.put(new Point(i, j), TileStatus.FREE);
			}
		}
		
		for (int y = 0; y < height; y++) {
			removeTunnel(y);
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	protected WharehouseContext make_context() {
		return this;
	}

	@Override
	protected ActionableWharehouse make_actionable() {
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
		
		boolean isOriginRobot = (originStatus.equals(TileStatus.ROBOT));
		boolean isOriginRobotCary = originStatus.equals(TileStatus.ROBOT_CARRYING);
				
		if (!isOriginRobot) {
			throw new Exception("There is not robot to move on the given coordinates, you liar !") ;
		}
		
		if (arrivalStatus.equals(TileStatus.OBSTACLE) || arrivalStatus.equals(TileStatus.ROBOT) || arrivalStatus.equals(TileStatus.ROBOT_CARRYING)) {
			return false;
		}
		
		if(isOriginRobot && isOriginRobotCary && arrivalStatus.equals(TileStatus.BOX))
			return false;
		
		// actually move the robot from env pov
		if (arrivalStatus.equals(TileStatus.BOX)) 
			grid.put(arrivalPoint, TileStatus.ROBOT_CARRYING);
		else // free tile !
			grid.put(arrivalPoint, TileStatus.ROBOT);
		
		grid.put(startPoint, TileStatus.FREE);
		
		return true;
	}

	@Override
	public boolean setStatus(int x, int y, TileStatus s) throws Exception {
		Point p = new Point(x, y);
		
		if (!isInGrid(p)) {
			//throw new Exception("Undefined cooridnates out of bondaries, shouldn't be in this state");
			return false;
		}
		
		grid.put(p, s);
		
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

	public Color getStatusColor(int x, int y) throws Exception {
		switch (getStatus(x, y)) {
		case FREE:
			return Color.WHITE;
		case ROBOT:
			return Color.BLUE;
		case ROBOT_CARRYING:
			return Color.RED;
		case BOX:
			return Color.YELLOW;
		case OBSTACLE:
			return Color.black;
		default:
			return Color.gray;
		}
	}
	
	private boolean isInGrid(Point p) {
		return (p.x < width && p.y < height && grid.get(p) != null);
	}

	@Override
	public boolean addTunnel(int y) {
		for(int x = startMountain; x <= endMountain; x++) {
			try {
				setStatus(x, y, TileStatus.FREE);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean removeTunnel(int y) {
		for(int x = startMountain; x <= endMountain; x++) {
				try {
					setStatus(x, y, TileStatus.OBSTACLE);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
//			}
		}
		
		return true;
	}

}

