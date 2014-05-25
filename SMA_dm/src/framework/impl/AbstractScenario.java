package framework.impl;

import framework.Agent;
import framework.AgentJoining;
import framework.Clock;
import framework.Environnement;
import framework.Scenario;

public class AbstractScenario extends Scenario {

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();	
//		newAgentSpecies("1");
	}
	
	@Override
	protected Clock make_clock() {
		//TODO : change default speed
		return new Clock_impl(300);
	}

	@Override
	protected Environnement make_env() {
		//TODO : how to specify an environnement subclass for the scenario? sublass Scenario?
		return new Environnement_impl();
	}

	@Override
	protected AgentSpecies make_AgentSpecies(String id) {
		return new AgentSpecies() {

			@Override
			protected Agent make_agent() {
				//TODO : Allow to specify which agent implementation we want to use
				return new Agent_impl();
			}
			
		};
	}

	@Override
	protected AgentJoining make_rjc() {
		return new AgentJoining_impl();
	}

}
