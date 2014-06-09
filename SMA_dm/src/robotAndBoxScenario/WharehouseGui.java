package robotAndBoxScenario;

import framework.GUI.DrawService;
import framework.impl.AbstractGui;

public class WharehouseGui extends AbstractGui<WharehouseContext> {
	int width;
	int height;
	DrawService gui;
	
	public WharehouseGui(int width, int height) {
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
		// TODO for each tile is here, change it by a list of point given by environnement
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
