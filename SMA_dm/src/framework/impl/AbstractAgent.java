package framework.impl;

import framework.Act;
import framework.Agent;
import framework.Decide;
import framework.IMemory;
import framework.Knowledge;
import framework.Logger;
import framework.Perceive;
import framework.SetupAgent;
import framework.Suicide;

public abstract class AbstractAgent<Context, Actionable> extends Agent<Context, Actionable> implements Suicide{
	private String uid;
	private volatile Thread t;
	
	public AbstractAgent(String uid) {
		this.uid = uid;
		t = null;
	}
	
	@Override
	protected void start() {
		super.start();
		t = new Thread(this.parts().perception().perception());
		t.start();
	}

	@Override
	protected abstract Perceive<Context> make_perception();

	@Override
	protected abstract Decide<Actionable> make_decision(); 

	@Override
	protected abstract Act<Actionable> make_action();
	
	@Override
	protected Knowledge make_knowledge() {
		return new Knowledge() {
			
			@Override
			protected IMemory make_memory() {
				return new IMemory() {
				}; 
			}
		};
	}

	@Override
	protected Suicide make_life() {
		return this;
	}

	@Override
	protected abstract SetupAgent make_setup();

	public String getUid() {
		return this.uid;
	}

	@Override
	protected Logger make_logger() {
		return new LoggerImpl();
	}

	@Override
	public void suicide() {
		t.interrupt();
		t = null;
	}
	
}
