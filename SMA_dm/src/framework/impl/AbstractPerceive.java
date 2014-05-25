package framework.impl;

import framework.Callable;
import framework.DecisionMaking;
import framework.Perceive;

public abstract class  AbstractPerceive extends Perceive implements Callable{

	public abstract void perceive();
	@Override
	protected Callable make_perception() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void launch() {
		this.perceive();
		this.requires().decision().launch();
	}
	
}
