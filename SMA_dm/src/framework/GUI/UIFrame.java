package framework.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int widthRatio;
	private int heightRatio;
	private int virtualGridWidth = 100;
	private int virtualGridHeight = 100;
	private Graphics g;

	public UIFrame(int w, int h) {
		super();
		final JPanel panel = new UIPanel(virtualGridWidth, virtualGridHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);
		this.setVisible(true);
		this.setSize(800, 700);
		this.setTitle("Agents UI");
		getContentPane().add(panel);
		g = panel.getGraphics();
		widthRatio = w;
		heightRatio = h;
	}


	public static void main(String args[]) {
		try {
			UIFrame frame = new UIFrame(100, 100);
			frame.setVisible(true);
			//frame.drawGrid();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
