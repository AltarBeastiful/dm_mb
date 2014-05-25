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

public abstract  class AbstractScenario extends Scenario implements SetupScenario {
	private List<Scenario.AgentSpecies.Component> agents;
	
	public AbstractScenario() {
		agents = new ArrayList<Scenario.AgentSpecies.Component>();
	}

	@Override
	protected Clock make_clock() {
		//TODO : change default speed
		return new Clock_impl(300);
	}

	@Override
	protected abstract Environnement make_env();

	@Override
	protected AgentSpecies make_AgentSpecies(String id) {
		final String uid = id;
		return new AgentSpecies() {

			@Override
			protected Agent make_agent() {
				return new AbstractAgent(uid);
			}
			
		};
	}

	@Override
	protected AgentJoining make_rjc() {
		return new AgentJoining_impl();
	}

	@Override
	protected SetupScenario make_setup() {
		return this;
	}

	@Override
	public void addAgent() {
		agents.add(newAgentSpecies(UUID.randomUUID().toString()));
	}

	@Override
	protected ActObservable make_observeAllAgents() {
		return new ActObservable() {
			
			@Override
			public void removeActListener(ActListener ac) {
				for (AgentSpecies.Component species : agents ) {
					species.actObservable().removeActListener(ac);;
				}
			}
			
			@Override
			public void fireAct() {
				for (AgentSpecies.Component species : agents ) {
					species.actObservable().fireAct();
				}
			}
			
			@Override
			public void addActListener(ActListener ac) {
				for (AgentSpecies.Component species : agents ) {
					species.actObservable().addActListener(ac);
				}
			}
		};
	}

	@Override
	protected abstract Gui make_gui();

}
