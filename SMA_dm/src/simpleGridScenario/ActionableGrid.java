package simpleGridScenario;

import simpleGridScenario.GridEnvironnement.TileStatus;
import framework.IActionable;

public interface ActionableGrid extends IActionable {
	public boolean moveAgent(int x, int y, int newX, int newY) throws Exception;
	
	public boolean setStatus(int x, int y, TileStatus s) throws Exception;
}
