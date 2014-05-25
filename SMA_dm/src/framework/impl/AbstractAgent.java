package framework.impl;

import framework.Act;
import framework.ActObservable;
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
	
	public void startCycle() {
		// creation of a thread 
		// launch perception in loop 
		// alreayd connected
	}
	
	@Override
	protected void start() {
		super.start();
		//this.requires().tick().getTick(uid);
		// thread
		// launch startCycle !
	}

	@Override
	protected IWorkLoad make_charge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Perceive make_perception() {
		return null;
	}

	@Override
	protected Decide make_decision() {
		return null;
	}

	@Override
	protected Knowledge make_knowledge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Act make_action() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActObservable make_actObservable() {
		// TODO Auto-generated method stub
		return null;
	}

}
