package framework.impl;

import java.util.ArrayList;
import java.util.List;

import framework.Act;
import framework.ActListener;
import framework.ActObservable;
import framework.IActionable;


public abstract class AbstractAct extends Act  implements IActionable, ActObservable{
	private List<ActListener> listeners;
	
	public AbstractAct(){
		listeners = new ArrayList<ActListener>();
	}
	
	@Override
	protected IActionable make_action() {
		return this;
	}

	@Override
	public void removeActListener(ActListener ac) {
		listeners.remove(ac);
	}
	
	@Override
	public void fireAct() {
		for (ActListener ac : listeners) {
			ac.actFired();
		}
	}
	
	@Override
	public void addActListener(ActListener ac) {
		listeners.add(ac);
	}

	@Override
	protected ActObservable make_observable() {
		return this;
	}

	
}
