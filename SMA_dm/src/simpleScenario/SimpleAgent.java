package simpleScenario;

import framework.Act;
import framework.Decide;
import framework.IMemory;
import framework.IWorkLoad;
import framework.Knowledge;
import framework.Perceive;
import framework.impl.Agent_impl;

public class SimpleAgent extends Agent_impl {

	public SimpleAgent(String uid) {
		super(uid);
	}
	
	@Override
	protected Perceive make_perception() {
		return new Perceive() {
			//Percieve nothing
		};
	}
	
	@Override
	protected Decide make_decision() {
		return new Decide() {
			//Decide nothing
		};
	}
	
	@Override
	protected Act make_act() {
		return new Act() {
			//Do nothing
		};
	}
	
	@Override
	protected Knowledge make_knowledge() {
		// TODO Auto-generated method stub
		return new Knowledge() {
			
			@Override
			protected IMemory make_memory() {
				// TODO Auto-generated method stub
				return new IMemory() {
					
					@Override
					public boolean isKnown(int x, int y) {
						// TODO Auto-generated method stub
						return false;
					}
				};
			}
		};
	}
	
	@Override
	protected IWorkLoad make_charge() {
		// TODO Auto-generated method stub
		return new IWorkLoad() {
		};
	}
	
}
