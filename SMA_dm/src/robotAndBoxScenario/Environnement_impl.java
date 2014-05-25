package robotAndBoxScenario;

import java.awt.Point;
import java.util.LinkedHashMap;

import simpleScenario.ContextExtern_impl;
import framework.Agent;
import framework.Environnement;
import framework.IActionable;
import framework.IContext;

public class Environnement_impl extends Environnement implements ContextExtern_impl, IActionable {

	//private int width = 100, height = 100;
	private LinkedHashMap<Point, ContextExtern_impl.TileStatus> grid;
	
	public Environnement_impl() {
		this.grid = new LinkedHashMap<Point, ContextExtern_impl.TileStatus>();
	}
	
	@Override
	protected IContext make_context() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	protected IActionable make_actionable() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean move(int x, int y, int newX, int newY) throws Exception {
		// TODO Auto-generated method stub
		Point p = new Point(newX, newY);
		TileStatus status = grid.get(p);
		
		if (status == null) {
			throw new Exception("Undefined cooridnates out of bondaries, shouldn't be in this state") ;
		}
		
		TileStatus originStatus = grid.get(new Point(x,y));
		boolean isRobot = (originStatus.equals(TileStatus.ROBOT));
		boolean isRobotCary = originStatus.equals(TileStatus.ROBOT_CARRY);
		if (isRobot == false) {
			throw new Exception("There is not robot to move on the given coordinates, you liar !") ;
		}
				
		if(isRobot && isRobotCary && status.equals(TileStatus.BOX))
			return false;
		
		if (status.equals(TileStatus.OBSTACLE) || status.equals(TileStatus.ROBOT) || status.equals(TileStatus.ROBOT_CARRY)) {
			return false;
		}
		// actually move the robot from env pov
		if (status.equals(TileStatus.BOX)) 
			grid.put(p, TileStatus.ROBOT_CARRY);
		else // free tile !
			grid.put(p, TileStatus.ROBOT);
		return true;	
	}

	@Override
	public boolean pickupBox(Agent a, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TileStatus queryPosition(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
