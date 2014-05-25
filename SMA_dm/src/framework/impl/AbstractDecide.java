package framework.impl;

import framework.Callable;
import framework.Decide;
import framework.DecisionMaking;
import framework.Perceive;

public abstract class  AbstractDecide extends Decide implements Callable{

	public abstract void decide();
	@Override
	protected Callable make_decision() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void launch() {
		this.decide(); // get something that lead to an action, code by user
	}
}
