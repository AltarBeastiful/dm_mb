package framework.impl;

import framework.Act;
import framework.Agent;
import framework.Decide;
import framework.IMemory;
import framework.IWorkLoad;
import framework.Knowledge;
import framework.Perceive;

public abstract class AbstractAgent extends Agent{
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
	protected Perceive make_perception() {
		return new AbstractPerceive(uid) {
			
			@Override
			public void perceive() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	protected Decide make_decision() {
		return new AbstractDecide() {
			
			@Override
			public void decide() {
				// TODO Auto-generated method stub
				
			}
		};
	}

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
	protected Act make_action() {
		return new AbstractAct() {
		};
	}
	
	public String getUid() {
		return this.uid;
	}
}
