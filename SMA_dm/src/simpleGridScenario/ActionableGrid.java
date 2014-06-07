package simpleGridScenario;

import simpleGridScenario.GridEnvironnement.TileStatus;

public interface ActionableGrid {
	public boolean moveAgent(int x, int y, int newX, int newY) throws Exception;
	
	public boolean setStatus(int x, int y, TileStatus s) throws Exception;
}
