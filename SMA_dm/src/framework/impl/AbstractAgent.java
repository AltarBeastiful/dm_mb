package framework.impl;

import framework.Act;
import framework.Agent;
import framework.Decide;
import framework.IWorkLoad;
import framework.Knowledge;
import framework.Perceive;

public class AbstractAgent extends Agent{
	private String uid;
	
	public AbstractAgent(String uid) {
		this.uid = uid;
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();
		
		//this.requires().tick().getTick(uid);
	}

	@Override
	protected IWorkLoad make_charge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Perceive make_perception() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Decide make_decision() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Act make_act() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Knowledge make_knowledge() {
		// TODO Auto-generated method stub
		return null;
	}

}
