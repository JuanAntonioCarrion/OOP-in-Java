package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import org.json.JSONObject;
import simulator.control.Controller;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;

public class ControlPanel extends JPanel  implements SimulatorObserver{
	
	
	private JButton fileChooserButton;
	private JButton gravityChooserButton;
	private JButton runButton;
	private JButton stopButton;
	private JSpinner _spinner;
	private JTextField _dtValue;
	private Controller _ctrl;
	private boolean _stopped;
	private static final long serialVersionUID = 1L;

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
		}

	private void initGUI() {	

		this.setLayout(new BorderLayout());
		this.add(createUtilsPanel(), BorderLayout.WEST);
		this.add(createClosePanel(), BorderLayout.EAST);	
	}
	
	private JPanel createUtilsPanel() {
		JPanel utilsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JToolBar fileChooserToolbar = new JToolBar();
		JToolBar gravityToolbar = new JToolBar();
		JToolBar runToolbar = new JToolBar();

		fileChooserButton = new JButton();
		fileChooserButton.setEnabled(true);
		fileChooserButton.setIcon(new ImageIcon("resources/icons/open.png"));
		fileChooserButton.setToolTipText("File chooser: Opens archive");
		
		fileChooserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = null;
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("resources/examples"));
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						InputStream br = new FileInputStream(file);
						_ctrl.reset();
						_ctrl.loadBodies(br);
					} 
					catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								 "This file has not a valid format", "Error Icon",
								 JOptionPane.ERROR_MESSAGE);
					}

				} 

			}
			
		});
		fileChooserToolbar.add(fileChooserButton, BorderLayout.WEST);
		
		gravityChooserButton = new JButton();
		gravityChooserButton.setEnabled(true);
		gravityChooserButton.setIcon(new ImageIcon("resources/icons/physics.png"));
		gravityChooserButton.setToolTipText("Gravity modifier: Click to choose gravity");
		
		gravityChooserButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new JPanel();
				Factory<GravityLaws> factory = _ctrl.getGravityLawsFactory();
				List<JSONObject> JSONArray = factory.getInfo();
				String[] list = new String[JSONArray.size()];
				int i = 0;
				
				for(JSONObject j: JSONArray) {
					list[i] = factory.createInstance(j).getDescription();
					i++;
				}
				
				String n = (String) JOptionPane.showInputDialog(
			            panel,  "Select gravity laws to be used.",  		            
			            "Gravity Laws selector", JOptionPane.INFORMATION_MESSAGE, 
			            null, list, list[0]);
				
				if (n.equals(list[0])) {
					_ctrl.setGravityLaws(JSONArray.get(0));
				}
				else if (n.equals(list[1])) {
					_ctrl.setGravityLaws(JSONArray.get(1));
				}
				else if (n.equals(list[2])){
					_ctrl.setGravityLaws(JSONArray.get(2));
				}
			}
		});
		gravityToolbar.add(gravityChooserButton);
		
		runButton = new JButton();
		runButton.setEnabled(true);
		runButton.setIcon(new ImageIcon("resources/icons/run.png"));
		runButton.setToolTipText("Run button: Runs the simulator");
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fileChooserButton.setEnabled(false);
				runButton.setEnabled(false);
				gravityChooserButton.setEnabled(false);
				_spinner.setEnabled(false);
				_dtValue.setEnabled(false);

				_stopped = false;
				run_sim((int) _spinner.getValue());
			}
			
		});
		runToolbar.add(runButton);

		
		stopButton = new JButton();
		stopButton.setEnabled(true);
		stopButton.setIcon(new ImageIcon("resources/icons/stop.png"));
		stopButton.setToolTipText("Stop button: Stops the simulator");
		runToolbar.add(stopButton);
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped = true;
				
			}
			
		});
		
		JLabel steps = new JLabel("Steps: ");
		runToolbar.add(steps);	
		
        _spinner = new JSpinner();
        _spinner.setPreferredSize(new Dimension(80, 15));
		runToolbar.add(_spinner);
		_spinner.setValue(0);
		
		JLabel dtLabel = new JLabel("Delta-time: ");
		runToolbar.add(dtLabel);
		
		_dtValue = new JTextField(8); 
		_dtValue.setEditable(true);	
		runToolbar.add(_dtValue);
		_dtValue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					_ctrl.setDeltaTime(Double.parseDouble(_dtValue.getText()));
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,
							 "The text entered is not a number", "Error Icon",
							 JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		utilsPanel.add(fileChooserToolbar);
		utilsPanel.add(gravityToolbar);
		utilsPanel.add(runToolbar);
		
		
		return utilsPanel;
	}
	
	public JPanel createClosePanel() {
		JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JToolBar closeToolBar = new JToolBar();
			
		
		JButton exitButton = new JButton();
		exitButton.setEnabled(true);
		exitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		exitButton.setToolTipText("Stop button: Stops the simulator");
		exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
			
		});
		closeToolBar.add(exitButton);
		closePanel.add(closeToolBar);
	
		
		return closePanel;
	}
	
	private void quit() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Are you sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if (n == 0) {
			System.exit(0);
		}
				
	}
	
	private void run_sim(int n) {
		if ( n > 0 && !_stopped ) {
		try {
			_ctrl.run(1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					 "There was an error in the running simulation", "Error Icon",
					 JOptionPane.ERROR_MESSAGE);
		
			_stopped = true;
			fileChooserButton.setEnabled(true);
			runButton.setEnabled(true);
			gravityChooserButton.setEnabled(true);
			_spinner.setEnabled(true);
			_dtValue.setEnabled(true);

			
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
		public void run() {
			run_sim(n-1);
		}
		});
		} else {
			_stopped = true;
			fileChooserButton.setEnabled(true);
			runButton.setEnabled(true);
			gravityChooserButton.setEnabled(true);
			_spinner.setEnabled(true);
			_dtValue.setEnabled(true);
			}
		}


		@Override
		public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
			_dtValue.setText(Double.toString(dt));
		}

		@Override
		public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
			_spinner.setValue(0);
			_dtValue.setText(Double.toString(dt));
		}

		@Override
		public void onBodyAdded(List<Body> bodies, Body b) {
			
		}

		@Override
		public void onAdvance(List<Body> bodies, double time) {
			
		}

		@Override
		public void onDeltaTimeChanged(double dt) {
			_dtValue.setText(Double.toString(dt));
			
		}

		@Override
		public void onGravityLawChanged(String gLawsDesc) {
			
		}
}


