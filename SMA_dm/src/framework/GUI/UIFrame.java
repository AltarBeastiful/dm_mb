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

	public UIFrame( JPanel panel){
		panel.setVisible(true);
		this.setVisible(true);
		this.setSize(800, 700);
		this.setTitle("Agents UI");
		getContentPane().add(panel);
	}


}
