package simpleGridScenario;

import simpleGridScenario.GridEnvironnement.TileStatus;

public interface GridContext {
	public TileStatus getStatus(int x, int y) throws Exception;//dynamic cat in simple agent?
}
