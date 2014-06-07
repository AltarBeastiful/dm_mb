package simpleGridScenario;

import framework.GUI.PrintService;
import framework.impl.AbstractGui;

public class GridGui extends AbstractGui{
	PrintService gui;
	
	public GridGui(int width, int height) {
		gui = new PrintService(width, height);
	}

	@Override
	public void actFired() {
		// TODO Redraw interface
		
		gui.drawGrid();
		
	}
	
}
