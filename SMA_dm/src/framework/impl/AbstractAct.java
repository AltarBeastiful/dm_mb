package framework.impl;

import java.util.ArrayList;
import java.util.List;

import framework.Act;
import framework.ActListener;
import framework.ActObservable;


public abstract class AbstractAct<Actionable> extends Act<Actionable>  implements ActObservable{
	private List<ActListener> listeners;
	
	public AbstractAct(){
		listeners = new ArrayList<ActListener>();
	}
	
	@Override
	protected abstract Actionable make_action();

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
