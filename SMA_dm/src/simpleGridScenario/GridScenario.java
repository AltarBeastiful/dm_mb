package simpleGridScenario;

import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.impl.AbstractScenario;

public class GridScenario extends AbstractScenario {

	@Override
	protected Environnement make_env() {
		return new GridEnvironnement(100, 200);
	}
	
	public static void main(String[] args) {
		Scenario.Component scenario = new GridScenario().newComponent();
		
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		
		scenario.speed().play();
		
	}

	@Override
	protected Gui make_gui() {
		return new GridGui();
	}

}
