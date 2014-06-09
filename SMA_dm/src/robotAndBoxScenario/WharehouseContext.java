package robotAndBoxScenario;

import java.awt.Color;

import robotAndBoxScenario.WarehouseEnvironnement.TileStatus;

public interface WharehouseContext {
	public TileStatus getStatus(int x, int y) throws Exception;
	public Color getStatusColor(int x, int y) throws Exception;
}
