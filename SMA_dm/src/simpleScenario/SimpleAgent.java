package simpleScenario;

import framework.Act;
import framework.Decide;
import framework.IMemory;
import framework.IWorkLoad;
import framework.Knowledge;
import framework.Perceive;
import framework.impl.AbstractAct;
import framework.impl.AbstractAgent;
import framework.impl.AbstractDecide;
import framework.impl.AbstractPerceive;

public class SimpleAgent extends AbstractAgent {

	// no coords but in an other example yeah !
	public SimpleAgent(String uid) {
		super(uid);
	}
	
	@Override
	protected Perceive make_perception() {
		return new AbstractPerceive(getUid()) {
			
			@Override
			public void perceive() {
				System.out.println(getUid() + " : " + ((SimpleContext)this.requires().context()).getStatus());
			}
		};
	}
	
	@Override
	protected Decide make_decision() {
		return new AbstractDecide() {
			
			@Override
			public void decide() {
				// get context from perception
				// get the updated memory 
				// calculate the best choice
				// give the choice to act !
				
				//Deciding.....
				
				//Now I'll act !
				((SimpleActionable)this.requires().action()).toggleIsSimple();
			}
		};
	}
	
	@Override
	protected Act make_action() {
		return new SimpleAct();
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
	
	public class SimpleAct extends AbstractAct implements SimpleActionable {

		@Override
		public void toggleIsSimple() {
			getActionable().toggleIsSimple();
			this.fireAct();
		}
		
		private SimpleActionable getActionable() { 
			return ((SimpleActionable)this.requires().env());
		}
	}
	
}
