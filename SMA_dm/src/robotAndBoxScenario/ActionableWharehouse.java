package robotAndBoxScenario;

import robotAndBoxScenario.WarehouseEnvironnement.TileStatus;
import simpleGridScenario.OutOfBondsException;


public interface ActionableWharehouse {
	public boolean moveAgent(int x, int y, int newX, int newY) throws OutOfBondsException, Exception;
	
	public boolean setStatus(int x, int y, TileStatus s) throws Exception;
	
	public boolean addTunnel(int y);
	public boolean removeTunnel(int y);

}
