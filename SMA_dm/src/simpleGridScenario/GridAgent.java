package simpleGridScenario;

import java.awt.Point;
import java.util.Random;

import simpleGridScenario.GridEnvironnement.OutOfBondsException;
import simpleGridScenario.GridEnvironnement.TileStatus;
import framework.Act;
import framework.Decide;
import framework.IMemory;
import framework.Knowledge;
import framework.Logger;
import framework.Perceive;
import framework.SetupAgent;
import framework.impl.AbstractAct;
import framework.impl.AbstractAgent;
import framework.impl.AbstractDecide;
import framework.impl.AbstractPerceive;

public class GridAgent extends AbstractAgent<GridContext, ActionableGrid> implements SetupAgent {
	Point currentPosition;

	public GridAgent(String uid) {
		super(uid);
	}
	
	@Override
	protected Perceive<GridContext> make_perception() {
		return new AbstractPerceive<GridContext>(getUid()) {
			
			@Override
			public void perceive() {
				requires().logger().log("My position is : " + currentPosition.toString());
			}
		};
	}

	@Override
	protected Decide<ActionableGrid> make_decision() {
		return new AbstractDecide<ActionableGrid>() {
			
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
	protected Act<ActionableGrid> make_action() {
		return new GridAct();
	}
	
	public class GridAct extends AbstractAct<ActionableGrid> implements ActionableGrid {

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
		protected ActionableGrid make_action() {
			return this;
		}
		
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

}
