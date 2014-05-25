package simpleGridScenario;

import simpleGridScenario.GridEnvironnement.TileStatus;
import framework.IContext;

public interface GridContext extends IContext {
	public TileStatus getStatus(int x, int y) throws Exception;
}
