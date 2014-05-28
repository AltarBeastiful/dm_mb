package framework.impl;

import framework.Callable;
import framework.DecisionMaking;
import framework.Perceive;

public abstract class  AbstractPerceive extends Perceive implements Callable{
	private String uid;
	
	public AbstractPerceive(String agentUid) {
		this.uid = agentUid;
	}

	public abstract void perceive();
	@Override
	protected Callable make_perception() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void launch() {
		this.requires().tick().getTick(uid);
		this.perceive();
		this.requires().decision().launch();
	}
	
}
