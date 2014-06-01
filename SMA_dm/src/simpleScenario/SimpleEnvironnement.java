package simpleScenario;

import framework.Environnement;
import framework.IActionable;
import framework.IContext;

public class SimpleEnvironnement extends Environnement implements SimpleContext, SimpleActionable {
	boolean isSimple;
	
	public SimpleEnvironnement() {
		isSimple = true;
	}

	@Override
	protected IContext make_context() {
		return this;
	}

	@Override
	protected IActionable make_actionable() {
		return this;
	}

	@Override
	public String getStatus() {
		if(isSimple){
			return "The environnement is simple!";
		}else{
			return "The environnement is complex!";
		}
	}

	@Override
	public void toggleIsSimple() {
		isSimple = !isSimple;
	}
}
