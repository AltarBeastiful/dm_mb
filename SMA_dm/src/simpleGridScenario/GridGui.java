package simpleGridScenario;

import framework.SpeedRegulation;
import framework.GUI.DrawService;
import framework.impl.AbstractGui;

public class GridGui extends AbstractGui<GridContext>{
	int width;
	int height;
	DrawService gui;
	
	public GridGui(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();
		gui = new DrawService(width, height, this.requires().speed());
	}

	@Override
	public void actFired() {
		// TODO for each tile is here
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				try {
					gui.drawAt(i, j, this.requires().env().getStatusColor(i, j));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		gui.repaint();
	}
}
