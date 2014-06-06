package simpleScenario;

import framework.Environnement;

public class SimpleEnvironnement extends Environnement<SimpleContext, SimpleActionable> implements SimpleContext, SimpleActionable {
	boolean isSimple;
	
	public SimpleEnvironnement() {
		isSimple = true;
	}

	@Override
	protected SimpleContext make_context() {
		return this;
	}

	@Override
	protected SimpleActionable make_actionable() {
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
