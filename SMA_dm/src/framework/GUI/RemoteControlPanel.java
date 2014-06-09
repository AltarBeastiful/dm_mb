package framework.GUI;

import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.soap.Text;

public class RemoteControlPanel extends JPanel {
	
	private JButton pause;
	private JButton start;
	private TextField speed;
	
	public RemoteControlPanel() {
		JFrame control = new JFrame();
		control.setSize(300, 100);
		control.getContentPane().add(this);
		this.setVisible(true);
		control.setVisible(true);
		start = new JButton();
		start.setText("Start");
		pause = new JButton();
		pause.setText("Pause");
		speed = new TextField();
		speed.setText("Speed");
		this.add(start);
		this.add(pause);
		this.add(speed);
		
	}

}
