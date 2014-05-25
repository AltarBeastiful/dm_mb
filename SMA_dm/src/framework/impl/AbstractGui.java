package framework.impl;

import framework.ActListener;
import framework.Gui;

public abstract class AbstractGui extends Gui implements ActListener {

	protected void start() {
		this.requires().actObservable().addActListener(this);
	};
	
	@Override
	protected ActListener make_actListener() {
		return this;
	}

}
