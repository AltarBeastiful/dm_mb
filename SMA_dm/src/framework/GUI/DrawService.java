package framework.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

public class DrawService extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int widthRatio;
	private int heightRatio;
	private int virtualGridWidth = 100;
	private int virtualGridHeight = 100;
	UIFrame ds;
	
	private HashMap<Point, Color> currentState;

	public DrawService(int w, int h) {
		ds = new UIFrame(this);
		virtualGridHeight=h;
		virtualGridWidth=w;
		currentState=new HashMap<Point, Color>();
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

	//Callable only in paintComponent(), or if you know what your doing
	private void printShape(Shape s,Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(s);
		g2d.fill(s);
	}
	
	//Callable only in paintComponent(), or if you know what your doing
	private void drawAt(int x, int y, Color c, Graphics g) {
		g.setColor(c);
		Rectangle r = new Rectangle(x * widthRatio, y * heightRatio,
				widthRatio, heightRatio);
		printShape(r,g);
	}
	
	
	//Use this method to store a point to draw.
	public void drawAt(int x, int y, Color c){
		currentState.put(new Point(x, y), c);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		for (Entry<Point, Color> entry : currentState.entrySet()) {
			Point p= entry.getKey();
			drawAt(p.x, p.y, entry.getValue(),g);
		}
	}

}
