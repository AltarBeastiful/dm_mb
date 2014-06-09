package framework.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JPanel;

public class UIPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int widthRatio;
	private int heightRatio;
	private int virtualGridWidth = 100;
	private int virtualGridHeight = 100;

	public UIPanel(int w, int h) {
		
	}

	private void drawGrid(Graphics g) {
		ratio(this.getWidth(), this.getHeight());
		for (int x = 1; x < virtualGridWidth + 1; x++) {
			g.drawLine((x * widthRatio), 0, (x * widthRatio), this.getHeight());
		}
		for (int y = 1; y < virtualGridHeight + 1; y++) {
			g.drawLine(0, (y * heightRatio), this.getWidth(), (y * heightRatio));
		}
	}

	private void ratio(int w, int h) {
		widthRatio = Math.round(w / virtualGridWidth);
		heightRatio = Math.round(h / virtualGridHeight);
	}

	private void printShape(Shape s,Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(s);
		g2d.fill(s);
	}

	public void drawAt(int x, int y, Color c, Graphics g) {
		g.setColor(c);
		Rectangle r = new Rectangle(x * widthRatio, y * heightRatio,
				widthRatio, heightRatio);
		printShape(r,g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Print all env here. Update of UI will be manage with this shit.
		drawGrid(g);
		drawAt(5, 10, Color.yellow, g);
	}

}
