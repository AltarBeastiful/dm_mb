package robotAndBoxScenario;

import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.impl.AbstractScenario;

public class WarehouseScenario extends AbstractScenario {
	public static void main(String[] args) {
		Scenario.Component scenario = new WarehouseScenario().newComponent();
	}

	@Override
	protected Environnement make_env() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Gui make_gui() {
		// TODO Auto-generated method stub
		return null;
	}
}
