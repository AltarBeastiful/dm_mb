package simpleScenario;

import framework.Agent;
import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.impl.AbstractGui;
import framework.impl.AbstractScenario;

public class SimpleScenario extends AbstractScenario {
	
	public SimpleScenario() {
		super(5000);
	}
	
	public static void main(String[] args) {
		Scenario.Component scenario = new SimpleScenario().newComponent();
		
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		
		scenario.speed().play();
	}

	@Override
	protected Environnement make_env() {
		return new SimpleEnvironnement();
	}

	@Override
	protected Gui make_gui() {
		return new AbstractGui() {
			
			@Override
			public void actFired() {
				System.out.println("Act was fired => Redrawing gui");
			}
		};
	}

	@Override
	protected Agent make_agent(String id) {
		return new SimpleAgent(id);
	}
}
