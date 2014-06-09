package simpleGridScenario;

import simpleGridScenario.GridEnvironnement.TileStatus;
import framework.Agent;
import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.SetupScenario;
import framework.impl.AbstractScenario;

public class GridScenario extends AbstractScenario<GridContext, ActionableGrid, SetupScenario> {
	private int width, height;
	
	public GridScenario(int width, int height) {
		super(100);
		
		this.width = width;
		this.height = height;
	}

	@Override
	protected Environnement<GridContext, ActionableGrid> make_env() {
		return new GridEnvironnement(width, height);
	}
	
	public static void main(String[] args) {

		Scenario.Component<GridContext, ActionableGrid, SetupScenario> scenario = new GridScenario(15, 20).newComponent();
		scenario.setup().redirectAgentLogToConsole(true);
		scenario.setup().redirectAgentLogToFile("boua");


		
		scenario.setup().addAgent(7,4);
		scenario.setup().addAgent(1,2);
		scenario.setup().addAgent(17,4);
		scenario.setup().addAgent(11,2);
		scenario.setup().addAgent(12,4);
		scenario.setup().addAgent(12,2);
		scenario.setup().addAgent(4,4);
		scenario.setup().addAgent(5,2);
		
		scenario.speed().play();
	}

	@Override
	protected Gui<GridContext> make_gui() {
		return new GridGui(width, height);
	}

	@Override
	protected Agent<GridContext, ActionableGrid> make_agent(String id) {
		return new GridAgent(id);
	}
	
	@Override
	public Scenario.AgentSpecies.Component<GridContext, ActionableGrid, SetupScenario> addAgent(Object...parameters) {
		int x = (Integer)parameters[0];
		int y = (Integer)parameters[1];
		try {
			if (parts().env().context().getStatus(x, y).equals(TileStatus.FREE)) {
				Scenario.AgentSpecies.Component<GridContext, ActionableGrid, SetupScenario> a = super.addAgent();
				a.setupAgent().initAgent(parameters[0], parameters[1]);
				parts().env().actionable().setStatus(x, y, TileStatus.AGENT);
				
				return a;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
