package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import simulator.control.Controller;
import simulator.model.Body;

public class StatusBar extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder(BorderFactory.createBevelBorder(1));
		JLabel _Time = new JLabel();
		_Time.setText("Time:");
		_Time.setPreferredSize(new Dimension(35, 10));
		this.add(_Time);
		
		_currTime = new JLabel();
		this.add(_currTime);
		_currTime.setText("0");
		_currTime.setPreferredSize(new Dimension(80, 10));
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		Dimension d = new Dimension(10, 0);
		d.height = _currTime.getPreferredSize().height*7/4;
		separator.setPreferredSize(d);
		this.add(separator);
		
		JLabel _Bodies = new JLabel();
		_Bodies.setText("Bodies:");
		_Bodies.setPreferredSize(new Dimension(45, 10));
		this.add(_Bodies);
		
		_numOfBodies = new JLabel();
		_numOfBodies.setText("0");
		_numOfBodies.setPreferredSize(new Dimension(45, 10));
		this.add(_numOfBodies);
		
		
		JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
		separator2.setPreferredSize(d);
		this.add(separator2);
		
		JLabel _Laws = new JLabel();
		_Laws.setText("Laws: ");
		_Laws.setPreferredSize(new Dimension(45, 10));
		this.add(_Laws);
		
		_currLaws = new JLabel();
		_currLaws.setText("Not choosen");
		_currLaws.setPreferredSize(new Dimension(250, 30));
		this.add(_currLaws);
		
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_currLaws.setText(gLawsDesc);
		
	}
	
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_currTime.setText("0");
		_numOfBodies.setText("0");
		_currLaws.setText(gLawsDesc);
	}
	
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_numOfBodies.setText(Integer.toString(bodies.size()));
		
	}
	
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_currTime.setText(Double.toString(time));
	}
	
	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}
	
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		_currLaws.setText(gLawsDesc);
	}
}


