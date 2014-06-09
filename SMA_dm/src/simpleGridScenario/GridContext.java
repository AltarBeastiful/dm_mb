package simpleGridScenario;

import java.awt.Color;

import simpleGridScenario.GridEnvironnement.TileStatus;

public interface GridContext {
	public TileStatus getStatus(int x, int y) throws Exception;
	public Color getStatusColor(int x, int y) throws Exception;
}
