package robotAndBoxScenario;

import robotAndBoxScenario.WarehouseEnvironnement.TileStatus;


public interface ActionableWharehouse {
	public boolean moveAgent(int x, int y, int newX, int newY) throws Exception;
	
	public boolean setStatus(int x, int y, TileStatus s) throws Exception;
	
	public boolean addTunnel(int y);
	public boolean removeTunnel(int y);

}
