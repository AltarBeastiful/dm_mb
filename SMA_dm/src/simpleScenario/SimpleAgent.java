package simpleScenario;

import framework.Act;
import framework.Decide;
import framework.DecisionMaking;
import framework.IActionable;
import framework.IMemory;
import framework.IWorkLoad;
import framework.Knowledge;
import framework.Perceive;
import framework.impl.AbstractAgent;
import framework.impl.AbstractPerceive;
import framework.Callable;

public class SimpleAgent extends AbstractAgent {

	// no coords but in an other example yeah !
	public SimpleAgent(String uid) {
		super(uid);
	}
	
	@Override
	protected Perceive make_perception() {
		return new Perceive() {
			
			@Override
			protected Callable make_perception() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	@Override
	protected Decide make_decision() {
		return new Decide() {

			@Override
			protected Callable make_decision() {
				// TODO Auto-generated method stub
				return null;
			}
			// get context from perception
			// get the updated memory 
			// calculate the best choice
			// give the choice to act !
		};
	}
	
	@Override
	protected Act make_action() {
		return new Act() {

			@Override
			protected IActionable make_action() {
				// TODO Auto-generated method stub
				return null;
			}
			// get the decision from decision
			// give the action to environement and if that it's possible 
			// update agent state : x and y 
			// if it wasn"t possible : do nothing or second best choice !	
		};
	}
	
	@Override
	protected Knowledge make_knowledge() {
		// TODO Auto-generated method stub
		return new Knowledge() {
			
			@Override
			protected IMemory make_memory() {
				// TODO Auto-generated method stub
				return new IMemory() {
				
				};
			}
		};
	}
	
	@Override
	protected IWorkLoad make_charge() {
		// TODO Auto-generated method stub
		return new IWorkLoad() {
		};
	}
	
}
