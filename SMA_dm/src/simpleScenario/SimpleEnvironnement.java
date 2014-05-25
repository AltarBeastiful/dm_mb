package simpleScenario;

import framework.Agent;
import framework.Environnement;
import framework.IActionable;
import framework.IContext;
import framework.IContext.TileStatus;

public class SimpleEnvironnement extends Environnement {

	@Override
	protected IContext make_context() {
		return new SimpleContext() {
			
			@Override
			public TileStatus queryPosition(int x, int y) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	protected IActionable make_actionable() {
		return new SimpleActionable() {
			
			@Override
			public boolean pickupBox(Agent a, int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean move(int x, int y, int newX, int newY) throws Exception {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
