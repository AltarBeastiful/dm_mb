package framework.impl;

import framework.Callable;
import framework.Decide;

public abstract class  AbstractDecide extends Decide implements Callable{

	public abstract void decide();
	
	@Override
	protected Callable make_decision() {
		return this;
	}

	@Override
	public void run() {
		this.decide(); // get something that lead to an action, code by user
		this.requires().perception().run();
	}
}
