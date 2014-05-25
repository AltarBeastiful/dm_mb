package framework.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrintService {

	private Graphics2D g2d;
	private JPanel panel;
	private int widthRatio;
	private int heightRatio;
	private int virtualGridWidth = 100;
	private int virtualGridHeight = 100;

	public PrintService() {
		panel=new JPanel();
		this.g2d=(Graphics2D) panel.getGraphics();
	}
	public PrintService(int w, int h) {
		virtualGridHeight=h;
		virtualGridWidth=w;
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setSize(600, 400);
		window.setTitle("Agents UI");
		panel=new JPanel();
		this.g2d=(Graphics2D) panel.getGraphics();
		window.add(panel);
		Container contentPane = window.getContentPane();
		contentPane.add(panel);
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
	
	public void drawGrid(){
		for (int x = 1; x < panel.getHeight()+ 1; x++) {
			g2d.drawLine((x * widthRatio), 0, (x * widthRatio),panel.getWidth() );
		}
		for (int y = 1; y < panel.getHeight() + 1; y++) {
			g2d.drawLine(0, (y * heightRatio), panel.getWidth(), (y * heightRatio));
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
	public JPanel getPanel() {
		return panel;
	}
}
