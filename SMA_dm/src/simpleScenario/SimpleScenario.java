package simpleScenario;

import framework.Environnement;
import framework.Scenario;
import framework.impl.AbstractScenario;

public class SimpleScenario extends AbstractScenario {
	public static void main(String[] args) {
		Scenario.Component scenario = new SimpleScenario().newComponent();
		
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		
		scenario.speed().play();
	}

	@Override
	protected Environnement make_env() {
		return new SimpleEnvironnement();
	}
}
