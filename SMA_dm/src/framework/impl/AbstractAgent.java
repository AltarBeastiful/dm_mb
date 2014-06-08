package framework.impl;

import framework.Act;
import framework.Agent;
import framework.Decide;
import framework.IMemory;
import framework.IWorkLoad;
import framework.Knowledge;
import framework.Perceive;
import framework.SetupAgent;

public abstract class AbstractAgent<Context, Actionable> extends Agent<Context, Actionable>{
	private String uid;
	private Thread t;
	
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
	protected IWorkLoad make_charge() {
		// TODO Auto-generated method stub
		return null;
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
	protected abstract SetupAgent make_setup();

	public String getUid() {
		return this.uid;
	}
}
