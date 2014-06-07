package simpleGridScenario;

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
		
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		//TODO : add agent with parameters
		
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
	protected AgentSpecies<GridContext, ActionableGrid> make_AgentSpecies(String id) {
		// TODO Auto-generated method stub
		return new AgentSpecies<GridContext, ActionableGrid>() {
			
			@Override
			protected Agent<GridContext, ActionableGrid> make_agent() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
