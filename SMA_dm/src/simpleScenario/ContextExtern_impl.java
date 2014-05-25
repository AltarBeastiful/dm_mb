package simpleScenario;

import framework.IContext;

public interface ContextExtern_impl extends IContext {
	
	// check if this is ok ? 
	final int maximum_sight = 3;
	public enum TileStatus { ROBOT, ROBOT_CARRY, BOX, OBSTACLE, FREE };
	public TileStatus queryPosition(int x, int y);

}
