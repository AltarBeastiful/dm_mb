package robotAndBoxScenario;

import robotAndBoxScenario.WarehouseEnvironnement.TileStatus;
import simpleGridScenario.GridGui;
import framework.Agent;
import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.impl.AbstractScenario;

public class WarehouseScenario extends AbstractScenario<WharehouseContext, ActionableWharehouse, SetupWarehouse> implements SetupWarehouse {
	private int width, height;
	
	public WarehouseScenario(int width, int height) {
		super(2000);
		
		this.width = width;
		this.height = height;
	}
	@Override
	protected Environnement<WharehouseContext, ActionableWharehouse> make_env() {
		return new WarehouseEnvironnement(width, height);
	}
	
	public static void main(String[] args) {
		Scenario.Component<WharehouseContext, ActionableWharehouse, SetupWarehouse> scenario = new WarehouseScenario(15, 20).newComponent();
		
		scenario.setup().redirectAgentLogToConsole(true);
//		scenario.setup().redirectAgentLogToFile("boua");
		
		scenario.setup().addTunnel(3);
		scenario.setup().addTunnel(15);
		
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
	protected Gui<WharehouseContext> make_gui() {
		return new WharehouseGui(width, height);
	}

	@Override
	protected Agent<WharehouseContext, ActionableWharehouse> make_agent(String id) {
		return new Robot(id);
	}
	
	@Override
	public Scenario.AgentSpecies.Component<WharehouseContext, ActionableWharehouse, SetupWarehouse> addAgent(Object...parameters) {
		int x = (Integer)parameters[0];
		int y = (Integer)parameters[1];
		try {
			TileStatus arrivalStatus = parts().env().context().getStatus(x, y);
			if (arrivalStatus.equals(TileStatus.FREE) || arrivalStatus.equals(TileStatus.BOX)) {
				Scenario.AgentSpecies.Component<WharehouseContext, ActionableWharehouse, SetupWarehouse> a = super.addAgent();
				a.setupAgent().initAgent(parameters[0], parameters[1]);
				
				if(arrivalStatus.equals(TileStatus.BOX)) {
					parts().env().actionable().setStatus(x, y, TileStatus.ROBOT_CARRYING);
				} else {
					parts().env().actionable().setStatus(x, y, TileStatus.ROBOT);
				}
				
				return a;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected SetupWarehouse make_setup() {
		return this;
	}

	@Override
	public boolean addTunnel(int y) {
		return this.parts().env().actionable().addTunnel(y);
	}
	
	@Override
	public boolean removeTunnel(int y) {
		//TODO : Kill agents that were in the tunnel
//		for(Scenario.AgentSpecies.Component<WharehouseContext, ActionableWharehouse, SetupWarehouse> a : agents) {
//			
//		}
		
		return this.parts().env().actionable().removeTunnel(y);
	}
	
	@Override
	public boolean addBox(int x, int y) throws Exception {
		return this.parts().env().actionable().setStatus(x, y, TileStatus.BOX);
	}
}
