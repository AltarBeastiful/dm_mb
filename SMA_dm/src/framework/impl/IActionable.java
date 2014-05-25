package framework.impl;

import framework.Agent;

public interface IActionable {
	public boolean move(int x, int y, int newX, int newY) throws Exception;
	public boolean pickupBox(Agent a, int x, int y);

}
