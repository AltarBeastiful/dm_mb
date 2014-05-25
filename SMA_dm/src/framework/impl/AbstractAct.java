package framework.impl;

import framework.Act;
import framework.DecisionMaking;
import framework.IActionable;


public abstract class AbstractAct extends Act  implements IActionable, DecisionMaking{

	public abstract void action();
	@Override
	protected IActionable make_action() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void launch() {
		// get the decision choice HOW ? 
		action();
		this.requires().perception().launch();
	}
	
}
