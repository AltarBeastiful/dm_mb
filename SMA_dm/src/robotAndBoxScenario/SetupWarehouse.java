package robotAndBoxScenario;

import framework.SetupScenario;

public interface SetupWarehouse extends SetupScenario {
	boolean addTunnel(int y);
	boolean removeTunnel(int y);
	
	boolean addBox(int x, int y) throws Exception;

}
