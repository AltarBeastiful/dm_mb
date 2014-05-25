package simpleScenario;

import framework.Scenario;
import framework.impl.AbstractScenario;

public class SimpleScenario extends AbstractScenario {
	public static void main(String[] args) {
		Scenario.Component scenario = new SimpleScenario().newComponent();
		
		scenario.speed().play();
	}
}
