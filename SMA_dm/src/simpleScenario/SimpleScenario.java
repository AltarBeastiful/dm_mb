package simpleScenario;

import framework.Agent;
import framework.Environnement;
import framework.Gui;
import framework.Scenario;
import framework.SetupScenario;
import framework.impl.AbstractGui;
import framework.impl.AbstractScenario;

public class SimpleScenario extends AbstractScenario<SimpleContext, SimpleActionable, SetupScenario> {
	
	public SimpleScenario() {
		super(5000);
	}
	
	public static void main(String[] args) {
		Scenario.Component<SimpleContext, SimpleActionable, SetupScenario> scenario = new SimpleScenario().newComponent();
		
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		scenario.setup().addAgent();
		
		scenario.speed().play();
	}

	@Override
	protected Environnement<SimpleContext, SimpleActionable> make_env() {
		return new SimpleEnvironnement();
	}

	@Override
	protected Gui make_gui() {
		return new AbstractGui() {
			
			@Override
			public void actFired() {
				System.out.println("Act was fired => Redrawing gui");
			}
		};
	}

	@Override
	protected Agent<SimpleContext, SimpleActionable> make_agent(String id) {
		return new SimpleAgent(id);
	}

}
