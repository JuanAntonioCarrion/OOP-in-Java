package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	private final String[] columnNames = { "Id", "Mass", "Position", "Velocity", "Acceleration" };
	private final int COLUMNCOUNT = 5;
	private List<Body> _bodies;
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	@Override
	public int getRowCount() {
		return _bodies.size();
	}
	@Override
	public int getColumnCount() {
		return COLUMNCOUNT;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column].toString();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o = null;
		Body b = this._bodies.get(rowIndex);
		switch (columnIndex) {
			case 0: o = b.getId(); break;
			case 1: o = b.getMass(); break;
			case 2: o = b.getPosition(); break;
			case 3: o = b.getVelocity(); break;
			case 4: o = b.getAcceleration(); break;
			default: assert (false);
		}
		return o;
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				_bodies = bodies;	
				fireTableStructureChanged();
			}
			
		});
	}
	
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();

			}
			
		});
	}
	
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();

			}
			
		});
	}
	
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				_bodies = bodies;	
				fireTableStructureChanged();

			}
			
		});
	}
	
	@Override
	public void onDeltaTimeChanged(double dt) {
	
	}
	
	@Override
	public void onGravityLawChanged(String gLawsDesc) {

	}
	
	
}
