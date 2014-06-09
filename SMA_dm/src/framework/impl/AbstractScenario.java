package framework.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import framework.ActListener;
import framework.ActObservable;
import framework.Agent;
import framework.AgentJoining;
import framework.Clock;
import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.SetupScenario;

public abstract  class AbstractScenario<Context, Actionable> extends Scenario<Context, Actionable> implements SetupScenario, ActObservable {
	private List<ActListener> listeners;
	protected List<Scenario.AgentSpecies.Component<Context, Actionable>> agents;
	private int defaultSpeed;
	
	public AbstractScenario(int defaultSpeed) {
		agents = new ArrayList<Scenario.AgentSpecies.Component<Context, Actionable>>();
		listeners = new ArrayList<ActListener>();
		
		this.defaultSpeed = defaultSpeed;
	}
	
	@Override
	protected void start() {
		super.start();
		
		this.provides().observeAllAgents().addActListener(this.parts().gui().actListener());
	}

	@Override
	protected Clock make_clock() {
		return new Clock_impl(defaultSpeed);
	}

	@Override
	protected abstract Environnement<Context, Actionable> make_env();
	
	protected abstract Agent<Context, Actionable> make_agent(String id);

	@Override
	protected AgentSpecies<Context, Actionable> make_AgentSpecies(String id) {
		final String uid = id;
		return new AgentSpecies<Context, Actionable>() {

			@Override
			protected Agent<Context, Actionable> make_agent() {
				return AbstractScenario.this.make_agent(uid);
			}
			
		};
	}

	@Override
	protected AgentJoining<Context, Actionable> make_rjc() {
		return new AgentJoining_impl<Context, Actionable>();
	}

	@Override
	protected SetupScenario make_setup() {
		return this;
	}

	@Override
	public Scenario.AgentSpecies.Component<Context, Actionable> addAgent(Object...parameters) {
		Scenario.AgentSpecies.Component<Context, Actionable> a = newAgentSpecies(randomUUID());
		updateListeners(a);
		agents.add(a);
		
		return a;
	}
	
	@Override
	public String randomUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	protected ActObservable make_observeAllAgents() {
		return this;
	}
	
	@Override
	public void removeActListener(ActListener ac) {
		listeners.remove(ac);
		
		for (Scenario.AgentSpecies.Component<Context, Actionable> species : agents ) {
			species.actObservable().removeActListener(ac);;
		}
	}
	
	@Override
	public void fireAct() {
		for (Scenario.AgentSpecies.Component<Context, Actionable> species : agents ) {
			species.actObservable().fireAct();
		}
	}
	
	@Override
	public void addActListener(ActListener ac) {
		if (!listeners.contains(ac))
			listeners.add(ac);
		
		for (Scenario.AgentSpecies.Component<Context, Actionable> species : agents ) {
			species.actObservable().addActListener(ac);
		}
	}
	
	protected void updateListeners(Scenario.AgentSpecies.Component<Context, Actionable> a) {
		for (ActListener ac : listeners) {
			a.actObservable().addActListener(ac);
		}
	}

	@Override
	protected abstract Gui make_gui();

}
