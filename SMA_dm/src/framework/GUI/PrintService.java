package framework.GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;

public class PrintService {

	private Graphics2D g2d;
	private int widthRatio;
	private int heightRatio;
	private int virtualGridWidth = 100;
	private int virtualGridHeight = 100;

	public PrintService() {
		// TODO Auto-generated constructor stub
	}
	public PrintService(int w, int h) {
		virtualGridHeight=h;
		virtualGridWidth=w;
	}

	public void drawComponent(List<Shape> shapes) {
		for (Shape s : shapes) {
			printShape(s);
		}
	}

	private void printShape(Shape s) {
		if (g2d != null) {
			g2d.draw(s);
			g2d.fill(s);
		}
	}

	public void drawAt(int x, int y, Color c) {
		g2d.setColor(c);
		Rectangle r = new Rectangle(x*widthRatio, y* heightRatio, widthRatio, heightRatio);
		printShape(r);
	}
	public void ratio(int w, int h){
		widthRatio = Math.round(w / virtualGridWidth);
		heightRatio = Math.round(h / virtualGridHeight);
	}
	
	public void drawGrid(int width, int height){
		for (int x = 1; x < width + 1; x++) {
			g2d.drawLine((x * widthRatio), 0, (x * widthRatio), height);
		}
		for (int y = 1; y < height + 1; y++) {
			g2d.drawLine(0, (y * heightRatio), width, (y * heightRatio));
		}
	}

	public void setHeight(int heightRatio) {
		this.heightRatio = heightRatio;

	}

	public void setWidth(int widthRatio) {
		this.widthRatio = widthRatio;
	}

	public void setGraphics(Graphics2D g2d) {
		this.g2d = g2d;

	}

}
