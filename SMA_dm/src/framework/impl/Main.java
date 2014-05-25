package framework.impl;

import framework.Environnement;
import framework.Scenario;

public class Main {
	public static void main(String[] args) {
		final Environnement.Component env = new Environnement_impl().newComponent();
		Scenario.Component scenario = new AbstractScenario().newComponent();
		
		
	}
}
