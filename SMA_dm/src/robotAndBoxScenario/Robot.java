package robotAndBoxScenario;

import java.awt.Point;
import java.util.Random;

import robotAndBoxScenario.WarehouseEnvironnement.TileStatus;
import simpleGridScenario.GridEnvironnement.OutOfBondsException;
import framework.Act;
import framework.Decide;
import framework.IMemory;
import framework.Knowledge;
import framework.Perceive;
import framework.SetupAgent;
import framework.impl.AbstractAct;
import framework.impl.AbstractAgent;
import framework.impl.AbstractDecide;
import framework.impl.AbstractPerceive;

public class Robot extends AbstractAgent<WharehouseContext, ActionableWharehouse> implements SetupAgent{
	private Point currentPosition;

	public Robot(String uid) {
		super(uid);
	}

	@Override
	protected Perceive<WharehouseContext> make_perception() {
		return new AbstractPerceive<WharehouseContext>(getUid()) {
			
			@Override
			public void perceive() {
				requires().logger().log("My position is : " + currentPosition.toString());
			}
		};
	}

	@Override
	protected Decide<ActionableWharehouse> make_decision() {
		return new AbstractDecide<ActionableWharehouse>() {
			
			@Override
			public void decide() {
				Point newPos = new Point(currentPosition);
				
				if(((new Random().nextInt()) & 1) == 0 ) {
					//even
					newPos.x++;
				}else{
					//odd
					newPos.y++;
				}
				
				try {
					if(requires().action().moveAgent(currentPosition.x, currentPosition.y, newPos.x, newPos.y)) {
						currentPosition = newPos;
						requires().logger().log("I Moved ");
					}else {
						requires().logger().log("I stuck");
					}
				} catch (OutOfBondsException e) {
					requires().logger().log("Tried to walk out of bound");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	protected Knowledge make_knowledge() {
		return new Knowledge() {
			
			@Override
			protected IMemory make_memory() {
				return new IMemory() {
				}; 
			}
		};
	}

	@Override
	protected Act<ActionableWharehouse> make_action() {
		return new WharehouseAct();
	}

	@Override
	protected SetupAgent make_setup() {
		return this;
	}

	@Override
	public void initAgent(Object... objects) {
		if(objects.length >= 2 && objects[0].getClass().equals(Integer.class) && objects[1].getClass().equals(Integer.class)) {
			currentPosition = new Point((Integer)objects[0], (Integer)objects[1]);
		}
	}
	
	public class WharehouseAct extends AbstractAct<ActionableWharehouse> implements ActionableWharehouse {

		@Override
		public boolean moveAgent(int x, int y, int newX, int newY)
				throws Exception {
			boolean result = this.requires().env().moveAgent(x, y, newX, newY);
			this.fireAct();
			return result;
		}

		@Override
		public boolean setStatus(int x, int y, TileStatus s) throws Exception {
			boolean result = this.requires().env().setStatus(x, y, s);
			this.fireAct();
			return result;
		}
		

		@Override
		protected ActionableWharehouse make_action() {
			return this;
		}

		@Override
		public boolean addTunnel(int y) {
			boolean result = this.requires().env().addTunnel(y);
			this.fireAct();
			return result;
		}

		@Override
		public boolean removeTunnel(int y) {
			boolean result = this.requires().env().removeTunnel(y);
			this.fireAct();
			return result;
		}
		
	}

}
