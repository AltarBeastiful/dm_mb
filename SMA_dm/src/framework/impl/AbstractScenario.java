package framework.impl;

import java.util.UUID;

import framework.Agent;
import framework.AgentJoining;
import framework.Clock;
import framework.Environnement;
import framework.Scenario;
import framework.SetupScenario;

public abstract class AbstractScenario extends Scenario implements SetupScenario {

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
				return new Agent_impl(uid);
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
		newAgentSpecies(UUID.randomUUID().toString());
	}

}
