package simpleGridScenario;

import simpleGridScenario.GridEnvironnement.TileStatus;
import framework.Agent;
import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.impl.AbstractScenario;

public class GridScenario extends AbstractScenario<GridContext, ActionableGrid> {
	private int width, height;
	
	public GridScenario(int width, int height) {
		super(5000);
		
		this.width = width;
		this.height = height;
	}

	@Override
	protected Environnement<GridContext, ActionableGrid> make_env() {
		return new GridEnvironnement(width, height);
	}
	
	public static void main(String[] args) {
		Scenario.Component<GridContext, ActionableGrid> scenario = new GridScenario(100, 200).newComponent();
		
		scenario.setup().addAgent(3,4);
		scenario.setup().addAgent(1,2);
		scenario.setup().addAgent(5,6);
		
		scenario.speed().play();
	}

	@Override
	protected Gui make_gui() {
		return new GridGui(width, height);
	}

	@Override
	protected Agent<GridContext, ActionableGrid> make_agent(String id) {
		return new GridAgent(id);
	}
	
	@Override
	public Scenario.AgentSpecies.Component<GridContext, ActionableGrid> addAgent(Object...parameters) {
		int x = (Integer)parameters[0];
		int y = (Integer)parameters[1];
		try {
			if (parts().env().context().getStatus(x, y).equals(TileStatus.FREE)) {
				//TODO : Move those 3 lines in super.addAgent() 
				Scenario.AgentSpecies.Component<GridContext, ActionableGrid> a = super.addAgent();
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
