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
		super(200);
		
		this.width = width;
		this.height = height;
	}
	@Override
	protected Environnement<WharehouseContext, ActionableWharehouse> make_env() {
		return new WarehouseEnvironnement(width, height);
	}
	
	public static void main(String[] args) {
		int width = 50;
		int height = 70;
		Scenario.Component<WharehouseContext, ActionableWharehouse, SetupWarehouse> scenario = new WarehouseScenario(width, height).newComponent();
		
		scenario.setup().redirectAgentLogToConsole(true);
		scenario.setup().redirectAgentLogToFile("boua");
		
		scenario.setup().addTunnel(69);
		scenario.setup().addTunnel(68);
		scenario.setup().addTunnel(58);
		
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				scenario.setup().addAgent(i,j);
			}
		}
		
		for(int i = 0; i <= 6; i++) {
			for(int j = 15; j < 25; j++ ) {
				try {
					scenario.setup().addBox(i, j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

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
		if(this.parts().env().context().getStatus(x, y).equals(TileStatus.FREE)) {
			return this.parts().env().actionable().setStatus(x, y, TileStatus.BOX);
		}
		
		return false;
	}
}
