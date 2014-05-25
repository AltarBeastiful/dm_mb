package simpleScenario;

import framework.Environnement;
import framework.IActionable;
import framework.IContext;

public class SimpleEnvironnement extends Environnement {

	@Override
	protected IContext make_context() {
		return new SimpleContext() {
		};
	}

	@Override
	protected IActionable make_actionable() {
		return new SimpleActionable() {

		};
	}

}
