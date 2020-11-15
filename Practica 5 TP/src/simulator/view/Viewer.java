package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;

public class Viewer extends JComponent implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	private int _centerX;
	private int _centerY;
	private Controller _ctrl;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _firstTime;
	
	Viewer(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	private void initGUI() {		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width*3/4, screenSize.height*3/4));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.blue), new TitledBorder(
				BorderFactory.createLineBorder(Color.black, 2), "Viewer",     
				TitledBorder.LEFT, TitledBorder.TOP)));
		
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_firstTime = true;
		
		
		addKeyListener(new KeyListener() {
		
		@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
					case '-':
						_scale = _scale * 1.1;
					break;
					case '+':
						_scale = Math.max(1000.0, _scale / 1.1);
					break;
					case '=':
						autoScale();
					break;
					case 'h':
						_showHelp = !_showHelp;
					break;
					default:
				}
				repaint();
			}
				
				@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		addMouseListener(new MouseListener() {
		// ...
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		
		if (_firstTime) {
			autoScale();
			_firstTime = false;
		}
			
		gr.setColor(Color.WHITE);
		gr.drawRect(0, 0, getWidth(), getHeight());
		gr.fillRect(0, 0, getWidth(), getHeight());
		gr.setBackground(Color.WHITE);
		

		for(Body b: _bodies) {
			gr.setColor(Color.BLUE);
			int coordinateX =   _centerX + (int) (b.getPosition().coordinate(0)/_scale);
			int coordinateY =  _centerY - (int) (b.getPosition().coordinate(1)/_scale);
			gr.fillOval(coordinateX - 5, coordinateY - 5, 10, 10);
			gr.setColor(Color.black);
			gr.drawString(b.getId(), coordinateX - 6, coordinateY - 10);
		}
		gr.setColor(Color.RED);
		gr.drawLine(_centerX+3, _centerY, _centerX-3, _centerY);
        gr.drawLine(_centerX, _centerY+3, _centerX, _centerY-3);

        if (_showHelp) {
			gr.setColor(Color.RED);
			gr.drawString("h: toogle help, +: zoom-in, -: zoom-out, =: fit", 10, 25);
			gr.drawString("Scaling ratio: " + _scale, 10, 40);
		}   

	}

	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
			Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),
		(double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		autoScale();
		repaint();
	}
	
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		autoScale();
		repaint();
	}
	
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;
		autoScale();
		repaint();
	}
	
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_bodies = bodies;
		repaint();
	}
	
	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
	}
}