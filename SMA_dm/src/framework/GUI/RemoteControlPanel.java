package framework.GUI;

import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.SpeedRegulation;

public class RemoteControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8346828629870190334L;
	private JButton pause;
	private JButton start;
	private JButton validateSpeed;
	private JButton step;
	private TextField speed;
	private SpeedRegulation regulator;

	public RemoteControlPanel(SpeedRegulation regulation) {
		regulator = regulation;
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
		speed.setText("200");
		step = new JButton();
		step.setText("step");
		validateSpeed = new JButton();
		validateSpeed.setText("Speed");
		this.add(start);
		this.add(pause);
		this.add(speed);
		this.add(validateSpeed);
		this.add(step);
		
		addListener();
	}

	private void addListener() {
		this.step.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemoteControlPanel.this.regulator.step();
			}
		});
		this.pause.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemoteControlPanel.this.regulator.pause();
			}
		});
		this.start.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				RemoteControlPanel.this.regulator.play();

			}
		});
		this.validateSpeed.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoteControlPanel.this.regulator.setSpeed(Integer.parseInt(RemoteControlPanel.this.speed.getText()));
				
			}
		});


	}

}
