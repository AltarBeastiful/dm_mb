package framework;

public interface IContext {
	
	public enum TileStatus { ROBOT, ROBOT_CARRY, BOX, OBSTACLE, FREE };
	
	public TileStatus queryPosition(int x, int y);

}

